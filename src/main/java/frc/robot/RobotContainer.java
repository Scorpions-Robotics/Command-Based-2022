package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commandgroups.Shoot;
import frc.robot.commandgroups.Autonomous.TwoBalls.Blue21;
import frc.robot.commands.Autonomous.AutoAngleTurn;
import frc.robot.commands.Autonomous.AutoStraightDrive;
import frc.robot.commands.Autonomous.TakeAim;
import frc.robot.commands.DriveTrain.TeleopDrive;
import frc.robot.commands.Feeder.FeederTurn;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class RobotContainer {

  public static final Joystick stick = new Joystick(Constants.OI.kStickId);
  public static final Joystick panel = new Joystick(Constants.OI.kPanelId);

  public final DriveSubsystem m_drive = new DriveSubsystem();
  public final VisionSubsystem m_vision = new VisionSubsystem();
  public final FeederSubsystem m_feeder = new FeederSubsystem();
  public final ShooterSubsystem m_shooter = new ShooterSubsystem();
  public final IntakeSubsystem m_intake = new IntakeSubsystem();
  public final ClimbSubsystem m_climb = new ClimbSubsystem();
  public final LEDSubsystem m_led = new LEDSubsystem();
  // public final ScorpTrajectory s_trajectory = new ScorpTrajectory(m_drive);

  private final JoystickButton stickButton1 = new JoystickButton(stick, Constants.OI.kButton1);
  private final JoystickButton stickButton2 = new JoystickButton(stick, Constants.OI.kButton2);
  private final JoystickButton stickButton3 = new JoystickButton(stick, Constants.OI.kButton3);
  private final JoystickButton stickButton4 = new JoystickButton(stick, Constants.OI.kButton4);
  private final JoystickButton stickButton5 = new JoystickButton(stick, Constants.OI.kButton5);
  private final JoystickButton stickButton6 = new JoystickButton(stick, Constants.OI.kButton6);
  private final JoystickButton stickButton7 = new JoystickButton(stick, Constants.OI.kButton7);
  private final JoystickButton stickButton8 = new JoystickButton(stick, Constants.OI.kButton8);
  private final JoystickButton stickButton9 = new JoystickButton(stick, Constants.OI.kButton9);
  private final JoystickButton stickButton10 = new JoystickButton(stick, Constants.OI.kButton10);
  private final JoystickButton stickButton11 = new JoystickButton(stick, Constants.OI.kButton11);
  private final JoystickButton stickButton12 = new JoystickButton(stick, Constants.OI.kButton12);

  public RobotContainer() {
    m_drive.setDefaultCommand(
        new TeleopDrive(
            m_drive,
            () -> stick.getRawAxis(0),
            () -> stick.getRawAxis(1),
            () -> stick.getThrottle()));

    // m_climb.setDefaultCommand(new ClimbCommand(m_climb, () -> panel.getRawAxis(3)));
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    // stickButton1.whileHeld(new Shoot(m_drive, m_shooter, m_vision, () -> stick.getRawButton(11), () -> panel.getRawAxis(0), () -> panel.getRawButton(9), () -> stick.getThrottle(), () -> stick.getRawAxis(1)));

    new Trigger(() -> m_feeder.isBallIn())
        .whileActiveContinuous(
            new FeederTurn(m_feeder, 0.7).withInterrupt(() -> m_feeder.getSwitchValue()));
    stickButton1.whenPressed(new InstantCommand(() -> m_led.setAll(255, 255, 255)));
  }

  public Command getAutonomousCommand(int mode) {
    //
    return new Blue21(m_drive, m_feeder, m_intake, m_shooter, m_vision);
  }
}
