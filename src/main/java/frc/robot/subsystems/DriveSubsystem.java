package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.ADIS16470_IMU.IMUAxis;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  private ADIS16470_IMU imu = new ADIS16470_IMU();
  private double startTime;
  private double driftPerSecond;

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  private Encoder leftDriveEncoder =
      new Encoder(
          Constants.ENCODERS.kLeftDriveEncoderChannelA,
          Constants.ENCODERS.kLeftDriveEncoderChannelB,
          false,
          EncodingType.k4X);
  private Encoder rightDriveEncoder =
      new Encoder(
          Constants.ENCODERS.kRightDriveEncoderChannelA,
          Constants.ENCODERS.kRightDriveEncoderChannelB,
          false,
          EncodingType.k4X);

  private CANSparkMax rightFront =
      new CANSparkMax(Constants.CAN.kRightLeaderID, MotorType.kBrushed);
  private CANSparkMax rightRear =
      new CANSparkMax(Constants.CAN.kRightFollowerID, MotorType.kBrushed);

  private CANSparkMax leftFront = new CANSparkMax(Constants.CAN.kLeftLeaderID, MotorType.kBrushed);
  private CANSparkMax leftRear = new CANSparkMax(Constants.CAN.kLeftFollowerID, MotorType.kBrushed);

  MotorControllerGroup m_right = new MotorControllerGroup(rightFront, rightRear);
  MotorControllerGroup m_left = new MotorControllerGroup(leftFront, leftRear);

  private DifferentialDrive drive = new DifferentialDrive(m_right, m_left);
  private DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(getHeading());

  public DriveSubsystem() {
    getLeftEncoderDistance();
    getRightEncoderDistance();

    m_right.setInverted(true);
    m_left.setInverted(true);

    this.imu.setYawAxis(IMUAxis.kY);
    this.calibrate();
  }

  public double getLeftEncoderDistance() {
    leftDriveEncoder.setDistancePerPulse(Math.PI * 6.0 / 5.0);
    return leftDriveEncoder.getDistance();
  }

  public double getRightEncoderDistance() {
    rightDriveEncoder.setDistancePerPulse(Math.PI * 6.0 / 5.0);
    return rightDriveEncoder.getDistance();
  }

  public double getStraightDriveDistance() {
    return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2;
  }

  public void runLeftMotor(double speed) {
    m_left.set(speed);
    ;
  }

  public void runRightMotor(double speed) {
    m_right.set(speed);
  }

  public void resetEncoders() {
    leftDriveEncoder.reset();
    rightDriveEncoder.reset();
  }

  public void arcadeDrive(double speed, double rotation) {
    drive.arcadeDrive(speed, rotation);
  }

  public void stopMotors() {
    drive.stopMotor();
  }

  public double getGyroAngle() {
    double runTime = Timer.getFPGATimestamp() - startTime;
    double drift = runTime * driftPerSecond;
    SmartDashboard.putNumber("Drift", drift);
    SmartDashboard.putNumber("runTime", runTime);
    SmartDashboard.putNumber("driftPerSecond", driftPerSecond);
    SmartDashboard.putNumber("Angle", this.imu.getAngle() - drift);
    return this.imu.getAngle() - drift;
  }

  public double getGyroRate() {
    return this.imu.getRate();
  }

  public void resetGyro() {
    this.imu.reset();
    this.startTime = Timer.getFPGATimestamp();
  }

  public void calibrate() {
    this.startTime = Timer.getFPGATimestamp();
    double startAngle = imu.getAngle();
    try {
      Thread.sleep(5000);
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.driftPerSecond = (imu.getAngle() - startAngle) / (Timer.getFPGATimestamp() - startTime);
  }

  public double getIR() {
    return m_colorSensor.getIR();
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(pose, getHeading());
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(
        leftDriveEncoder.getRate(), rightDriveEncoder.getRate());
  }

  public Rotation2d getHeading() {
    return Rotation2d.fromDegrees(-this.imu.getAngle());
  }

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    m_left.setVoltage(leftVolts);
    m_right.setVoltage(rightVolts);
    drive.feed();
  }

  @Override
  public void periodic() {
    odometry.update(getHeading(), leftDriveEncoder.getDistance(), rightDriveEncoder.getDistance());
    SmartDashboard.putNumber("Angle", getGyroAngle());
    SmartDashboard.putNumber("Left Distance", getLeftEncoderDistance());
    SmartDashboard.putNumber("Right Distance", getRightEncoderDistance());
  }
}
