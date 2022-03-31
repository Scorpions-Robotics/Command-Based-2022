// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.LEDCGs;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.LEDSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Spiral extends SequentialCommandGroup {
  LEDSubsystem m_led;

  public Spiral(LEDSubsystem m_led, Color color) {
    this.m_led = m_led;
    addCommands(
        new RunCommand(() -> m_led.setOne(0, color), m_led).withTimeout(0.05),
        new RunCommand(() -> m_led.setOne(1, color), m_led).withTimeout(0.05),
        new RunCommand(() -> m_led.setOne(2, color), m_led).withTimeout(0.05),
        new RunCommand(() -> m_led.setOne(3, color), m_led).withTimeout(0.05),
        new RunCommand(() -> m_led.setOne(4, color), m_led).withTimeout(0.05),
        new RunCommand(() -> m_led.setOne(5, color), m_led).withTimeout(0.05),
        new RunCommand(() -> m_led.setOne(6, color), m_led).withTimeout(0.05),
        new RunCommand(() -> m_led.setOne(7, color), m_led).withTimeout(0.05),
        new RunCommand(() -> m_led.setOne(8, color), m_led).withTimeout(0.05),
        new RunCommand(() -> m_led.setOne(9, color), m_led).withTimeout(0.05),
        new RunCommand(() -> m_led.setOne(10, color), m_led).withTimeout(0.05),
        new RunCommand(() -> m_led.setOne(11, color), m_led).withTimeout(0.05));
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    m_led.turnOff();
  }
}
