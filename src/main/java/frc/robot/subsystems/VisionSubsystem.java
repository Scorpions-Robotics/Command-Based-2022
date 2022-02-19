package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {

  NetworkTableInstance inst = NetworkTableInstance.create();
  NetworkTable table = inst.getTable("vision");
  NetworkTable ballTable = table.getSubTable("ball");
  NetworkTable hoopTable = table.getSubTable("hoop");

  NetworkTableEntry ballXEntry = ballTable.getEntry("ball_X");
  NetworkTableEntry ballYEntry = ballTable.getEntry("ball_Y");
  NetworkTableEntry ballWEntry = ballTable.getEntry("ball_W");
  NetworkTableEntry ballHEntry = ballTable.getEntry("ball_H");
  NetworkTableEntry ballDEntry = ballTable.getEntry("ball_D");
  NetworkTableEntry ballBEntry = ballTable.getEntry("ball_B");
  NetworkTableEntry ballREntry = ballTable.getEntry("ball_R");

  NetworkTableEntry hoopXEntry = hoopTable.getEntry("hoop_X");
  NetworkTableEntry hoopYEntry = hoopTable.getEntry("hoop_Y");
  NetworkTableEntry hoopWEntry = hoopTable.getEntry("hoop_W");
  NetworkTableEntry hoopHEntry = hoopTable.getEntry("hoop_H");
  NetworkTableEntry hoopDEntry = hoopTable.getEntry("hoop_D");
  NetworkTableEntry hoopBEntry = hoopTable.getEntry("hoop_B");
  NetworkTableEntry hoopREntry = hoopTable.getEntry("hoop_R");

  NetworkTableEntry modeEntry = table.getEntry("mode");

  String mode = "hoop";

  public VisionSubsystem() {
    inst.startClient("10.76.72.10");
    sendMode(mode);
  }

  public void sendMode(String mode) {
    this.mode = mode;
    modeEntry.setString(mode);
  }

  @Override
  public void periodic() {
    showDatas();
  }

  public double getBallD() {return getBallB() == 1 ? Double.valueOf(ballDEntry.getString("")) : 0.0;}

  public double getBallB() {return Double.valueOf(ballBEntry.getString("0"));}

  public double getBallR() {return getBallB() == 1 ? Double.valueOf(ballREntry.getString("")) : 0.0;}

  public double getHoopD() {return getHoopB() == 1 ? Double.valueOf(hoopDEntry.getString("")) : 0.0;}

  public double getHoopB() {return Double.valueOf(hoopBEntry.getString("0"));}

  public double getHoopR(){return getHoopB() == 1 ? Double.valueOf(hoopREntry.getString("")) : 0.0;}

  

  public void showDatas() {
    SmartDashboard.putNumber("Ball D", getBallD());
    SmartDashboard.putNumber("Ball B", getBallB());
    SmartDashboard.putNumber("Ball R", getBallR());

    SmartDashboard.putNumber("Hoop D", getHoopD());
    SmartDashboard.putNumber("Hoop B", getHoopB());
    SmartDashboard.putNumber("Hoop R", getHoopR());
  }
}
