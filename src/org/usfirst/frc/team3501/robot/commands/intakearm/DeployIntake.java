package org.usfirst.frc.team3501.robot.commands.intakearm;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DeployIntake extends Command {

  public DeployIntake() {
    requires(Robot.intake);
  }

  @Override
  protected void initialize() {
    Robot.intake.deploy();

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
