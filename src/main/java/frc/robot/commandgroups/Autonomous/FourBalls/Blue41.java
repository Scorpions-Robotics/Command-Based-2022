package frc.robot.commandgroups.Autonomous.FourBalls;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commandgroups.AutoDriveWithHeading;
import frc.robot.commandgroups.Shoot;
import frc.robot.commands.Autonomous.AutoAngleTurn;
import frc.robot.commands.Autonomous.AutoStraightDrive;
import frc.robot.commands.Autonomous.TakeAim;
import frc.robot.commands.Feeder.FeederTurn;
import frc.robot.commands.Shooter.ShooterTurnNew;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class Blue41 extends SequentialCommandGroup {
  public Blue41(
      DriveSubsystem m_drive,
      FeederSubsystem m_feeder,
      IntakeSubsystem m_intake,
      ShooterSubsystem m_shooter,
      VisionSubsystem m_vision) {
    addCommands(
        new RunCommand(() -> m_intake.runIntake(1))
            .alongWith(new AutoStraightDrive(m_drive, 1, false))
            .andThen(new AutoAngleTurn(m_drive, 180))
            .andThen(new Shoot(m_shooter, m_vision)).withTimeout(1.5)
            .alongWith(new WaitCommand(0.5)).andThen(new RunCommand(() -> m_feeder.runFeeder(1)))
            .andThen(new WaitCommand(0.5))
            .andThen(new RunCommand(new Runnable() {
              @Override
              public void run() {
                  m_feeder.stopFeeder();
                  m_shooter.stopShooter();
              }
            }))
            .andThen(new AutoDriveWithHeading(m_drive, 3, 45, false))
            .alongWith(new FeederTurn(m_feeder, 0.7)).withInterrupt(() -> m_feeder.getSwitchValue())
            .andThen(new RunCommand(() -> m_intake.stopIntake())
            .andThen(new AutoDriveWithHeading(m_drive, 1, 90, false))
            .andThen(new TakeAim(m_drive, m_vision))
            .andThen(new Shoot(m_shooter, m_vision).withTimeout(1.5))
            .alongWith(new WaitCommand(0.5)).andThen(new RunCommand(() -> m_feeder.runFeeder(1)))));
  }
}
