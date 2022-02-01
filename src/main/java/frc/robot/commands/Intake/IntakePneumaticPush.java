package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakePneumaticPush extends CommandBase {
  IntakeSubsystem m_intake;

  public IntakePneumaticPush(IntakeSubsystem m_intake) {
    this.m_intake = m_intake;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_intake.pushPneumatic();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
