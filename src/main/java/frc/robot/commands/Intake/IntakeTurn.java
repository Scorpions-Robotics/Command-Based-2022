package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeTurn extends CommandBase {
  IntakeSubsystem m_intake;
  double speed;

  public IntakeTurn(IntakeSubsystem m_intake, double speed) {
    this.m_intake = m_intake;
    this.speed = speed;

    addRequirements(m_intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_intake.runIntake(speed);
  }

  @Override
  public void end(boolean interrupted) {
    m_intake.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
