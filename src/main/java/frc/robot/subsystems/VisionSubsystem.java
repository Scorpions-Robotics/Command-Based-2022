package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {

  NetworkTableInstance inst = NetworkTableInstance.create();
  NetworkTable table = inst.getTable("vision");
  NetworkTable ballTable = table.getSubTable("ball");
  NetworkTable hoopTable = table.getSubTable("hoop");

  NetworkTableEntry ballXEntry = ballTable.getEntry("X");
  NetworkTableEntry ballYEntry = ballTable.getEntry("Y");
  NetworkTableEntry ballWEntry = ballTable.getEntry("W");
  NetworkTableEntry ballHEntry = ballTable.getEntry("H");
  NetworkTableEntry ballDEntry = ballTable.getEntry("D");
  NetworkTableEntry ballBEntry = ballTable.getEntry("B");
  NetworkTableEntry ballREntry = ballTable.getEntry("R");

  NetworkTableEntry hoopXEntry = hoopTable.getEntry("X");
  NetworkTableEntry hoopYEntry = hoopTable.getEntry("Y");
  NetworkTableEntry hoopWEntry = hoopTable.getEntry("W");
  NetworkTableEntry hoopHEntry = hoopTable.getEntry("H");
  NetworkTableEntry hoopDEntry = hoopTable.getEntry("D");
  NetworkTableEntry hoopBEntry = hoopTable.getEntry("B");
  NetworkTableEntry hoopREntry = hoopTable.getEntry("R");

  double value;

  public VisionSubsystem() {
    inst.startClient("10.76.72.10");
  }

  public String getPeriod() {
    if (DriverStation.isAutonomous()) {
      return "Autonomous";
    } else if (DriverStation.isTeleop()) {
      return "Teleoperated";
    } else {
      return "None";
    }
  }

  public void sendMode(boolean mode) {
    NetworkTableEntry modeEntry = table.getEntry("mode");

    if (mode) {
      modeEntry.setString("1");
    } else {
      modeEntry.setString("0");
    }
  }

  public double getDouble(NetworkTableEntry entry) {
    try {
      value = Double.valueOf(entry.getString(""));
    } catch (Exception e) {
      value = 0.0;
    }
    return value;
  }

  public void sendNecessaryDatas() {
    showDatas();
    NetworkTableEntry periodEntry = table.getEntry("period");
    NetworkTableEntry positionEntry = table.getEntry("position");
    NetworkTableEntry allianceEntry = table.getEntry("alliance");

    allianceEntry.setString(DriverStation.getAlliance().toString());
    positionEntry.setNumber(DriverStation.getLocation());
    periodEntry.setString(getPeriod());
  }

  @Override
  public void periodic() {
    sendNecessaryDatas();
  }

  public double getBallX() {
    return getDouble(ballXEntry);
  }

  public double getBallY() {
    return getDouble(ballYEntry);
  }

  public double getBallW() {
    return getDouble(ballWEntry);
  }

  public double getBallH() {
    return getDouble(ballHEntry);
  }

  public double getBallD() {
    return getDouble(ballDEntry);
  }

  public double getBallB() {
    return getDouble(ballBEntry);
  }

  public double getBallR() {
    return getDouble(ballREntry);
  }

  public double getHoopX() {
    return getDouble(hoopXEntry);
  }

  public double getHoopY() {
    return getDouble(hoopYEntry);
  }

  public double getHoopW() {
    return getDouble(hoopWEntry);
  }

  public double getHoopH() {
    return getDouble(hoopHEntry);
  }

  public double getHoopD() {
    return getDouble(hoopDEntry);
  }

  public double getHoopB() {
    return getDouble(hoopBEntry);
  }

  public double getHoopR() {
    return getDouble(hoopREntry);
  }

  public void showDatas() {
    SmartDashboard.putNumber("Ball X", getBallX());
    SmartDashboard.putNumber("Ball Y", getBallY());
    SmartDashboard.putNumber("Ball W", getBallW());
    SmartDashboard.putNumber("Ball H", getBallH());
    SmartDashboard.putNumber("Ball D", getBallD());
    SmartDashboard.putNumber("Ball B", getBallB());
    SmartDashboard.putNumber("Ball R", getBallR());

    SmartDashboard.putNumber("Hoop X", getBallX());
    SmartDashboard.putNumber("Hoop Y", getBallY());
    SmartDashboard.putNumber("Hoop W", getBallW());
    SmartDashboard.putNumber("Hoop H", getBallH());
    SmartDashboard.putNumber("Hoop D", getBallD());
    SmartDashboard.putNumber("Hoop B", getBallB());
    SmartDashboard.putNumber("Hoop R", getBallR());
  }
}
