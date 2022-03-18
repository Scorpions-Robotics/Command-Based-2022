package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class AdjustShooterAngle extends CommandBase {
  ShooterSubsystem m_shooter;
  VisionSubsystem m_vision;

  public AdjustShooterAngle(ShooterSubsystem m_shooter, VisionSubsystem m_vision) {
    this.m_shooter = m_shooter;
    this.m_vision = m_vision;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (m_vision.getHoopD() > 450) {
      m_shooter.pushPneumatic();
    } else {
      m_shooter.pullPneumatic();
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
