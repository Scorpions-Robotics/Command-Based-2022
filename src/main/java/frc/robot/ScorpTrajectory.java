// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.subsystems.DriveSubsystem;

/** Add your docs here. */
public class ScorpTrajectory {
    private DriveSubsystem m_drive;

    public ScorpTrajectory(DriveSubsystem m_drive){
        this.m_drive = m_drive;

        var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(
                Constants.ODOMETRY.kS, Constants.ODOMETRY.kV, Constants.ODOMETRY.kA),
            Constants.ODOMETRY.kinematics,
            10);

        TrajectoryConfig config =
        new TrajectoryConfig(
                Constants.ODOMETRY.kMaxSpeedMetersPerSecond,
                Constants.ODOMETRY.kMaxAccelerationMetersPerSecondSquared)
            .setKinematics(Constants.ODOMETRY.kinematics)
            .addConstraint(autoVoltageConstraint);
    }
    
    public RamseteCommand ramseteGenerator(Trajectory trajectory){
        return new RamseteCommand(
          trajectory,
          m_drive::getPose,
          new RamseteController(Constants.ODOMETRY.kRamseteB, Constants.ODOMETRY.kRamseteZeta),
          new SimpleMotorFeedforward(Constants.ODOMETRY.kS,
          Constants.ODOMETRY.kV,
          Constants.ODOMETRY.kA),
          Constants.ODOMETRY.kinematics,
          m_drive::getWheelSpeeds,
          new PIDController(Constants.ODOMETRY.kP, 0, 0),
          new PIDController(Constants.ODOMETRY.kP, 0, 0),
          m_drive::tankDriveVolts,
          m_drive);
      }
}
