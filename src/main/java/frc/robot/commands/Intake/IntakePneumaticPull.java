package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakePneumaticPull extends CommandBase {
  IntakeSubsystem m_intake;

  public IntakePneumaticPull(IntakeSubsystem m_intake) {
    this.m_intake = m_intake;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_intake.pullPneumatic();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
