package frc.robot.commandgroups.Autonomous.FourBalls;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class Red41 extends SequentialCommandGroup {
  public Red41(
      DriveSubsystem m_drive,
      FeederSubsystem m_feeder,
      IntakeSubsystem m_intake,
      ShooterSubsystem m_shooter,
      VisionSubsystem m_vision) {
    addCommands();
  }
}
