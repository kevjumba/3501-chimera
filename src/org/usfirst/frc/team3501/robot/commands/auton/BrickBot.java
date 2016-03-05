package org.usfirst.frc.team3501.robot.commands.auton;

import org.usfirst.frc.team3501.robot.commands.driving.DriveForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/*
 * Auton to touch defenses
 */
public class BrickBot extends CommandGroup {

  public BrickBot() {
    addSequential(new DriveForTime(3.0, 0.3)); // seconds and PercentVBus Speed
  }

}
