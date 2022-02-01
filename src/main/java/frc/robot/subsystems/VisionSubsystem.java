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

  NetworkTableEntry xEntry = table.getEntry("X");
  NetworkTableEntry yEntry = table.getEntry("Y");
  NetworkTableEntry wEntry = table.getEntry("W");
  NetworkTableEntry hEntry = table.getEntry("H");
  NetworkTableEntry dEntry = table.getEntry("D");
  NetworkTableEntry bEntry = table.getEntry("B");
  NetworkTableEntry rEntry = table.getEntry("R");

  double x;
  double y;
  double w;
  double h;
  double d;
  double b;
  double r;

  public VisionSubsystem() {
    inst.startClient("10.76.72.10");
  }

  public double getX() {
    try {
      x = Double.valueOf(xEntry.getString(""));
    } catch (Exception e) {
      x = 0.0;
    }
    return x;
  }

  public double getY() {
    try {
      y = Double.valueOf(yEntry.getString(""));
    } catch (Exception e) {
      y = 0.0;
    }
    return y;
  }

  public double getW() {
    try {
      w = Double.valueOf(wEntry.getString(""));
    } catch (Exception e) {
      w = 0.0;
    }
    return w;
  }

  public double getH() {
    try {
      h = Double.valueOf(hEntry.getString(""));
    } catch (Exception e) {
      h = 0.0;
    }
    return h;
  }

  public double getD() {
    try {
      d = Double.valueOf(dEntry.getString(""));
    } catch (Exception e) {
      d = 0.0;
    }
    return d;
  }

  public double getB() {
    try {
      b = Double.valueOf(bEntry.getString(""));
    } catch (Exception e) {
      b = 0.0;
    }
    return b;
  }

  public double getR() {
    try {
      r = Double.valueOf(rEntry.getString(""));
    } catch (Exception e) {
      r = 0.0;
    }
    return r;
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

  public void send_mode(boolean mode) {
    NetworkTableEntry mode_entry = table.getEntry("mode");

    if (mode) {
      mode_entry.setString("1");
    } else {
      mode_entry.setString("0");
    }
  }

  public void send_necessary_datas() {
    show_datas();
    NetworkTableEntry period_entry = table.getEntry("period");
    NetworkTableEntry position_entry = table.getEntry("position");
    NetworkTableEntry alliance_entry = table.getEntry("alliance");

    alliance_entry.setString(DriverStation.getAlliance().toString());
    position_entry.setNumber(DriverStation.getLocation());
    period_entry.setString(getPeriod());
  }

  public void show_datas() {
    SmartDashboard.putNumber("X", getX());
    SmartDashboard.putNumber("Y", getY());
    SmartDashboard.putNumber("W", getW());
    SmartDashboard.putNumber("H", getH());
    SmartDashboard.putNumber("D", getD());
    SmartDashboard.putNumber("B", getB());
    SmartDashboard.putNumber("R", getR());
  }

  @Override
  public void periodic() {
    send_necessary_datas();
  }
}
