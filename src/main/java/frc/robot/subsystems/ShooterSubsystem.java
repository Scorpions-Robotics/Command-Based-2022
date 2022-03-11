package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private WPI_VictorSPX shooterLeftMotor = new WPI_VictorSPX(Constants.CAN.kShooterLeftMotorID);
  private WPI_VictorSPX shooterRightMotor = new WPI_VictorSPX(Constants.CAN.kShooterRightMotorID);

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
  double max_min_distance_diff;
  double current_min_distance_diff;
  double max_min_rpm_diff;

  public boolean pneumatic_mode;

  public ShooterSubsystem() {
    shooterLeftMotor.follow(shooterRightMotor);
    shooterLeftMotor.setInverted(true);
  }

  @Override
  public void periodic() {}

  public void runShooter(double speed) {
    shooterRightMotor.set(speed * -1);
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

    max_min_distance_diff = max_distance - min_distance;
    current_min_distance_diff = distance - min_distance;

    result = current_min_distance_diff / max_min_distance_diff;

    max_min_rpm_diff = max_rpm - min_rpm;

    result = max_min_rpm_diff * result + min_rpm;

    return Math.max(min_rpm, result);
  }

  public void runShooterVoltage(double voltage) {
    shooterRightMotor.setVoltage(voltage);
  }

  public double getShooterEncoderRPM() {
    return shooterEncoder.getRate();
  }

  public void stopShooter() {
    shooterRightMotor.set(Constants.VARIABLES.kZero);
  }
}
