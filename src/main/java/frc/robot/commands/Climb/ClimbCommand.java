package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;
import java.util.function.DoubleSupplier;

public class ClimbCommand extends CommandBase {
  ClimbSubsystem m_climb;
  DoubleSupplier speedSupplier;

  public ClimbCommand(ClimbSubsystem m_climb, DoubleSupplier speedSupplier) {
    this.m_climb = m_climb;
    this.speedSupplier = speedSupplier;

    addRequirements(m_climb);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_climb.runClimb(speedSupplier.getAsDouble());
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
