package org.usfirst.frc.team3501.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Shoot extends CommandGroup {

  public boolean usePhotogate;

  /**
   * Fires catapult, then resets after a pause. If robot is set to use photogate
   * and no ball is detected, nothing happens.
   *
   * Precondition: catapult is in reset position, and ball is loaded in
   * catapult.
   */
  public Shoot() {
    addSequential(new FireCatapult());
    addSequential(new WaitCommand(1.0));
    addSequential(new ResetCatapult());
  }
}
