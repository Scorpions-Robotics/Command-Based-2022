// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveTrain.TeleopDrive;
import frc.robot.commands.Vision.VisionSendMode;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public final Joystick stick = new Joystick(Constants.OI.stickId);

  private final DriveSubsystem m_drive = new DriveSubsystem();
  private final VisionSubsystem m_vision = new VisionSubsystem();

  private final JoystickButton stickButton1 = new JoystickButton(stick, Constants.OI.button1);
  private final JoystickButton stickButton2 = new JoystickButton(stick, Constants.OI.button1);
  private final JoystickButton stickButton3 = new JoystickButton(stick, Constants.OI.button1);
  private final JoystickButton stickButton4 = new JoystickButton(stick, Constants.OI.button1);
  private final JoystickButton stickButton5 = new JoystickButton(stick, Constants.OI.button1);
  private final JoystickButton stickButton6 = new JoystickButton(stick, Constants.OI.button1);
  private final JoystickButton stickButton7 = new JoystickButton(stick, Constants.OI.button1);
  private final JoystickButton stickButton8 = new JoystickButton(stick, Constants.OI.button1);
  private final JoystickButton stickButton9 = new JoystickButton(stick, Constants.OI.button1);
  private final JoystickButton stickButton10 = new JoystickButton(stick, Constants.OI.button1);
  private final JoystickButton stickButton11 = new JoystickButton(stick, Constants.OI.button1);
  private final JoystickButton stickButton12 = new JoystickButton(stick, Constants.OI.button1);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_drive.setDefaultCommand(new TeleopDrive(
      m_drive,
      () -> stick.getRawAxis(1),
      () -> stick.getRawAxis(0),
      stick.getThrottle()
    ));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    stickButton1.whileActiveContinuous(new VisionSendMode(m_vision, true));
    stickButton1.whenInactive(new VisionSendMode(m_vision, false));


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
