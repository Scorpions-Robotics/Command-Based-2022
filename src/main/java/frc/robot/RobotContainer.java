package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.DriveTrain.TeleopDrive;
import frc.robot.commands.Feeder.FeederTurn;
import frc.robot.commands.Intake.IntakePneumaticPull;
import frc.robot.commands.Intake.IntakePneumaticPush;
import frc.robot.commands.Shooter.ShooterTurnManual;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import java.util.List;

public class RobotContainer {

  public static final Joystick stick = new Joystick(Constants.OI.kStickId);
  public static final Joystick panel = new Joystick(Constants.OI.kPanelId);

  private final DriveSubsystem m_drive = new DriveSubsystem();
  private final VisionSubsystem m_vision = new VisionSubsystem();
  private final FeederSubsystem m_feeder = new FeederSubsystem();
  private final ShooterSubsystem m_shooter = new ShooterSubsystem();
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  // private final ClimbSubsystem m_climb = new ClimbSubsystem();

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

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    // stickButton1.whileActiveContinuous(new RunCommand(() -> m_vision.sendMode("hoop")));
    // stickButton1.whenInactive(new RunCommand(() -> m_vision.sendMode("ball")));
    // stickButton12.whenActive(new RunCommand(() -> m_drive.resetGyro(), m_drive));

    stickButton1.whileHeld(new ShooterTurnManual(m_shooter, () -> stick.getThrottle()));

    // stickButton2.whileHeld(new FeederTurn(m_feeder, 1));

    // stickButton3.whileHeld(new IntakeTurn(m_intake, -1));

    // stickButton4.whileHeld(new FeederTurn(m_feeder, -1));

    // stickButton5.whenActive(new AutoAngleTurn(m_drive, -60));
    // stickButton6.whenActive(new AutoAngleTurn(m_drive, 60));

    // sensör için trigger
    new Trigger(() -> stick.getRawButton(3))
        .whileActiveContinuous(
            new FeederTurn(m_feeder, 0.8).withInterrupt(() -> m_feeder.getSwitchValue()));
    stickButton4.whileHeld(new FeederTurn(m_feeder, 1));
    stickButton5.whileHeld(new FeederTurn(m_feeder, -1));
    stickButton7.whenActive(new IntakePneumaticPush(m_intake));
    stickButton8.whenActive(new IntakePneumaticPull(m_intake));
    // stickButton10.whenActive(new SetServoAngle(m_shooter, 50));
    // stickButton11.whenActive(new SetServoAngle(m_shooter, 95));
    // stickButton1.whileHeld(new FixedPosition(m_drive, () -> stick.getRawAxis(1), () ->
    // stick.getThrottle()));
  }

  public Command getAutonomousCommand(boolean go_to_terminal, int position, int ball_count) {
    final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(0.7);

    var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(
                Constants.ODOMETRY.kS, Constants.ODOMETRY.kV, Constants.ODOMETRY.kA),
            kinematics,
            10);

    TrajectoryConfig config =
        new TrajectoryConfig(
                Constants.ODOMETRY.kMaxSpeedMetersPerSecond,
                Constants.ODOMETRY.kMaxAccelerationMetersPerSecondSquared)
            .setKinematics(kinematics)
            .addConstraint(autoVoltageConstraint);

    Trajectory exampleTrajectory =
        TrajectoryGenerator.generateTrajectory(
            new Pose2d(0, 0, new Rotation2d(0)),
            List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
            new Pose2d(3, 0, new Rotation2d(0)),
            config);

    RamseteCommand ramseteCommand =
        new RamseteCommand(
            exampleTrajectory,
            m_drive::getPose,
            new RamseteController(Constants.ODOMETRY.kRamseteB, Constants.ODOMETRY.kRamseteZeta),
            new SimpleMotorFeedforward(
                Constants.ODOMETRY.kS, Constants.ODOMETRY.kV, Constants.ODOMETRY.kA),
            kinematics,
            m_drive::getWheelSpeeds,
            new PIDController(Constants.ODOMETRY.kP, 0, 0),
            new PIDController(Constants.ODOMETRY.kP, 0, 0),
            m_drive::tankDriveVolts,
            m_drive);

    m_drive.resetOdometry(exampleTrajectory.getInitialPose());

    return ramseteCommand.andThen(() -> m_drive.tankDriveVolts(0, 0));
  }
}

// if (go_to_terminal) {
//   return new Autonomous5Balls(position);
// } else {
//   if (ball_count == 2) {
//     return new Autonomous2Balls();
//   } else if (ball_count == 3) {
//     return new Autonomous3Balls(position);
//   }
// }
