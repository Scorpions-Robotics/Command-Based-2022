package frc.robot.commands.Autonomous;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class AutoAngleTurn extends PIDCommand {
  DriveSubsystem m_drive;

  public AutoAngleTurn(DriveSubsystem m_drive, double angle) {
    super(
        new PIDController(Constants.PID.kP, Constants.PID.kI, Constants.PID.kD),
        () -> m_drive.getGyroAngle(),
        () -> angle,
        output -> {
          m_drive.arcadeDrive(0, output);
        });
    this.m_drive = m_drive;
    m_drive.resetGyro();
    addRequirements(m_drive);
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
