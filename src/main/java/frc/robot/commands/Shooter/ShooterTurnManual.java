package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class ShooterTurnManual extends CommandBase {
  ShooterSubsystem m_shooter;
  DoubleSupplier throttleSupplier;
  BooleanSupplier pneumatic;
  double throttle;

  public ShooterTurnManual(
      ShooterSubsystem m_shooter, DoubleSupplier throttleSupplier, BooleanSupplier pneumatic) {
    this.m_shooter = m_shooter;
    this.throttleSupplier = throttleSupplier;
    this.pneumatic = pneumatic;
    addRequirements(m_shooter);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (pneumatic.getAsBoolean() == true) {
      m_shooter.pushPneumatic();
    } else {
      m_shooter.pullPneumatic();
    }
    throttle = throttleSupplier.getAsDouble();
    m_shooter.runShooter(m_shooter.calculateSpeed(throttle, 0.165, 0.472, 0, 1));
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
