// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import java.util.function.DoubleSupplier;

public class ShooterTurnAuto extends CommandBase {
  ShooterSubsystem m_shooter;
  VisionSubsystem m_vision;
  double output;
  double distance;
  double min_distance;
  double max_distance;
  double min_rpm;
  double max_rpm;
  double motorOutput;
  PIDController controller = new PIDController(1.5143, 0, 0);
  SimpleMotorFeedforward feedforward =
      new SimpleMotorFeedforward(Constants.SHOOTER.kS, Constants.SHOOTER.kV, Constants.SHOOTER.kA);
  /** Creates a new ShooterTurnNew. */
  public ShooterTurnAuto(ShooterSubsystem m_shooter, VisionSubsystem m_vision) {
    this.m_shooter = m_shooter;
    this.m_vision = m_vision;
    addRequirements(this.m_shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    distance = m_vision.getHoopD();
    if (m_shooter.pneumatic_mode) {
      min_distance = 450;
      max_distance = 850;
      min_rpm = 1300;
      max_rpm = 1450;
    } else {
      min_distance = 140;
      max_distance = 450;
      min_rpm = 800;
      max_rpm = 1125;
    }
    if (m_vision.getHoopB() == 1) {
      output =
          controller.calculate(
              m_shooter.getShooterEncoderRPM(),
              m_shooter.calculateShooterSpeed(
                  distance, min_distance, max_distance, min_rpm, max_rpm));
      motorOutput =
          output
              + feedforward.calculate(
                  m_shooter.calculateShooterSpeed(
                      distance, min_distance, max_distance, min_rpm, max_rpm));
      m_shooter.runShooterVoltage(motorOutput);
    } else {
      m_shooter.runShooter(-0.7);
    }

    SmartDashboard.putNumber("RPM", m_shooter.getShooterEncoderRPM());
    SmartDashboard.putNumber("Vision", m_vision.getHoopB());
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
