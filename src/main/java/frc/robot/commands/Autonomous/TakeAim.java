

package frc.robot.commands.Autonomous;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class TakeAim extends PIDCommand {
  DriveSubsystem m_drive;
  VisionSubsystem m_vision;

  public TakeAim(DriveSubsystem m_drive, VisionSubsystem m_vision) {
    super(
        new PIDController(Constants.PID.kP, Constants.PID.kI, Constants.PID.kD),
        () -> Double.valueOf(m_vision.getR()),
        () -> 0,
        output -> {
          if(Math.abs(Double.valueOf(m_vision.getR()))>10){
            m_drive.arcadeDrive(0, output);
          }
        });

    addRequirements(m_drive, m_vision);
  }

  @Override
  public boolean isFinished() {
    if(getController().atSetpoint()){
      return true;
    }
    return false;
  }
}
