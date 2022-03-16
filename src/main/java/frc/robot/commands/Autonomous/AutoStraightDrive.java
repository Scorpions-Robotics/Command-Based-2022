package frc.robot.commands.Autonomous;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;

public class AutoStraightDrive extends PIDCommand {
  DriveSubsystem m_drive;

  public AutoStraightDrive(DriveSubsystem m_drive, double meters, boolean reversed) {
    super(
        new PIDController(0.0165, 0, 0),
        () -> m_drive.getStraightDriveDistance(),
        () -> reversed ? -meters * 100 : meters * 100,
        output -> {
          m_drive.arcadeDrive(0, -output);
          SmartDashboard.putNumber("meters", m_drive.getStraightDriveDistance());
          SmartDashboard.putNumber("setpoint", reversed ? -meters * 100 : meters * 100);
        });
    getController().setTolerance(4);
    this.m_drive = m_drive;
  }

  @Override
  public void initialize() {
    m_drive.resetEncoders();
    m_drive.modeBrake();
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
