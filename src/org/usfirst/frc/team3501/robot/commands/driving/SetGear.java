package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetGear extends Command {
  private DoubleSolenoid.Value gear;

  public SetGear(DoubleSolenoid.Value gear) {
    // Doesn't require drivetrain because change can be made while robot is
    // driving
    this.gear = gear;
  }

  @Override
  protected void initialize() {
    Robot.driveTrain.changeGear(gear);
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
