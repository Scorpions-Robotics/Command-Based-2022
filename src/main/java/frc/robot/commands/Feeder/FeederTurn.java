package frc.robot.commands.Feeder;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FeederSubsystem;

public class FeederTurn extends CommandBase {
  FeederSubsystem m_feeder;
  double speed;

  public FeederTurn(FeederSubsystem m_feeder, double speed) {
    this.m_feeder = m_feeder;
    this.speed = speed;

    // addRequirements(m_feeder);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_feeder.runFeeder(speed);
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
