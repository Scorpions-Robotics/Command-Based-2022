// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.LED.LEDCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class TakeAim extends CommandBase {
  private DriveSubsystem m_drive;
  private VisionSubsystem m_vision;
  private LEDSubsystem m_led;
  double error;
  double rotation;
  double last_value;

  public TakeAim(DriveSubsystem m_drive, VisionSubsystem m_vision, LEDSubsystem m_led) {
    this.m_drive = m_drive;
    this.m_vision = m_vision;
    this.m_led = m_led;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.modeBrake();
    last_value = m_vision.getHoopR();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_vision.getHoopB() == 1) {
      if (!(Math.abs(m_vision.getHoopR() - last_value) > 40)) {
        last_value = m_vision.getHoopR();
      }
      error = last_value;
      SmartDashboard.putNumber("error", error);

      if (error < -10) {
        m_drive.runLeftMotorVoltage(1);
        m_drive.runRightMotorVoltage(1);
        if (error < -50) {
          m_drive.runLeftMotorVoltage(1.5);
          m_drive.runRightMotorVoltage(1.5);
        }
      } else if (error > 10) {
        m_drive.runLeftMotorVoltage(-1);
        m_drive.runRightMotorVoltage(-1);
        if (error > 50) {
          m_drive.runLeftMotorVoltage(-1.5);
          m_drive.runRightMotorVoltage(-1.5);
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
    m_drive.modeCoast();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_vision.getHoopB() == 0) {
      return true;
    }
    if (error >= -10 && error <= 10) {
      new LEDCommand(m_led, Color.kGreen).withTimeout(5).schedule();
      ;
      return true;
    }
    return false;
  }
}
