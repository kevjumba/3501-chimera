package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleDrive extends Command {
  // toggle drive toggles the drivetrain inversion.
  public ToggleDrive() {
    // no requires because can be done while driving
  }

  @Override
  protected void initialize() {
    Robot.driveTrain.toggleInversion();
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
