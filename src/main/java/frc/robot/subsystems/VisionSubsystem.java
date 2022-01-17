// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

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

  /** Creates a new VisionSubsystem. */
  public VisionSubsystem() {
    inst.startClient("10.76.72.10");
  }

  public String getX(){
    return xEntry.getString("");
  }

  public String getY(){
    return yEntry.getString("");
  }

  public String getW(){
    return wEntry.getString("");
  }

  public String getH(){
    return hEntry.getString("");
  }

  public String getD(){
    return dEntry.getString("");
  }

  public String getB(){
    return bEntry.getString("");
  }

  public String getR(){
    return rEntry.getString("");
  }

  public String getPeriod(){
    if(DriverStation.isAutonomous()){
      return "Autonomous";
    }
    else if(DriverStation.isTeleop()){
      return "Teleoperated";
    }
    else{
      return "None";
    }
  }

  public void send_mode(boolean mode){
    NetworkTableEntry mode_entry = table.getEntry("mode");

    if(mode){
      mode_entry.setString("1");
    }
    else{
      mode_entry.setString("0");
    }
  }

  public void send_necessary_datas(){
    show_datas();
    NetworkTableEntry period_entry = table.getEntry("period");
    NetworkTableEntry position_entry = table.getEntry("position");
    NetworkTableEntry alliance_entry = table.getEntry("alliance");

    alliance_entry.setString(DriverStation.getAlliance().toString());
    position_entry.setNumber(DriverStation.getLocation());
    period_entry.setString(getPeriod());
  }

  public void show_datas(){
    SmartDashboard.putString("X", getX());
    SmartDashboard.putString("Y", getY());
    SmartDashboard.putString("W", getW());
    SmartDashboard.putString("H", getH());
    SmartDashboard.putString("D", getD());
    SmartDashboard.putString("B", getB());
    SmartDashboard.putString("R", getR());
  }

  @Override
  public void periodic() {
    send_necessary_datas();
  }
}
