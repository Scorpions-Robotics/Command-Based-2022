package frc.robot.commands.Shooter;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.commands.LED.LEDCommand;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class ShooterTurnNew extends CommandBase {
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
  BooleanSupplier state;
  DoubleSupplier throttle;
  BooleanSupplier pneumatic;

  public ShooterTurnNew(
      ShooterSubsystem m_shooter,
      VisionSubsystem m_vision,
      BooleanSupplier state,
      DoubleSupplier throttle,
      BooleanSupplier pneumatic) {
    this.m_shooter = m_shooter;
    this.m_vision = m_vision;
    this.state = state;
    this.throttle = throttle;
    this.pneumatic = pneumatic;
    addRequirements(this.m_shooter);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (state.getAsBoolean() == true) {
      distance = m_vision.getHoopD();
      if (m_shooter.pneumatic_mode) {
        min_distance = 450;
        max_distance = 850;
        min_rpm = 1125;
        max_rpm = 1575;
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
        m_shooter.runShooter(0.7);
      }
    } else {
      if (pneumatic.getAsBoolean() == true) {
        m_shooter.pushPneumatic();
      } else {
        m_shooter.pullPneumatic();
      }
      m_shooter.runShooter(m_shooter.calculateSpeed(throttle.getAsDouble(), 0.165, 0.472, 0, 1));
    }

    // if(controller.getSetpoint() - 50 < m_shooter.getShooterEncoderRPM() && controller.getSetpoint() + 50 > m_shooter.getShooterEncoderRPM()){
    //   new LEDCommand(m_led, Color.kAliceBlue).withTimeout(3).schedule();
    // }
    SmartDashboard.putNumber("rpm", m_shooter.getShooterEncoderRPM());
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
