// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public class OI{
        public static final int stickId = 0;
        public static final int panelId = 1;

        public static final int button1 = 1;
        public static final int button2 = 2;
        public static final int button3 = 3;
        public static final int button4 = 4;
        public static final int button5 = 5;
        public static final int button6 = 6;
        public static final int button7 = 7;
        public static final int button8 = 8;
        public static final int button9 = 9;
        public static final int button10 = 10;
        public static final int button11 = 11;
        public static final int button12 = 12;
    }
    public class PID{
        public static final double kP = 0.0;
        public static final double kI = 0.0;
        public static final double kD = 0.0;
    }
    public class CAN{
        public static final int rightLeaderID = 1;
        public static final int rightFollowerID = 2;
        public static final int leftLeaderID = 3;
        public static final int leftFollowerID = 4;

        public static final int climbMotorID = 5;
        public static final int feederMotorID = 6;

        public static final int shooterRightMotorID = 7;
        public static final int shooterLeftMotorID = 8;

        public static final int intakeMotorID = 9;
    }
    public class PNEUMATICS{}
}
