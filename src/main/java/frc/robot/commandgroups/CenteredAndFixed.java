// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.FixedPosition;
import frc.robot.commands.Autonomous.TakeAim;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CenteredAndFixed extends SequentialCommandGroup {
  public CenteredAndFixed(DriveSubsystem m_drive, VisionSubsystem m_vision, boolean mode) {
    addCommands(new TakeAim(m_drive, m_vision).withTimeout(4).andThen(new FixedPosition(m_drive, mode)));
  }
}
