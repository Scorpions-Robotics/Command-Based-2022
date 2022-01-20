

package frc.robot.commands.DriveTrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class TeleopDrive extends CommandBase {
  private DoubleSupplier xSpeed;
  private DoubleSupplier zRotation;
  private double throttle;

  private DriveSubsystem m_drive;

  public TeleopDrive(DriveSubsystem m_drive, DoubleSupplier xSpeed, DoubleSupplier zRotation, double throttle) {
    this.xSpeed = xSpeed;
    this.zRotation = zRotation;
    this.throttle = throttle;
    this.m_drive = m_drive;


    addRequirements(m_drive);
  }


  @Override
  public void initialize() {}


  @Override
  public void execute() {
    throttle = (throttle+1) / 2;
    m_drive.arcadeDrive(throttle*xSpeed.getAsDouble(), zRotation.getAsDouble());
  }


  @Override
  public void end(boolean interrupted) {}


  @Override
  public boolean isFinished() {
    return false;
  }
}
