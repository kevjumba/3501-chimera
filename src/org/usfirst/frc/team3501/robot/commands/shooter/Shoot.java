package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {

  public Shoot() {
    requires(Robot.shooter);
  }

  @Override
  protected void initialize() {
    Robot.shooter.fireCatapult();
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
    Robot.shooter.resetCatapult();
    ;
  }

  @Override
  protected void interrupted() {
    end();

  }

}
