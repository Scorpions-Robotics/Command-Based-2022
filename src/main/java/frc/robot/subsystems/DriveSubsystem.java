package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
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

  // private final ColorSensorV3 m_colorSensor1 = new ColorSensorV3(I2C.Port.kOnboard);
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(I2C.Port.kMXP);

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
    m_right.setInverted(false);
    m_left.setInverted(false);

    getLeftEncoderDistance();
    getRightEncoderDistance();

    this.imu.setYawAxis(IMUAxis.kY);
    this.calibrate();

    this.resetGyro();
    this.resetEncoders();
  }

  public void modeCoast() {
    rightFront.setIdleMode(IdleMode.kCoast);
    rightRear.setIdleMode(IdleMode.kCoast);
    leftFront.setIdleMode(IdleMode.kCoast);
    leftRear.setIdleMode(IdleMode.kCoast);
  }

  public void modeBrake() {
    rightFront.setIdleMode(IdleMode.kBrake);
    rightRear.setIdleMode(IdleMode.kBrake);
    leftFront.setIdleMode(IdleMode.kBrake);
    leftRear.setIdleMode(IdleMode.kBrake);
  }

  public double getLeftEncoderDistance() {
    leftDriveEncoder.setDistancePerPulse(1.0 / 20.0 * Math.PI * 6 * (1 / 10.71));
    return leftDriveEncoder.getDistance() * 2.54;
  }

  public double getRightEncoderDistance() {
    rightDriveEncoder.setDistancePerPulse(1.0 / 20.0 * Math.PI * 6 * (1 / 10.71));
    return rightDriveEncoder.getDistance() * 2.54 * -1;
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

  public void runLeftMotorVoltage(double voltage) {
    m_left.setVoltage(voltage);
  }

  public void runRightMotorVoltage(double voltage) {
    m_right.setVoltage(voltage);
  }

  public void resetEncoders() {
    leftDriveEncoder.reset();
    rightDriveEncoder.reset();
  }

  public void arcadeDrive(double speed, double rotation) {
    drive.arcadeDrive(speed * -1, rotation * -1);
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

  // public double getSensor1IR() {
  //  return m_colorSensor1.getIR();
  // }

  public double getSensorIR() {
    return m_colorSensor.getIR();
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(pose, getHeading());
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(
        leftDriveEncoder.getRate(), rightDriveEncoder.getRate() * -1);
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
    odometry.update(getHeading(), getLeftEncoderDistance(), getRightEncoderDistance());
    SmartDashboard.putNumber("IR", getSensorIR());
    // SmartDashboard.putNumber("Left Distance", getLeftEncoderDistance());
    // SmartDashboard.putNumber("Right Distance", getRightEncoderDistance());
    // SmartDashboard.putString("Rotation 2d", getHeading().toString());
    // SmartDashboard.putString("Pose 2d", getPose().toString());
  }
}
