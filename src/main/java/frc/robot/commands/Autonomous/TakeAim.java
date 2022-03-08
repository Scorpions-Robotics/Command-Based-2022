package frc.robot.commands.Autonomous;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class TakeAim extends PIDCommand {
  VisionSubsystem m_vision;

  public TakeAim(DriveSubsystem m_drive, VisionSubsystem m_vision) {
    super(
        new PIDController(0.015, 0.0005, 0.0001),
        () -> m_vision.getHoopR(),
        () -> 0,
        output -> {
          if(m_vision.getHoopB() == 0){
            m_drive.arcadeDrive(0, 0.45);
          }
          else{
            if(m_vision.getHoopR() > 10){
              m_drive.runLeftMotor(0.1);
              m_drive.runRightMotor(-0.1);
            }
            else if(m_vision.getHoopR() < -10){
              m_drive.runLeftMotor(-0.1);
              m_drive.runRightMotor(0.1);
            }
          }
          SmartDashboard.putNumber("output", -output);
        });
    this.m_vision = m_vision;
    addRequirements(m_drive, m_vision);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
