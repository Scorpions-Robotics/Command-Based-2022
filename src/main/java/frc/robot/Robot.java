package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  public static SendableChooser<String> alliance_chooser = new SendableChooser<>();
  public static SendableChooser<Integer> position_chooser = new SendableChooser<>();
  public static SendableChooser<Integer> ball_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();

    alliance_chooser.setDefaultOption("Blue", "blue");
    alliance_chooser.addOption("Red", "red");

    position_chooser.setDefaultOption("1", 1);
    position_chooser.addOption("2", 2);
    position_chooser.addOption("3", 3);

    ball_chooser.setDefaultOption("2", 2);
    ball_chooser.addOption("3", 3);
    ball_chooser.addOption("5", 5);

    SmartDashboard.putData(alliance_chooser);
    SmartDashboard.putData(position_chooser);
    SmartDashboard.putData(ball_chooser);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    m_robotContainer.m_drive.resetEncoders();
    m_robotContainer.m_drive.resetGyro();
    m_autonomousCommand =
        m_robotContainer.getAutonomousCommand(
            alliance_chooser.getSelected(),
            position_chooser.getSelected(),
            ball_chooser.getSelected());

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}
