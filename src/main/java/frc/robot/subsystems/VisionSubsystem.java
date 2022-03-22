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
  NetworkTable hoopTable = table.getSubTable("hoop");

  NetworkTableEntry hoopXEntry = hoopTable.getEntry("hoop_X");
  NetworkTableEntry hoopYEntry = hoopTable.getEntry("hoop_Y");
  NetworkTableEntry hoopWEntry = hoopTable.getEntry("hoop_W");
  NetworkTableEntry hoopHEntry = hoopTable.getEntry("hoop_H");
  NetworkTableEntry hoopDEntry = hoopTable.getEntry("hoop_D");
  NetworkTableEntry hoopBEntry = hoopTable.getEntry("hoop_B");
  NetworkTableEntry hoopREntry = hoopTable.getEntry("hoop_R");

  NetworkTableEntry modeEntry = table.getEntry("mode");

  NetworkTableEntry allianceEntry = table.getEntry("alliance");

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

  public double getHoopD() {
    try {
      return getHoopB() == 1 ? Double.valueOf(hoopDEntry.getString("")) : 0.0;
    } catch (Exception e) {
      e.printStackTrace();
      return 0.0;
    }
  }

  public double getHoopB() {
    return Double.valueOf(hoopBEntry.getString("0"));
  }

  public double getHoopR() {
    try {
      return getHoopB() == 1 ? Double.valueOf(hoopREntry.getString("")) : 0.0;
    } catch (Exception e) {
      e.printStackTrace();
      return 0.0;
    }
  }

  public void showDatas() {
    sendMode(mode);
    allianceEntry.setString(DriverStation.getAlliance().toString().toLowerCase());
  }
}
