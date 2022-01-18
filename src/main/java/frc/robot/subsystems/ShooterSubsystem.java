// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private WPI_VictorSPX shooterLeftMotor = new WPI_VictorSPX(Constants.CAN.shooterLeftMotorID);
  private WPI_VictorSPX shooterRightMotor = new WPI_VictorSPX(Constants.CAN.shooterRightMotorID);

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    shooterLeftMotor.follow(shooterRightMotor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void runShooter(double speed){
    shooterRightMotor.set(speed);
  }

  public void stopShooters() {
    shooterRightMotor.set(0);
  }
}
