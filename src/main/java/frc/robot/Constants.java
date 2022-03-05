package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

public final class Constants {
  public static class OI {
    public static final int kStickId = 0;
    public static final int kPanelId = 1;

    public static final int kButton1 = 1;
    public static final int kButton2 = 2;
    public static final int kButton3 = 3;
    public static final int kButton4 = 4;
    public static final int kButton5 = 5;
    public static final int kButton6 = 6;
    public static final int kButton7 = 7;
    public static final int kButton8 = 8;
    public static final int kButton9 = 9;
    public static final int kButton10 = 10;
    public static final int kButton11 = 11;
    public static final int kButton12 = 12;
  }

  public static class FEEDER {
    public static final int limitPort = 0;
  }

  public static class SHOOTER {
    public static final double kV = 0.065552;
    public static final double kS = 1.5299;
    public static final double kA = 0.03379;
    public static final int kServoPWM = 1;
  }

  public static class CAN {
    public static final int kRightLeaderID = 5;
    public static final int kRightFollowerID = 6;
    public static final int kLeftLeaderID = 1;
    public static final int kLeftFollowerID = 7;

    public static final int kClimbMotorID = 0;
    public static final int kFeederMotorID = 7;

    public static final int kShooterRightMotorID = 2;
    public static final int kShooterLeftMotorID = 8;

    public static final int kIntakeMotorID = 8;
  }

  public static class PNEUMATICS {
    public static final int kIntakeSolenoidForwardChannel = 2;
    public static final int kIntakeSolenoidReverseChannel = 3;

    public static final int kShooterSolenoidForwardChannel = 0;
    public static final int kShooterSolenoidReverseChannel = 1;
  }

  public static class ENCODERS {
    public static final int kLeftDriveEncoderChannelA = 0;
    public static final int kLeftDriveEncoderChannelB = 1;

    public static final int kRightDriveEncoderChannelA = 2;
    public static final int kRightDriveEncoderChannelB = 3;

    public static final int kShooterEncoderChannelA = 8;
    public static final int kShooterEncoderChannelB = 9;
  }

  public static class VARIABLES {
    public static final int kZero = 0;
  }

  public static class ODOMETRY {
    public static final double kP = 0.0;

    public static final double kRamseteB = 2.0;
    public static final double kRamseteZeta = 0.7;

    public static final double kS = 0.0;
    public static final double kV = 0.0;
    public static final double kA = 0.0;

    // gon' modify track width value
    public static final double kTrackwidthMeters = 0.7;
    public static final DifferentialDriveKinematics kinematics =
        new DifferentialDriveKinematics(kTrackwidthMeters);

    // will decide these later
    public static final double kMaxSpeedMetersPerSecond = 0;
    public static final double kMaxAccelerationMetersPerSecondSquared = 0;
  }
}
