// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class TakeAimNew extends CommandBase {
  private DriveSubsystem m_drive;
  private VisionSubsystem m_vision;
  double error;
  double rotation;
  double increase;

  public TakeAimNew(DriveSubsystem m_drive, VisionSubsystem m_vision) {
    this.m_drive = m_drive;
    this.m_vision = m_vision;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.modeBrake();
    increase = m_vision.getHoopR();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_vision.getHoopB() == 1) {
      error = m_vision.getHoopR();
      if (Math.signum(increase) == 1) {
        error -= Math.abs(increase) / 4;
      } else if (Math.signum(increase) == -1) {
        error += Math.abs(increase) / 4;
      }

      if (error < -20) {
        m_drive.runLeftMotor(-0.15);
        m_drive.runRightMotor(0.15);
        if (error < -50) {
          m_drive.runLeftMotor(-0.17);
          m_drive.runRightMotor(0.17);
        }
      } else if (error > 20) {
        m_drive.runLeftMotor(0.15);
        m_drive.runRightMotor(-0.15);
        if (error > 10) {
          m_drive.runLeftMotor(0.17);
          m_drive.runRightMotor(-0.17);
        }
      } else {
        m_drive.runLeftMotor(0);
        m_drive.runRightMotor(0);
      }
      if (error >= -10 && error <= 10) {
        m_drive.runLeftMotor(0);
        m_drive.runRightMotor(0);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.runLeftMotor(0);
    m_drive.runRightMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (error >= -10 && error <= 10) {
      return true;
    }
    return false;
  }
}
