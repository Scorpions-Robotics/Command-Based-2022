package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class GoTillBlack extends CommandBase {
  DriveSubsystem m_drive;

  public GoTillBlack(DriveSubsystem m_drive) {
    this.m_drive = m_drive;
    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    m_drive.modeBrake();
  }

  @Override
  public void execute() {
    m_drive.arcadeDrive(0.5, 0);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.stopMotors();
    m_drive.modeCoast();
  }

  @Override
  public boolean isFinished() {
    if (m_drive.getIR() < 12) {
      return true;
    }
    return false;
  }
}
