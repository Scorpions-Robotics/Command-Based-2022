package frc.robot.commands.DriveTrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import java.util.function.DoubleSupplier;

public class TeleopDrive extends CommandBase {
  DoubleSupplier xSpeed;
  DoubleSupplier zRotation;
  DoubleSupplier throttleSupplier;
  double throttle;

  DriveSubsystem m_drive;

  public TeleopDrive(
      DriveSubsystem m_drive,
      DoubleSupplier xSpeed,
      DoubleSupplier zRotation,
      DoubleSupplier throttleSupplier) {
    this.xSpeed = xSpeed;
    this.zRotation = zRotation;
    this.throttleSupplier = throttleSupplier;
    this.m_drive = m_drive;

    addRequirements(m_drive);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    throttle = (throttleSupplier.getAsDouble() * -1 + 1) / 2;
    SmartDashboard.putNumber("throttle", throttle);
    m_drive.arcadeDrive(throttle * xSpeed.getAsDouble(), throttle * zRotation.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
