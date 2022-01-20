

package frc.robot.subsystems;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  private Encoder rightDriveEncoder = new Encoder(Constants.ENCODERS.leftDriveEncoderChannelA, Constants.ENCODERS.leftDriveEncoderChannelB, false, EncodingType.k4X);
  private Encoder leftDriveEncoder = new Encoder(Constants.ENCODERS.rightDriveEncoderChannelA, Constants.ENCODERS.rightDriveEncoderChannelB, false, EncodingType.k4X);

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
  }

  public double getLeftEncoderDistance(){
    leftDriveEncoder.setDistancePerPulse(1.0 / 400.0 * 2.0 * Math.PI * 3.0);
    return leftDriveEncoder.getDistance();
  }

  public double getRightEncoderDistance(){
    rightDriveEncoder.setDistancePerPulse(1.0 / 400.0 * 2.0 * Math.PI * 3.0);
    return rightDriveEncoder.getDistance();
  }

  public void resetEncoders(){
    leftDriveEncoder.reset();
    rightDriveEncoder.reset();
  }

  public void arcadeDrive(double speed, double rotation){
    drive.arcadeDrive(speed, rotation);
  }

  public void stopMotors(){
    drive.stopMotor();
  }

  @Override
  public void periodic() {}
}
