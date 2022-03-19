package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.AdjustShooterAngle;
import frc.robot.commands.Autonomous.FixedPosition;
import frc.robot.commands.Shooter.ShooterTurnNew;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class Shoot extends SequentialCommandGroup {
  public Shoot(
      DriveSubsystem m_drive,
      ShooterSubsystem m_shooter,
      VisionSubsystem m_vision,
      BooleanSupplier state,
      DoubleSupplier throttle,
      BooleanSupplier pneumatic) {

      addCommands(new ShooterTurnNew(m_shooter, m_vision, state, throttle, pneumatic));
    
  }
}
