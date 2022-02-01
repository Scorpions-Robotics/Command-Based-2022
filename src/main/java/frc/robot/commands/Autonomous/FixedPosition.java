package frc.robot.commands.Autonomous;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;

public class FixedPosition extends PIDCommand {
  boolean mode;
  DriveSubsystem m_drive;

  public FixedPosition(DriveSubsystem m_drive, boolean mode) {
    super(
        new PIDController(Constants.PID.kP, Constants.PID.kI, Constants.PID.kD),
        () -> m_drive.getLeftEncoderDistance() - m_drive.getRightEncoderDistance(),
        () -> 0,
        output -> {
          m_drive.arcadeDrive(RobotContainer.stick.getY(), output);
        },
        m_drive);
    this.m_drive = m_drive;
    this.mode = mode;
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.stopMotors();
  }

  @Override
  public boolean isFinished() {
    if (mode == false) {
      return true;
    }
    return false;
  }
}
