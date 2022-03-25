package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commandgroups.Autonomous.ThreeBalls.Blue31;
import frc.robot.commandgroups.Autonomous.ThreeBalls.Red31;
import frc.robot.commandgroups.Autonomous.TwoBalls.Blue21;
import frc.robot.commandgroups.Autonomous.TwoBalls.Red21;
import frc.robot.commands.Autonomous.AdjustShooterAngle;
import frc.robot.commands.Autonomous.GoTillBlack;
import frc.robot.commands.Autonomous.TakeAim;
import frc.robot.commands.Climb.ClimbCommand;
import frc.robot.commands.Climb.ModeChange;
import frc.robot.commands.Climb.ToggleClimbPneumatic;
import frc.robot.commands.DriveTrain.TeleopDrive;
import frc.robot.commands.Feeder.FeederTurn;
import frc.robot.commands.Intake.IntakePneumaticPull;
import frc.robot.commands.Intake.IntakePneumaticPush;
import frc.robot.commands.Intake.IntakeTurn;
import frc.robot.commands.Shooter.ShooterTurnNew;
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
  private final JoystickButton stickButton11 = new JoystickButton(stick, Constants.OI.kButton11);
  private final JoystickButton stickButton12 = new JoystickButton(stick, Constants.OI.kButton12);

  private final JoystickButton panelButton8 = new JoystickButton(panel, Constants.OI.kButton8);
  private final JoystickButton panelButton3 = new JoystickButton(panel, Constants.OI.kButton3);
  private final JoystickButton panelButton11 = new JoystickButton(panel, Constants.OI.kButton11);
  private final JoystickButton panelButton5 = new JoystickButton(panel, Constants.OI.kButton5);
  private final JoystickButton panelButton6 = new JoystickButton(panel, Constants.OI.kButton6);
  private final JoystickButton panelButton7 = new JoystickButton(panel, Constants.OI.kButton7);

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
    new Trigger(() -> !m_feeder.isBallIn() && m_intake.pneumaticMode)
        .whenActive(
            new FeederTurn(m_feeder, 1)
                .withInterrupt(() -> m_feeder.getSwitchValue() || panel.getRawButton(10)));

    stickButton1.whileHeld(
        new ShooterTurnNew(
            m_shooter,
            m_vision,
            () -> panel.getRawButton(12),
            () -> panel.getRawAxis(0),
            () -> panel.getRawButton(13)));

    stickButton2.whileHeld(new IntakeTurn(m_intake, -1));
    stickButton3.whileHeld(new FeederTurn(m_feeder, 1));
    stickButton4.whileHeld(new FeederTurn(m_feeder, -1));

    stickButton11.whileHeld(new ClimbCommand(m_climb, 1));
    stickButton12.whileHeld(new ClimbCommand(m_climb, -1));

    panelButton3.whileHeld(new GoTillBlack(m_drive));

    panelButton6.whenPressed(new TakeAim(m_drive, m_vision, m_led));

    panelButton8.whenPressed(new ModeChange(m_climb));

    panelButton5.whenPressed(new ToggleClimbPneumatic(m_climb));

    panelButton11.whenPressed(new IntakePneumaticPush(m_intake));
    panelButton11.whenReleased(new IntakePneumaticPull(m_intake));

    // stickButton8.whenPressed(new AdjustShooterAngle(m_shooter, m_vision));
    panelButton7.whenPressed(new AdjustShooterAngle(m_shooter, m_vision));
  }

  public Command getAutonomousCommand(int mode) {
    String alliance = DriverStation.getAlliance().toString();
    if (alliance == "Blue") {
      switch (mode) {
        case 1:
          return new Blue21(m_drive, m_feeder, m_intake, m_shooter, m_vision, m_led);
        case 2:
          return new Blue31(m_drive, m_feeder, m_intake, m_shooter, m_vision, m_led);
        default:
          return new Blue21(m_drive, m_feeder, m_intake, m_shooter, m_vision, m_led);
      }
    } else {
      switch (mode) {
        case 1:
          return new Red21(m_drive, m_feeder, m_intake, m_shooter, m_vision, m_led);
        case 2:
          return new Red31(m_drive, m_feeder, m_intake, m_shooter, m_vision, m_led);
        default:
          return new Blue21(m_drive, m_feeder, m_intake, m_shooter, m_vision, m_led);
      }
    }
  }
}
