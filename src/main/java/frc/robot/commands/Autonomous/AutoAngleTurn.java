// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoAngleTurn extends PIDCommand {
  DriveSubsystem m_drive;
  /** Creates a new AutoAngleTurn. */
  public AutoAngleTurn(DriveSubsystem m_drive, double angle) {
    super(
        // The controller that the command will use
        new PIDController(Constants.PID.kP, Constants.PID.kI, Constants.PID.kD),
        // This should return the measurement
        () -> m_drive.getGyroAngle(),
        // This should return the setpoint (can also be a constant)
        () -> angle,
        // This uses the output
        output -> {
          m_drive.arcadeDrive(0, output);
        });
        this.m_drive = m_drive;
        m_drive.resetGyro();
        addRequirements(m_drive);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.stopMotors();
  }


  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
