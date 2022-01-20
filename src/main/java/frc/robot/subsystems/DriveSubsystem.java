

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  private WPI_VictorSPX rightLeader = new WPI_VictorSPX(Constants.CAN.rightLeaderID);
  private WPI_VictorSPX rightFollower = new WPI_VictorSPX(Constants.CAN.rightFollowerID);

  private WPI_VictorSPX leftLeader = new WPI_VictorSPX(Constants.CAN.leftLeaderID);
  private WPI_VictorSPX leftFollower = new WPI_VictorSPX(Constants.CAN.leftFollowerID);

  private DifferentialDrive drive = new DifferentialDrive(rightLeader, leftLeader);

  public DriveSubsystem() {
    rightFollower.follow(rightLeader);
    leftFollower.follow(leftLeader);
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
