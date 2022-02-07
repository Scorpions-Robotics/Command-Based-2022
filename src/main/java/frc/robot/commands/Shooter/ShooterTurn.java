package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class ShooterTurn extends CommandBase {
  ShooterSubsystem m_shooter;
  VisionSubsystem m_vision;

  public ShooterTurn(VisionSubsystem m_vision, ShooterSubsystem m_shooter) {
    this.m_shooter = m_shooter;
    this.m_vision = m_vision;

    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // gonna change 100 and 600 values
    m_shooter.runShooter(m_shooter.calculateShooterSpeed(m_vision.getHoopD(), 100, 600));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.stopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
