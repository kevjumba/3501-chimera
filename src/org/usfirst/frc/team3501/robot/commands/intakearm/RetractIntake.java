package org.usfirst.frc.team3501.robot.commands.intakearm;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RetractIntake extends Command {

  public RetractIntake() {
    requires(Robot.intake);
  }

  @Override
  protected void initialize() {
    Robot.intake.retractIntake();

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
