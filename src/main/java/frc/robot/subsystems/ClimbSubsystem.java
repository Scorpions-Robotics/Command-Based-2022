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

  double result;
  double max_min_value_diff;
  double current_min_value_diff;
  double out_difference;
  double divide_value;

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

  public double calculateAnalogValue(double speed, double in_max, double in_min, double out_max, double out_min){
    result = speed - in_min;
    max_min_value_diff = in_max - in_min;

    if(result != 0){
      divide_value = max_min_value_diff / result;
    }

    else{
      return out_min;
    }

    out_difference = out_max - out_min;
    result = out_difference / divide_value;

    return result + out_min;
  }

  public double calculateAnalogValueNew(double speed, double in_max, double in_min){
    if(Math.signum(speed) == 1){
      result = speed / in_max;
    }
    else{
      result = speed / in_min * -1;
    }

    return result;
  }

  public void stopClimb() {
    left.set(Constants.VARIABLES.kZero);
    right.set(Constants.VARIABLES.kZero);
  }
}
