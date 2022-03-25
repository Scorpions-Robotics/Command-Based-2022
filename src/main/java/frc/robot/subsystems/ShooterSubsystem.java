package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private CANSparkMax shooterLeftMotor =
      new CANSparkMax(Constants.CAN.kShooterLeftMotorID, MotorType.kBrushed);
  private CANSparkMax shooterRightMotor =
      new CANSparkMax(Constants.CAN.kShooterRightMotorID, MotorType.kBrushed);

  Servo servo = new Servo(Constants.SHOOTER.kServoPWM);

  private Encoder shooterEncoder =
      new Encoder(
          Constants.ENCODERS.kShooterEncoderChannelA,
          Constants.ENCODERS.kShooterEncoderChannelB,
          false,
          EncodingType.k4X);

  private DoubleSolenoid anglePneumatic =
      new DoubleSolenoid(
          PneumaticsModuleType.CTREPCM,
          Constants.PNEUMATICS.kShooterSolenoidForwardChannel,
          Constants.PNEUMATICS.kShooterSolenoidReverseChannel);

  double result;
  double max_min_in_diff;
  double current_min_in_diff;
  double max_min_out_diff;

  public double required_rpm;

  public boolean pneumatic_mode;

  public ShooterSubsystem() {}

  @Override
  public void periodic() {}

  public void runShooter(double speed) {
    shooterLeftMotor.set(-speed);
    shooterRightMotor.set(speed);
  }

  public void SetServoAngle(double angle) {
    servo.setAngle(angle);
  }

  public void pushPneumatic() {
    anglePneumatic.set(DoubleSolenoid.Value.kForward);
    pneumatic_mode = true;
  }

  public void pullPneumatic() {
    anglePneumatic.set(DoubleSolenoid.Value.kReverse);
    pneumatic_mode = false;
  }

  public double calculateShooterSpeed(
      double distance, double min_distance, double max_distance, double min_rpm, double max_rpm) {

    max_min_in_diff = max_distance - min_distance;
    current_min_in_diff = distance - min_distance;

    result = current_min_in_diff / max_min_in_diff;

    max_min_out_diff = max_rpm - min_rpm;

    result = max_min_out_diff * result + min_rpm;

    return Math.max(min_rpm, result);
  }

  public double calculateSpeed(
      double speed, double min_in, double max_in, double min_out, double max_out) {

    max_min_in_diff = max_in - min_in;
    current_min_in_diff = speed - min_in;

    result = current_min_in_diff / max_min_in_diff;

    max_min_out_diff = max_out - min_out;

    result = max_min_out_diff * result + min_out;

    return result;
  }

  public void runShooterVoltage(double voltage) {
    shooterLeftMotor.setVoltage(-voltage);
    shooterRightMotor.setVoltage(voltage);
  }

  public double getShooterEncoderRPM() {
    return shooterEncoder.getRate();
  }

  public void stopShooter() {
    shooterRightMotor.set(Constants.VARIABLES.kZero);
    shooterLeftMotor.set(Constants.VARIABLES.kZero);
  }
}
