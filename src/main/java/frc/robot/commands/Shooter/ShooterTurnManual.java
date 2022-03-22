package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import java.util.function.DoubleSupplier;

public class ShooterTurnManual extends CommandBase {
  ShooterSubsystem m_shooter;
  DoubleSupplier speedSupplier;
  double speed;

  public ShooterTurnManual(ShooterSubsystem m_shooter, DoubleSupplier speedSupplier) {
    this.m_shooter = m_shooter;
    this.speedSupplier = speedSupplier;
    addRequirements(m_shooter);

  }


  @Override
  public void initialize() {}

 
  @Override
  public void execute() {
    speed = (speedSupplier.getAsDouble() * -1 + 1) / 2;
    m_shooter.runShooter(-speed);
  }

  @Override
  public void end(boolean interrupted) {
    m_shooter.stopShooter();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
