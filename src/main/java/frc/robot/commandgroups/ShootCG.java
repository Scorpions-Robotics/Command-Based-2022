package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.AdjustShooterAngle;
import frc.robot.commands.Feeder.FeederTurnAuto;
import frc.robot.commands.Shooter.ShooterTurnManual;
import frc.robot.commands.Shooter.ShooterTurnNew;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class ShootCG extends SequentialCommandGroup {
  public ShootCG(
      ShooterSubsystem m_shooter,
      FeederSubsystem m_feeder,
      VisionSubsystem m_vision,
      BooleanSupplier state,
      DoubleSupplier throttleSupplier) {
    addCommands(
        new AdjustShooterAngle(m_shooter, m_vision)
            .andThen(
                new ConditionalCommand(
                        new ShooterTurnNew(m_shooter, m_vision),
                        new ShooterTurnManual(m_shooter, throttleSupplier),
                        state)
                    .alongWith(new FeederTurnAuto(m_feeder, m_shooter))));
  }
}
