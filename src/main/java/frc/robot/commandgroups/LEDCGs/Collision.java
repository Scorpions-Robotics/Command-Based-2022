package frc.robot.commandgroups.LEDCGs;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.LEDSubsystem.Rotation;

public class Collision extends SequentialCommandGroup {
  LEDSubsystem m_led;

  public Collision(LEDSubsystem m_led, Color color1, Color color2, int tail) {
    this.m_led = m_led;
    addCommands(
        new RunCommand(() -> m_led.collision(Rotation.TOP_TO_BOTTOM, 0, color1, color2, 1), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(Rotation.TOP_TO_BOTTOM, 1, color1, color2, Math.min(2, tail)), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(Rotation.TOP_TO_BOTTOM, 2, color1, color2, Math.min(3, tail)), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(Rotation.TOP_TO_BOTTOM, 3, color1, color2, Math.min(4, tail)), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(Rotation.TOP_TO_BOTTOM, 4, color1, color2, Math.min(5, tail)), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(Rotation.TOP_TO_BOTTOM, 5, color1, color2, Math.min(6, tail)), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(Rotation.BOTTOM_TO_TOP, 4, color1, color2, 1), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(Rotation.BOTTOM_TO_TOP, 3, color1, color2, Math.min(2, tail)), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(Rotation.BOTTOM_TO_TOP, 2, color1, color2, Math.min(3, tail)), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(Rotation.BOTTOM_TO_TOP, 1, color1, color2, Math.min(4, tail)), m_led).withTimeout(0.2),
        new RunCommand(() -> m_led.collision(Rotation.BOTTOM_TO_TOP, 0, color1, color2, Math.min(5, tail)), m_led).withTimeout(0.2));
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    m_led.turnOff();
  }
}
