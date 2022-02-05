package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {
  private WPI_VictorSPX climbMotor = new WPI_VictorSPX(Constants.CAN.kClimbMotorID);

  public ClimbSubsystem() {}

  @Override
  public void periodic() {}

  public void runClimb(double speed) {
    climbMotor.set(speed);
  }

  public void stopClimb() {
    climbMotor.set(Constants.VARIABLES.kZero);
  }
}
