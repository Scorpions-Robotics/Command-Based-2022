

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Autonomous.Autonomous2Balls;
import frc.robot.commands.Autonomous.Autonomous3Balls;
import frc.robot.commands.Autonomous.Autonomous5Balls;
import frc.robot.commands.DriveTrain.TeleopDrive;
import frc.robot.commands.Feeder.FeederTurn;
import frc.robot.commands.Intake.IntakeTurn;
import frc.robot.commands.Shooter.ShooterTurn;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class RobotContainer {

  public static final Joystick stick = new Joystick(Constants.OI.stickId);
  public static final Joystick panel = new Joystick(Constants.OI.panelId);

  private final DriveSubsystem m_drive = new DriveSubsystem();
  private final VisionSubsystem m_vision = new VisionSubsystem();
  private final FeederSubsystem m_feeder = new FeederSubsystem();
  private final ShooterSubsystem m_shooter = new ShooterSubsystem();
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final ClimbSubsystem m_climb = new ClimbSubsystem();

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

  public RobotContainer() {
    m_drive.setDefaultCommand(new TeleopDrive(
      m_drive,
      () -> stick.getRawAxis(1),
      () -> stick.getRawAxis(0),
      stick.getThrottle()
    ));


    configureButtonBindings();
  }


  private void configureButtonBindings() {
    stickButton1.whileActiveContinuous(new RunCommand(() -> m_vision.send_mode(true)));
    stickButton1.whenInactive(new RunCommand(() -> m_vision.send_mode(false)));
    stickButton2.whileHeld(new ShooterTurn(m_shooter, 1));

    stickButton3.whenPressed(new FeederTurn(m_feeder, 1));
    stickButton3.whenReleased(new FeederTurn(m_feeder, 0));

    stickButton4.whenPressed(new FeederTurn(m_feeder, -1));
    stickButton4.whenReleased(new FeederTurn(m_feeder, 0));

    stickButton5.whileHeld(new IntakeTurn(m_intake, 1));
    
    meriba

  }


  public Command getAutonomousCommand(boolean goToTerminal, int position, int ball_count) {
    if(goToTerminal){
      return new Autonomous5Balls(position);
    }
    else{
      if(ball_count == 2){
        return new Autonomous2Balls(position);
      }
      else if(ball_count == 3){
        return new Autonomous3Balls(position);
      }
    }
    return null;
  }
}
