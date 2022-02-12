package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private WPI_VictorSPX shooterLeftMotor = new WPI_VictorSPX(Constants.CAN.kShooterLeftMotorID);
  private WPI_VictorSPX shooterRightMotor = new WPI_VictorSPX(Constants.CAN.kShooterRightMotorID);

  private Encoder shooterEncoder =
      new Encoder(
          Constants.ENCODERS.kShooterEncoderChannelA,
          Constants.ENCODERS.kShooterEncoderChannelB,
          false,
          EncodingType.k4X);

  double result;
  double max_min_distance_diff;
  double current_min_distance_diff;
  double max_min_rpm_diff;

  public ShooterSubsystem() {
    shooterLeftMotor.follow(shooterRightMotor);
    shooterLeftMotor.setInverted(true);
  }

  @Override
  public void periodic() {}

  public void runShooter(double speed) {
    shooterRightMotor.set(speed);
  }

  public double calculateShooterSpeed(
      double distance, double isHoopInVision, double min_distance, double max_distance, double min_rpm, double max_rpm) {
        if(isHoopInVision == 1){
          max_min_distance_diff = max_distance - min_distance;
          current_min_distance_diff = distance - min_distance;

          result = current_min_distance_diff / max_min_distance_diff;

          max_min_rpm_diff = max_rpm - min_rpm;

          result = max_min_rpm_diff * result + min_rpm;

          return Math.max(min_rpm, result);
        }
        else{
          // this is just for testing.
          return 0.5;
        }
  }

  public double getShooterEncoderRPM() {
    return shooterEncoder.getRate() * 60;
  }

  public void stopShooter() {
    shooterRightMotor.set(Constants.VARIABLES.kZero);
  }
}
