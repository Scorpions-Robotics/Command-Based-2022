package frc.robot.commands.Autonomous;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.estimator.AngleStatistics;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;

public class AutoAngleTurn extends PIDCommand {
  DriveSubsystem m_drive;
  double angle;

  public AutoAngleTurn(DriveSubsystem m_drive, double angle) {
    super(
        new PIDController(0.0125, 0.0015, 0.0050),
        () -> m_drive.getGyroAngle(),
        () -> angle,
        output -> {
          if(m_drive.getGyroAngle() > angle){
            m_drive.arcadeDrive(Math.max(-output, 0.4), 0);
          }
          else{
            m_drive.arcadeDrive(Math.min(-output, -0.4), 0);
          }
          SmartDashboard.putNumber("Output", -output);
        });
    this.m_drive = m_drive;
    this.angle = angle;
    m_drive.resetGyro();
    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
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
