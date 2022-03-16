package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  public static SendableChooser<Integer> auto_chooser = new SendableChooser<>();

  DigitalInput input = new DigitalInput(1);

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();

    auto_chooser.setDefaultOption("1", 1);
    auto_chooser.addOption("2", 2);
    auto_chooser.addOption("3", 3);
    auto_chooser.addOption("4", 4);
    auto_chooser.addOption("5", 5);
    auto_chooser.addOption("6", 6);

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
  public void testPeriodic() {
    SmartDashboard.putBoolean("test", input.get());
  }
}
