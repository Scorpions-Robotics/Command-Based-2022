package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commandgroups.Autonomous2Balls;
import frc.robot.commandgroups.Autonomous3Balls;
import frc.robot.commandgroups.Autonomous5Balls;
import frc.robot.commands.DriveTrain.TeleopDrive;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class RobotContainer {

  public static final Joystick stick = new Joystick(Constants.OI.kStickId);
  public static final Joystick panel = new Joystick(Constants.OI.kPanelId);

  private final DriveSubsystem m_drive = new DriveSubsystem();
  private final VisionSubsystem m_vision = new VisionSubsystem();
  private final FeederSubsystem m_feeder = new FeederSubsystem();
  private final ShooterSubsystem m_shooter = new ShooterSubsystem();
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final ClimbSubsystem m_climb = new ClimbSubsystem();

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
            m_drive, () -> stick.getRawAxis(1), () -> stick.getRawAxis(0), stick.getThrottle()));

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    // stickButton1.whileActiveContinuous(new RunCommand(() -> m_vision.sendMode(true)));
    // stickButton1.whenInactive(new RunCommand(() -> m_vision.sendMode(false)));

    // stickButton2.whileHeld(new ShooterTurn(m_shooter, 1));

    // stickButton3.whenPressed(new FeederTurn(m_feeder, 1));
    // stickButton3.whenReleased(new FeederTurn(m_feeder, 0));

    // stickButton4.whenPressed(new FeederTurn(m_feeder, -1));
    // stickButton4.whenReleased(new FeederTurn(m_feeder, 0));

    // stickButton5.whileHeld(new IntakeTurn(m_intake, 1));
  }

  public Command getAutonomousCommand(boolean go_to_terminal, int position, int ball_count) {
    if (go_to_terminal) {
      return new Autonomous5Balls(position);
    } else {
      if (ball_count == 2) {
        return new Autonomous2Balls(position);
      } else if (ball_count == 3) {
        return new Autonomous3Balls(position);
      }
    }
    return null;
  }
}
