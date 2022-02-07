package frc.robot.commandgroups.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Autonomous3Balls extends SequentialCommandGroup {
  public Autonomous3Balls(int position) {
    switch (position) {
      case 1:
        addCommands();
      case 2:
        addCommands();
      case 3:
        addCommands();
    }
  }
}
