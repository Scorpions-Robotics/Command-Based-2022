package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbCommand extends CommandBase {
  ClimbSubsystem m_climb;
  double speed;

  public ClimbCommand(ClimbSubsystem m_climb, double speed) {
    this.m_climb = m_climb;
    this.speed = speed;

    addRequirements(m_climb);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (speed > 0) {
      m_climb.runClimbUpwards(speed);
    } else {
      m_climb.runClimbDownwards(speed);
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_climb.stopClimb();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
