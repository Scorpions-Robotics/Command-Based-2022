package frc.robot.commandgroups.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Autonomous5Balls extends SequentialCommandGroup {
  public Autonomous5Balls(int position){
    switch(position){
      case 1:
        addCommands();
      case 2:
        addCommands();
      case 3:
        addCommands();
    }
  }
}
