// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;

public class ZeroLeftEncoder extends PIDCommand {

  public ZeroLeftEncoder(DriveSubsystem m_drive) {
    super(
        new PIDController(0, 0, 0),
        () -> m_drive.getLeftEncoderDistance(),
        () -> 0,
        output -> {
          m_drive.runLeftMotor(output);
        });
  }

  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
