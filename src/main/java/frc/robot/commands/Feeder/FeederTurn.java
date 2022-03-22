package frc.robot.commands.Feeder;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class FeederTurn extends CommandBase {
  ShooterSubsystem m_shooter;
  FeederSubsystem m_feeder;
  double speed;

  public FeederTurn(ShooterSubsystem m_shooter, FeederSubsystem m_feeder, double speed) {
    this.m_feeder = m_feeder;
    this.speed = speed;
    this.m_shooter = m_shooter;
    // addRequirements(m_feeder);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(m_shooter.is_at_right_rpm){
      m_feeder.runFeeder(speed);
    }
    
  }

  @Override
  public void end(boolean interrupted) {
    m_feeder.stopFeeder();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
