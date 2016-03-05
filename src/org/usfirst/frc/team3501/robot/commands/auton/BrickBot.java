package org.usfirst.frc.team3501.robot.commands.auton;

import org.usfirst.frc.team3501.robot.commands.driving.DriveForTime;
import org.usfirst.frc.team3501.robot.commands.intakearm.DeployIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/*
 * Auton to touch defenses
 */
public class BrickBot extends CommandGroup {

  public BrickBot() {
    addSequential(new DeployIntake());
    addSequential(new WaitCommand(1.0));
    addSequential(new DriveForTime(3.0, 0.3)); // seconds and PercentVBus Speed
  }
}
