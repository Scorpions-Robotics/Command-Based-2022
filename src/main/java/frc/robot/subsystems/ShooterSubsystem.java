package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private WPI_VictorSPX shooterLeftMotor = new WPI_VictorSPX(Constants.CAN.kShooterLeftMotorID);
  private WPI_VictorSPX shooterRightMotor = new WPI_VictorSPX(Constants.CAN.kShooterRightMotorID);
  double result;
  double max_min_distance_diff;
  double current_min_distance_diff;

  public ShooterSubsystem() {
    shooterLeftMotor.follow(shooterRightMotor);
  }

  @Override
  public void periodic() {}

  public void runShooter(double speed) {
    shooterRightMotor.set(speed);
  }

  public double calculateShooterSpeed(double distance, double min_distance, double max_distance){
    max_min_distance_diff = max_distance - min_distance;
    current_min_distance_diff = distance - min_distance;

    result = current_min_distance_diff / max_min_distance_diff;
    
    // could modify 0.4 constant
    return Math.max(0.4, result);
  }

  public void stopShooter() {
    shooterRightMotor.set(Constants.VARIABLES.kZero);
  }
}
