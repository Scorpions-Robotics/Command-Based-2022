// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.AutoAngleTurn;
import frc.robot.commands.Autonomous.AutoStraightDrive;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoDriveWithHeading extends SequentialCommandGroup {
  /** Creates a new AutoDriveWithHeading. */
  public AutoDriveWithHeading(DriveSubsystem m_drive, double meters, double heading, boolean reversed) {
    addCommands(new AutoStraightDrive(m_drive, meters, reversed).andThen(new AutoAngleTurn(m_drive, heading)));
  }
}
