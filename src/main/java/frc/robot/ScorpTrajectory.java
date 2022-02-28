// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.subsystems.DriveSubsystem;

/** Add your docs here. */
public class ScorpTrajectory {
    private DriveSubsystem m_drive;

    public Trajectory Blue21 = new Trajectory();
    public Trajectory Blue22 = new Trajectory();
    public Trajectory Blue23 = new Trajectory();
    public Trajectory Red21 = new Trajectory();
    public Trajectory Red22 = new Trajectory();
    public Trajectory Red23 = new Trajectory();

    public Trajectory Blue31 = new Trajectory();
    public Trajectory Blue32 = new Trajectory();
    public Trajectory Red31 = new Trajectory();
    public Trajectory Red32 = new Trajectory();

    public Trajectory[] Blue51 = new Trajectory[2];
    public Trajectory[] Red51 = new Trajectory[2];
    


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

        TrajectoryConfig configBackward =
        new TrajectoryConfig(
                Constants.ODOMETRY.kMaxSpeedMetersPerSecond,
                Constants.ODOMETRY.kMaxAccelerationMetersPerSecondSquared)
            .setKinematics(Constants.ODOMETRY.kinematics)
            .addConstraint(autoVoltageConstraint);
        configBackward.setReversed(true);

        // Path Blue2BallsPosition1 = Filesystem.getDeployDirectory().toPath().resolve("paths/blue2ball1position.wpilib.json");
        // Path Blue2BallsPosition2 = Filesystem.getDeployDirectory().toPath().resolve("paths/blue2ball2position.wpilib.json");
        // Path Blue2BallsPosition3 = Filesystem.getDeployDirectory().toPath().resolve("paths/blue2ball3position.wpilib.json");

        Path Blue3BallsPosition1 = Filesystem.getDeployDirectory().toPath().resolve("paths/Blue3BallsPosition1.wpilib.json");
        Path Blue3BallsPosition2 = Filesystem.getDeployDirectory().toPath().resolve("paths/Blue3BallsPosition2.wpilib.json");

        // Path Blue5BallsPath1 = Filesystem.getDeployDirectory().toPath().resolve("paths/blue5ballpath1.wpilib.json");
        // Path Blue5BallsPath2 = Filesystem.getDeployDirectory().toPath().resolve("paths/blue5ballpath2.wpilib.json");

        // Path Red2BallsPosition1 = Filesystem.getDeployDirectory().toPath().resolve("paths/red2ball1position.wpilib.json");
        // Path Red2BallsPosition2 = Filesystem.getDeployDirectory().toPath().resolve("paths/red2ball2position.wpilib.json");
        // Path Red2BallsPosition3 = Filesystem.getDeployDirectory().toPath().resolve("paths/red2ball3position.wpilib.json");

        // Path Red3BallsPosition1 = Filesystem.getDeployDirectory().toPath().resolve("paths/red3ball1position.wpilib.json");
        // Path Red3BallsPosition2 = Filesystem.getDeployDirectory().toPath().resolve("paths/red3ball2position.wpilib.json");

        // Path Red5BallsPath1 = Filesystem.getDeployDirectory().toPath().resolve("paths/red5ballpath1.wpilib.json");
        // Path Red5BallsPath2 = Filesystem.getDeployDirectory().toPath().resolve("paths/red5ballpath2.wpilib.json");

        try {
            // Blue21 = TrajectoryUtil.fromPathweaverJson(Blue2BallsPosition1);
            // Blue22 = TrajectoryUtil.fromPathweaverJson(Blue2BallsPosition2);
            // Blue23 = TrajectoryUtil.fromPathweaverJson(Blue2BallsPosition3);
            
            Blue31 = TrajectoryUtil.fromPathweaverJson(Blue3BallsPosition1);
            Blue32 = TrajectoryUtil.fromPathweaverJson(Blue3BallsPosition2);

            // Blue51[0] = TrajectoryUtil.fromPathweaverJson(Blue5BallsPath1);
            // Blue51[1] = TrajectoryUtil.fromPathweaverJson(Blue5BallsPath2);

            // Red21 = TrajectoryUtil.fromPathweaverJson(Red2BallsPosition1);
            // Red22 = TrajectoryUtil.fromPathweaverJson(Red2BallsPosition2);
            // Red23 = TrajectoryUtil.fromPathweaverJson(Red2BallsPosition3);
            
            // Red31 = TrajectoryUtil.fromPathweaverJson(Red3BallsPosition1);
            // Red32 = TrajectoryUtil.fromPathweaverJson(Red3BallsPosition2);

            // Red51[0] = TrajectoryUtil.fromPathweaverJson(Red5BallsPath1);
            // Red51[1] = TrajectoryUtil.fromPathweaverJson(Red5BallsPath2);

        } catch (IOException e) {
        e.printStackTrace();
}
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
