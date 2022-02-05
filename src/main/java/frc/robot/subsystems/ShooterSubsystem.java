package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private WPI_VictorSPX shooterLeftMotor = new WPI_VictorSPX(Constants.CAN.kShooterLeftMotorID);
  private WPI_VictorSPX shooterRightMotor = new WPI_VictorSPX(Constants.CAN.kShooterRightMotorID);

  public ShooterSubsystem() {
    shooterLeftMotor.follow(shooterRightMotor);
  }

  @Override
  public void periodic() {}

  public void runShooter(double speed) {
    shooterRightMotor.set(speed);
  }

  public void stopShooter() {
    shooterRightMotor.set(Constants.VARIABLES.kZero);
  }
}
