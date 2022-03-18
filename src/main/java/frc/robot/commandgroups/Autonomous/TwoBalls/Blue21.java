package frc.robot.commandgroups.Autonomous.TwoBalls;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commandgroups.ShootAuto;
import frc.robot.commands.Autonomous.AutoAngleTurn;
import frc.robot.commands.Autonomous.AutoStraightDrive;
import frc.robot.commands.Autonomous.TakeAim;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class Blue21 extends SequentialCommandGroup {
  public Blue21(
      DriveSubsystem m_drive,
      FeederSubsystem m_feeder,
      IntakeSubsystem m_intake,
      ShooterSubsystem m_shooter,
      VisionSubsystem m_vision,
      LEDSubsystem m_led) {
    addCommands(
        new InstantCommand(new Runnable() {
            @Override
            public void run() {
                m_intake.pushPneumatic();
                m_intake.runIntake(-1);
            }
        })
            .andThen(new AutoStraightDrive(m_drive, 1.5, false))
            .andThen(new WaitCommand(1.5))
            .andThen(new AutoAngleTurn(m_drive, 180))
            .andThen(
                new ShootAuto(m_shooter, m_vision)
                    .alongWith(
                        new WaitCommand(2)
                            .andThen(new InstantCommand(() -> m_feeder.runFeeder(1))))));
  }
}
