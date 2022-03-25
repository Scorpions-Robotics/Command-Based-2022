package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  private WPI_VictorSPX intakeMotor = new WPI_VictorSPX(Constants.CAN.kIntakeMotorID);
  DoubleSolenoid intake_solenoid =
      new DoubleSolenoid(
          PneumaticsModuleType.CTREPCM,
          Constants.PNEUMATICS.kIntakeSolenoidForwardChannel,
          Constants.PNEUMATICS.kIntakeSolenoidReverseChannel);

  public boolean pneumaticMode;

  public IntakeSubsystem() {
    pneumaticMode = true;
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("intake pneumatic", pneumaticMode);
  }

  public void runIntake(double speed) {
    intakeMotor.set(speed);
  }

  public void stopIntake() {
    intakeMotor.set(Constants.VARIABLES.kZero);
  }

  public void pushPneumatic() {
    intake_solenoid.set(DoubleSolenoid.Value.kForward);
    pneumaticMode = true;
  }

  public void pullPneumatic() {
    intake_solenoid.set(DoubleSolenoid.Value.kReverse);
    pneumaticMode = false;
  }
}
