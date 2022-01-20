

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  private WPI_VictorSPX intakeMotor = new WPI_VictorSPX(Constants.CAN.intakeMotorID);


  public IntakeSubsystem() {}

  @Override
  public void periodic() {}

  public void runIntake(double speed){
    intakeMotor.set(speed);
  }

  public void stopIntake(){
    intakeMotor.set(Constants.Variables.kZero);
  }
}
