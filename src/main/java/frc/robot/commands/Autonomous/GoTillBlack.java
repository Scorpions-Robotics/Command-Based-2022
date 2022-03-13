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
    if (m_drive.getSensor1IR() > 12 && m_drive.getSensor2IR() > 12) {
      m_drive.arcadeDrive(0.5, 0);
    } else if (m_drive.getSensor1IR() < 12 && m_drive.getSensor2IR() > 12) {
      m_drive.runRightMotor(0.4);
      m_drive.runLeftMotor(0);
    } else if (m_drive.getSensor1IR() > 12 && m_drive.getSensor2IR() < 12) {
      m_drive.runRightMotor(0);
      m_drive.runLeftMotor(0.4);
    } else {
      m_drive.stopMotors();
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.stopMotors();
    m_drive.modeCoast();
  }

  @Override
  public boolean isFinished() {
    if (m_drive.getSensor1IR() < 12 && m_drive.getSensor2IR() < 12) {
      return true;
    }
    return false;
  }
}
