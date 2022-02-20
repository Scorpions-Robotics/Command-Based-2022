package frc.robot;

public final class Constants {
  public class OI {
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

  public class FEEDER {
    public static final int limitPort = 6;
  }

  public class SHOOTER {
    public static final double kV = 0.0;
    public static final double kS = 0.0;
    public static final double kA = 0.0;
  }

  public class CAN {
    public static final int kRightLeaderID = 1;
    public static final int kRightFollowerID = 2;
    public static final int kLeftLeaderID = 3;
    public static final int kLeftFollowerID = 4;

    public static final int kClimbMotorID = 5;
    public static final int kFeederMotorID = 6;

    public static final int kShooterRightMotorID = 7;
    public static final int kShooterLeftMotorID = 8;

    public static final int kIntakeMotorID = 9;
  }

  public class PNEUMATICS {
    public static final int kIntakeSolenoidForwardChannel = 1;
    public static final int kIntakeSolenoidReverseChannel = 2;

    public static final int kShooterSolenoidForwardChannel = 3;
    public static final int kShooterSolenoidReverseChannel = 4;
  }

  public class ENCODERS {
    public static final int kLeftDriveEncoderChannelA = 0;
    public static final int kLeftDriveEncoderChannelB = 1;

    public static final int kRightDriveEncoderChannelA = 2;
    public static final int kRightDriveEncoderChannelB = 3;

    public static final int kShooterEncoderChannelA = 4;
    public static final int kShooterEncoderChannelB = 5;
  }

  public class VARIABLES {
    public static final int kZero = 0;
  }
}
