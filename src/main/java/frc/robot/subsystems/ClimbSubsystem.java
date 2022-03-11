package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {
  private WPI_VictorSPX climbMotor1 = new WPI_VictorSPX(Constants.CAN.kClimbMotorLeft1ID);
  private WPI_VictorSPX climbMotor2 = new WPI_VictorSPX(Constants.CAN.kClimbMotorLeft2ID);
  private WPI_VictorSPX climbMotor3 = new WPI_VictorSPX(Constants.CAN.kClimbMotorRight1ID);
  private WPI_VictorSPX climbMotor4 = new WPI_VictorSPX(Constants.CAN.kClimbMotorRight2ID);

  MotorControllerGroup left = new MotorControllerGroup(climbMotor1, climbMotor2);
  MotorControllerGroup right = new MotorControllerGroup(climbMotor3, climbMotor4);

  public ClimbSubsystem() {
    climbMotor1.setNeutralMode(NeutralMode.Brake);
    climbMotor2.setNeutralMode(NeutralMode.Brake);
    climbMotor3.setNeutralMode(NeutralMode.Brake);
    climbMotor4.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {}

  public void runClimb(double speed) {
    left.set(speed);
    right.set(speed);
  }

  public void stopClimb() {
    left.set(Constants.VARIABLES.kZero);
    right.set(Constants.VARIABLES.kZero);
  }
}
