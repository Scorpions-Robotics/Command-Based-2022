package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Autonomous.TakeAim;
import frc.robot.commands.DriveTrain.TeleopDrive;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class RobotContainer {

  public static final Joystick stick = new Joystick(Constants.OI.kStickId);
  public static final Joystick panel = new Joystick(Constants.OI.kPanelId);

  public final DriveSubsystem m_drive = new DriveSubsystem();
  public final VisionSubsystem m_vision = new VisionSubsystem();
  // public final FeederSubsystem m_feeder = new FeederSubsystem();
  // public final ShooterSubsystem m_shooter = new ShooterSubsystem();
  // public final IntakeSubsystem m_intake = new IntakeSubsystem();
  // public final ClimbSubsystem m_climb = new ClimbSubsystem();
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
            () -> stick.getRawAxis(1),
            () -> stick.getRawAxis(0),
            () -> stick.getThrottle()));

    // m_climb.setDefaultCommand(new ClimbCommand(m_climb, () -> stick.getRawAxis(2)));
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    // stickButton1.whileActiveContinuous(new RunCommand(() -> m_vision.sendMode("hoop")));
    // stickButton1.whenInactive(new RunCommand(() -> m_vision.sendMode("ball")));
    // stickButton12.whenActive(new RunCommand(() -> m_drive.resetGyro(), m_drive));

    // stickButton1.whileHeld(new Shoot(m_shooter, m_vision));

    // stickButton2.whileHeld(new FeederTurn(m_feeder, 1));

    // stickButton3.whileHeld(new IntakeTurn(m_intake, -1));

    // stickButton4.whileHeld(new FeederTurn(m_feeder, -1));

    // stickButton5.whenActive(new AutoAngleTurn(m_drive, -60));
    // stickButton6.whenActive(new AutoAngleTurn(m_drive, 60));

    stickButton2.whenPressed(new TakeAim(m_drive, m_vision));

    // new Trigger(() -> m_feeder.isBallIn())
    //     .whileActiveContinuous(
    //         new FeederTurn(m_feeder, 0.7).withInterrupt(() -> m_feeder.getSwitchValue()));
    // stickButton4.whileHeld(new FeederTurn(m_feeder, 1));
    // stickButton5.whileHeld(new FeederTurn(m_feeder, -1));
    // stickButton7.whenActive(new IntakePneumaticPush(m_intake));
    // stickButton8.whenActive(new IntakePneumaticPull(m_intake));
    // stickButton10.whenActive(new SetServoAngle(m_shooter, 50));
    // stickButton11.whenActive(new SetServoAngle(m_shooter, 95));
    // stickButton1.whileHeld(new FixedPosition(m_drive, () -> stick.getRawAxis(1), () ->
    // stick.getThrottle()));
  }

  public Command getAutonomousCommand(int mode) {
    //   String alliance = DriverStation.getAlliance().toString();
    //   if (alliance == "Blue") {
    //     switch (mode) {
    //       case 1:
    //         return new Blue21(s_trajectory);
    //       case 2:
    //         return new Blue22(s_trajectory);
    //       case 3:
    //         return new Blue23(s_trajectory);
    //       case 4:
    //         return new Blue31(s_trajectory);
    //       case 5:
    //         return new Blue32(s_trajectory);
    //       default:
    //         return new Blue51(s_trajectory);
    //     }
    //   } else {
    //     switch (mode) {
    //       case 1:
    //         return new Red21(s_trajectory);
    //       case 2:
    //         return new Red22(s_trajectory);
    //       case 3:
    //         return new Red23(s_trajectory);
    //       case 4:
    //         return new Red31(s_trajectory);
    //       case 5:
    //         return new Red32(s_trajectory);
    //       default:
    //         return new Red51(s_trajectory);
    //     }
    //   }
    // }
    return null;
  }
}
