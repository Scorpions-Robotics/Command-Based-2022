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

public class ShooterTurnNew extends CommandBase {
  ShooterSubsystem m_shooter;
  VisionSubsystem m_vision;
  double output;
  double distance;
  PIDController controller = new PIDController(1.5143, 0, 0);
  SimpleMotorFeedforward feedforward =
      new SimpleMotorFeedforward(Constants.SHOOTER.kS, Constants.SHOOTER.kV, Constants.SHOOTER.kA);
  /** Creates a new ShooterTurnNew. */
  public ShooterTurnNew(ShooterSubsystem m_shooter, VisionSubsystem m_vision) {
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
    if (distance > 500) {
      m_shooter.pushPneumatic();
    } else {
      m_shooter.pullPneumatic();
    }
    output =
        controller.calculate(
            m_shooter.getShooterEncoderRPM(),
            m_shooter.calculateShooterSpeed(distance, 140, 750, 820, 1450));
    if (m_vision.getHoopB() == 1) {
      double motorOutput =
          output
              + feedforward.calculate(
                  m_shooter.calculateShooterSpeed(distance, 140, 750, 820, 1450));
      m_shooter.runShooterVoltage(-motorOutput);
    } else {
      m_shooter.runShooter(0.7);
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
