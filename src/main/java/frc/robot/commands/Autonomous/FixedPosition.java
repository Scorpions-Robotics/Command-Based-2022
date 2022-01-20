// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FixedPosition extends PIDCommand {
  boolean mode;
  DriveSubsystem m_drive;

  public FixedPosition(DriveSubsystem m_drive, boolean mode) {
    super(
        // The controller that the command will use
        new PIDController(Constants.PID.kP, Constants.PID.kI, Constants.PID.kD),
        // This should return the measurement
        () -> m_drive.getLeftEncoderDistance()-m_drive.getRightEncoderDistance(),
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {
          m_drive.arcadeDrive(RobotContainer.stick.getY(), output);
        }, m_drive);
        this.m_drive = m_drive;
        this.mode = mode;
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(mode == false){
      return true;
    }
    return false;
  }
}
