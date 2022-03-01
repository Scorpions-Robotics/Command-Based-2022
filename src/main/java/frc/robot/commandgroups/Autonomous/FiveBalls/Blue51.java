// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.Autonomous.FiveBalls;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ScorpTrajectory;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Blue51 extends SequentialCommandGroup {
  public Blue51(ScorpTrajectory s_trajectory) {
    addCommands();
  }
}
