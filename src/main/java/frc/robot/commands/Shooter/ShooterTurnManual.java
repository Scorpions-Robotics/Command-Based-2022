// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterTurnManual extends CommandBase {
  ShooterSubsystem m_shooter;
  DoubleSupplier speedSupplier;
  double speed;
  /** Creates a new ShooterTurn1. */
  public ShooterTurnManual(ShooterSubsystem m_shooter, DoubleSupplier speedSupplier) {
    this.m_shooter = m_shooter;
    this.speedSupplier = speedSupplier;
    addRequirements(m_shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    speed = (speedSupplier.getAsDouble() * -1 + 1) / 2;
    m_shooter.runShooter(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.stopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
