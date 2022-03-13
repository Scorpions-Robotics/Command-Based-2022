package frc.robot.commandgroups.Autonomous.TwoBalls;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commandgroups.Shoot;
import frc.robot.commands.Autonomous.AutoAngleTurn;
import frc.robot.commands.Autonomous.AutoStraightDrive;
import frc.robot.commands.Autonomous.TakeAim;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class Red23 extends SequentialCommandGroup {
  public Red23(
      DriveSubsystem m_drive,
      FeederSubsystem m_feeder,
      IntakeSubsystem m_intake,
      ShooterSubsystem m_shooter,
      VisionSubsystem m_vision) {
    addCommands(
        new RunCommand(() -> m_intake.runIntake(1))
            .andThen(new AutoStraightDrive(m_drive, 1, false))
            .andThen(new WaitCommand(0.2))
            .andThen(new AutoAngleTurn(m_drive, 180))
            .andThen(new TakeAim(m_drive, m_vision))
            .andThen(
                new Shoot(m_shooter, m_vision)
                    .alongWith(
                        new WaitCommand(1).andThen(new RunCommand(() -> m_feeder.runFeeder(1))))));
  }
}
