package frc.robot.commands.Autonomous;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;

public class AutoAngleTurn extends PIDCommand {
  DriveSubsystem m_drive;
  double angle;

  public AutoAngleTurn(DriveSubsystem m_drive, double angle) {
    super(
        new PIDController(0.012, 0.0005, 0.0040),
        () -> m_drive.getGyroAngle(),
        () -> angle,
        output -> {
          if (m_drive.getGyroAngle() > angle) {
            m_drive.arcadeDrive(Math.max(-output, 0.25), 0);
          } else {
            m_drive.arcadeDrive(Math.min(-output, -0.25), 0);
          }
          SmartDashboard.putNumber("Output", -output);
        });
    // getController().setTolerance(0.0825);
    this.m_drive = m_drive;
    this.angle = angle;
    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    m_drive.modeBrake();
    m_drive.resetGyro();
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
