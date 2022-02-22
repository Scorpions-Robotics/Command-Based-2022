package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.commands.Autonomous.GoTillBlack;
import frc.robot.commands.Autonomous.ZeroLeftEncoder;
import frc.robot.commands.Autonomous.ZeroRightEncoder;
import frc.robot.subsystems.DriveSubsystem;
import java.util.function.BooleanSupplier;

public class RungCenter extends SequentialCommandGroup {
  public RungCenter(DriveSubsystem m_drive, BooleanSupplier supplier) {
    addCommands(
        new GoTillBlack(m_drive)
            .andThen(
                new WaitUntilCommand(supplier)
                    .andThen(new ZeroLeftEncoder(m_drive).andThen(new ZeroRightEncoder(m_drive)))));
  }
}
