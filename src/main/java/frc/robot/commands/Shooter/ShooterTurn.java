// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShooterTurn extends PIDCommand {
  ShooterSubsystem m_shooter;
  VisionSubsystem m_vision;

  public ShooterTurn(ShooterSubsystem m_shooter, VisionSubsystem m_vision) {
    super(
        new PIDController(0, 0, 0),
        () -> m_shooter.getShooterEncoderRPM(),
        () ->
            m_shooter.calculateShooterSpeed(m_vision.getHoopD(), m_vision.getHoopB(), 2, 6, 12, 18),
        output -> {
          m_shooter.runShooter(output);
        },
        m_shooter);
    this.m_shooter = m_shooter;
    this.m_vision = m_vision;
  }

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
