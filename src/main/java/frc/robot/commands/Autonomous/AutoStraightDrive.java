package frc.robot.commands.Autonomous;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;

public class AutoStraightDrive extends PIDCommand {
  DriveSubsystem m_drive;

  public AutoStraightDrive(DriveSubsystem m_drive, double meters, boolean reversed) {
    super(
        new PIDController(0, 0, 0),
        () -> m_drive.getStraightDriveDistance(),
        () -> reversed ? -meters : meters,
        output -> {
          m_drive.arcadeDrive(output, 0);
        });
    this.m_drive = m_drive;
    m_drive.resetEncoders();
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
