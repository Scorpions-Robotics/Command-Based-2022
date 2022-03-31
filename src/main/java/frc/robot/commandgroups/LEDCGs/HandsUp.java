package frc.robot.commandgroups.LEDCGs;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.LEDSubsystem.Side;

public class HandsUp extends SequentialCommandGroup {
  LEDSubsystem m_led;
  boolean should_start;

  public HandsUp(LEDSubsystem m_led) {
    this.m_led = m_led;
    addCommands(
        new RunCommand(() -> m_led.setOneSide(Side.LEFT, Color.kBlue), m_led).withTimeout(0.03),
        new RunCommand(() -> m_led.turnOff(), m_led).withTimeout(0.03),
        new RunCommand(() -> m_led.setOneSide(Side.LEFT, Color.kBlue), m_led).withTimeout(0.03),
        new RunCommand(() -> m_led.turnOff(), m_led).withTimeout(0.03),
        new RunCommand(() -> m_led.setOneSide(Side.LEFT, Color.kBlue), m_led).withTimeout(0.03),
        new RunCommand(() -> m_led.setOneSide(Side.RIGHT, Color.kRed), m_led).withTimeout(0.03),
        new RunCommand(() -> m_led.turnOff(), m_led).withTimeout(0.03),
        new RunCommand(() -> m_led.setOneSide(Side.RIGHT, Color.kRed), m_led).withTimeout(0.03),
        new RunCommand(() -> m_led.turnOff(), m_led).withTimeout(0.03),
        new RunCommand(() -> m_led.setOneSide(Side.RIGHT, Color.kRed), m_led).withTimeout(0.03));
  }

  @Override
  public void end(boolean interrupted) {
    m_led.turnOff();
    super.end(interrupted);
  }
}
