package frc.robot.commandgroups.LEDCGs;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.LEDSubsystem;

public class Collision extends SequentialCommandGroup {
  LEDSubsystem m_led;

  public Collision(LEDSubsystem m_led, Color color1, Color color2) {
    this.m_led = m_led;
    addCommands(
        new RunCommand(() -> m_led.collision(0, color1, color2), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(1, color1, color2), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(2, color1, color2), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(3, color1, color2), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(4, color1, color2), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(5, color1, color2), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(4, color1, color2), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(3, color1, color2), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(2, color1, color2), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(1, color1, color2), m_led).withTimeout(0.2));
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    m_led.turnOff();
  }
}
