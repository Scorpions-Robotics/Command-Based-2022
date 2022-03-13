package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.AdjustShooterAngle;
import frc.robot.commands.Shooter.ShooterTurnNew;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class Shoot extends SequentialCommandGroup {
  public Shoot(ShooterSubsystem m_shooter, VisionSubsystem m_vision) {
    addCommands(
        new AdjustShooterAngle(m_shooter, m_vision)
            .andThen(new ShooterTurnNew(m_shooter, m_vision)));
  }
}
