package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeGear extends Command {

  public ChangeGear() {
    // Doesn't require drivetrain because change can be made while robot is
    // driving
  }

  @Override
  protected void initialize() {
    Value gear = Robot.driveTrain.getLeftGearPistonValue(); // Gears are assumed
                                                            // to be the same
    Value opposite = (gear == Constants.DriveTrain.HIGH_GEAR) ? Constants.DriveTrain.LOW_GEAR
        : Constants.DriveTrain.HIGH_GEAR;
    Robot.driveTrain.changeGear(opposite);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
