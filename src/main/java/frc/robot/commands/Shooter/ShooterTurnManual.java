package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class ShooterTurnManual extends CommandBase {
  ShooterSubsystem m_shooter;
  DoubleSupplier throttleSupplier;
  double throttle;

  public ShooterTurnManual(
      ShooterSubsystem m_shooter, DoubleSupplier throttleSupplier) {
    this.m_shooter = m_shooter;
    this.throttleSupplier = throttleSupplier;
    addRequirements(m_shooter);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    throttle = throttleSupplier.getAsDouble();
    m_shooter.runShooter(m_shooter.calculateSpeed(throttle, 0.260, 0.575, 0, 1));
    m_shooter.required_rpm = m_shooter.calculateSpeed(throttle, 0.165, 0.472, 0, 1500);
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
