// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class TakeAim extends CommandBase {
  private DriveSubsystem m_drive;
  private VisionSubsystem m_vision;
  double error;
  double rotation;

  public TakeAim(DriveSubsystem m_drive, VisionSubsystem m_vision) {
    this.m_drive = m_drive;
    this.m_vision = m_vision;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_vision.getHoopB() == 1){
      rotation = m_vision.getHoopR();
      error = rotation;
      if(error < 0){
        m_drive.runLeftMotor(0.1);
        m_drive.runRightMotor(-0.1);
        if(error < -25){
          m_drive.runLeftMotor(0.15);
          m_drive.runRightMotor(-0.15);
        }
      }
      else if(error > 0){
        m_drive.runLeftMotor(0.1);
        m_drive.runRightMotor(-0.1);
        if(error > 25){
          m_drive.runLeftMotor(0.15);
          m_drive.runRightMotor(-0.15);
        }
      }
      else{
        m_drive.stopMotors();
      }
      if(error >= -5 && error <= 5){
        m_drive.stopMotors();
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_vision.getHoopB() == 1 && error >= -5 && error <= 5){
      return true;
    }
    return false;
  }
}
