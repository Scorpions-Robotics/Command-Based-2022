package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.AutoAngleTurn;
import frc.robot.commands.Autonomous.AutoStraightDrive;
import frc.robot.subsystems.DriveSubsystem;

public class AutoDriveWithHeading extends SequentialCommandGroup {
  public AutoDriveWithHeading(
      DriveSubsystem m_drive, double meters, double heading, boolean reversed) {
    addCommands(
        new AutoAngleTurn(m_drive, heading)
            .andThen(new AutoStraightDrive(m_drive, meters, reversed)));
  }
}
