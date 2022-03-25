// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.LED;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;

public class LEDCommand extends CommandBase {
  LEDSubsystem m_led;
  Color color;

  public LEDCommand(LEDSubsystem m_led, Color color) {
    this.m_led = m_led;
    this.color = color;
  }

  @Override
  public void initialize() {
    m_led.setAll(color);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    m_led.setAll(Color.kRed);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
