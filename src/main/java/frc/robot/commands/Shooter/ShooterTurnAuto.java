package frc.robot.commands.Shooter;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class ShooterTurnAuto extends CommandBase {
  ShooterSubsystem m_shooter;
  VisionSubsystem m_vision;
  double output;
  double distance;
  double min_distance;
  double max_distance;
  double min_rpm;
  double max_rpm;
  double motorOutput;
  PIDController controller = new PIDController(1.5143, 0, 0);
  SimpleMotorFeedforward feedforward =
      new SimpleMotorFeedforward(Constants.SHOOTER.kS, Constants.SHOOTER.kV, Constants.SHOOTER.kA);

  public ShooterTurnAuto(ShooterSubsystem m_shooter, VisionSubsystem m_vision) {
    this.m_shooter = m_shooter;
    this.m_vision = m_vision;
    addRequirements(this.m_shooter);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    distance = m_vision.getHoopD();
    if (m_shooter.pneumatic_mode) {
      min_distance = 450;
      max_distance = 850;
      min_rpm = 1300;
      max_rpm = 1425;
    } else {
      min_distance = 140;
      max_distance = 450;
      min_rpm = 800;
      max_rpm = 1125;
    }
    if (m_vision.getHoopB() == 1) {
      output =
          controller.calculate(
              m_shooter.getShooterEncoderRPM(),
              m_shooter.calculateShooterSpeed(
                  distance, min_distance, max_distance, min_rpm, max_rpm));
      motorOutput =
          output
              + feedforward.calculate(
                  m_shooter.calculateShooterSpeed(
                      distance, min_distance, max_distance, min_rpm, max_rpm));
      m_shooter.runShooterVoltage(motorOutput);
    } else {
      m_shooter.runShooter(1);
      m_shooter.required_rpm = 99999;
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_shooter.stopShooter();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
