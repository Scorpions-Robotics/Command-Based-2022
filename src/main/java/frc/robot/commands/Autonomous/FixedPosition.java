package frc.robot.commands.Autonomous;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;
import java.util.function.DoubleSupplier;

public class FixedPosition extends PIDCommand {
  DriveSubsystem m_drive;
  static double throttle;

  public FixedPosition(
      DriveSubsystem m_drive, DoubleSupplier speedSupplier, DoubleSupplier throttleSupplier) {
    super(
        new PIDController(0.04, 0.0015, 0.005),
        () -> m_drive.getGyroAngle(),
        () -> 0,
        output -> {
          throttle = (throttleSupplier.getAsDouble() * -1 + 1) / 2;
          m_drive.arcadeDrive(-output, speedSupplier.getAsDouble() * throttle);
        },
        m_drive);
    this.m_drive = m_drive;
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
    return false;
  }
}
