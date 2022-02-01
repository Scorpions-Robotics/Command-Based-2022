package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.FixedPosition;
import frc.robot.commands.Autonomous.TakeAim;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class CenteredAndFixed extends SequentialCommandGroup {
  public CenteredAndFixed(DriveSubsystem m_drive, VisionSubsystem m_vision, boolean mode) {
    addCommands(
        new TakeAim(m_drive, m_vision).withTimeout(2.5).andThen(new FixedPosition(m_drive, mode)));
  }
}
