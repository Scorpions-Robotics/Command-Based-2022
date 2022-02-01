package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.ADIS16470_IMU.IMUAxis;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  private ADIS16470_IMU imu;
  private double startTime;
  private double driftPerSecond;

  private Encoder rightDriveEncoder =
      new Encoder(
          Constants.ENCODERS.leftDriveEncoderChannelA,
          Constants.ENCODERS.leftDriveEncoderChannelB,
          false,
          EncodingType.k4X);
  private Encoder leftDriveEncoder =
      new Encoder(
          Constants.ENCODERS.rightDriveEncoderChannelA,
          Constants.ENCODERS.rightDriveEncoderChannelB,
          false,
          EncodingType.k4X);

  private WPI_VictorSPX rightLeader = new WPI_VictorSPX(Constants.CAN.rightLeaderID);
  private WPI_VictorSPX rightFollower = new WPI_VictorSPX(Constants.CAN.rightFollowerID);

  private WPI_VictorSPX leftLeader = new WPI_VictorSPX(Constants.CAN.leftLeaderID);
  private WPI_VictorSPX leftFollower = new WPI_VictorSPX(Constants.CAN.leftFollowerID);

  private DifferentialDrive drive = new DifferentialDrive(rightLeader, leftLeader);

  public DriveSubsystem() {
    rightFollower.follow(rightLeader);
    leftFollower.follow(leftLeader);

    getLeftEncoderDistance();
    getRightEncoderDistance();

    this.imu = new ADIS16470_IMU();
    this.imu.setYawAxis(IMUAxis.kY);
    this.calibrate();
  }

  public double getLeftEncoderDistance() {
    leftDriveEncoder.setDistancePerPulse(1.0 / 400.0 * 2.0 * Math.PI * 3.0);
    return leftDriveEncoder.getDistance();
  }

  public double getRightEncoderDistance() {
    rightDriveEncoder.setDistancePerPulse(1.0 / 400.0 * 2.0 * Math.PI * 3.0);
    return rightDriveEncoder.getDistance();
  }

  public double getStraightDriveDistance() {
    return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2;
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

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Angle", getGyroAngle());
  }
}
