package frc.robot.commandgroups;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.AdjustShooterAngle;
import frc.robot.commands.Autonomous.FixedPosition;
import frc.robot.commands.Autonomous.TakeAim;
import frc.robot.commands.Shooter.ShooterTurnNew;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class Shoot extends SequentialCommandGroup {
  public Shoot(DriveSubsystem m_drive, ShooterSubsystem m_shooter, VisionSubsystem m_vision, BooleanSupplier state, DoubleSupplier throttle, BooleanSupplier pneumatic, DoubleSupplier chassis_speed, DoubleSupplier stickY) {
    if(state.getAsBoolean() == true){
      addCommands(new TakeAim(m_drive, m_vision).andThen(new FixedPosition(m_drive, stickY, chassis_speed)).alongWith
          (new AdjustShooterAngle(m_shooter, m_vision))
              .andThen(new ShooterTurnNew(m_shooter, m_vision, state, throttle, pneumatic)));
    }
    else{
      addCommands(new TakeAim(m_drive, m_vision).andThen(new FixedPosition(m_drive, stickY, chassis_speed)).alongWith
              (new ShooterTurnNew(m_shooter, m_vision, state, throttle, pneumatic)));
    }
  }
}
