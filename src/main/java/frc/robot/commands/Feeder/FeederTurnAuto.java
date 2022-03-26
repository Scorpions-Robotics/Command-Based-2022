package frc.robot.commands.Feeder;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class FeederTurnAuto extends CommandBase {
  FeederSubsystem m_feeder;
  ShooterSubsystem m_shooter;

  public FeederTurnAuto(FeederSubsystem m_feeder, ShooterSubsystem m_shooter) {
    this.m_feeder = m_feeder;
    this.m_shooter = m_shooter;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (m_shooter.required_rpm - 10 < m_shooter.getShooterEncoderRPM()
        && m_shooter.required_rpm + 10 > m_shooter.getShooterEncoderRPM()) {
      m_feeder.runFeeder(1);
    } else {
      m_feeder.runFeeder(0);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
