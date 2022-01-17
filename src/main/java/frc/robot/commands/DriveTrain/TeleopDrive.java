// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DriveTrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class TeleopDrive extends CommandBase {
  private DoubleSupplier xSpeed;
  private DoubleSupplier zRotation;
  private double throttle;

  private DriveSubsystem driveSubsystem;
  /** Creates a new TeleopDrive. */
  public TeleopDrive(DriveSubsystem driveSubsystem, DoubleSupplier xSpeed, DoubleSupplier zRotation, double throttle) {
    this.xSpeed = xSpeed;
    this.zRotation = zRotation;
    this.throttle = throttle;
    this.driveSubsystem = driveSubsystem;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    throttle = (throttle+1) / 2;
    driveSubsystem.arcadeDrive(throttle*xSpeed.getAsDouble(), zRotation.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
