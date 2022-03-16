package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  public static SendableChooser<Integer> auto_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();

    auto_chooser.setDefaultOption("2 top", 1);
    auto_chooser.addOption("3 top pozisyon 1", 2);
    auto_chooser.addOption("3 top pozisyon 2", 3);
    auto_chooser.addOption("4 top", 4);

    SmartDashboard.putData(auto_chooser);
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
    m_robotContainer.m_drive.modeBrake();
    m_autonomousCommand = m_robotContainer.getAutonomousCommand(auto_chooser.getSelected());

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
    SmartDashboard.putString("command", m_autonomousCommand.toString());
  }

  @Override
  public void teleopInit() {
    m_robotContainer.m_led.setAll(Color.kRed);
    m_robotContainer.m_climb.pullPneumatic();
    m_robotContainer.m_drive.modeCoast();
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
