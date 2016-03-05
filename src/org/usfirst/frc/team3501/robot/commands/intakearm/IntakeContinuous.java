package org.usfirst.frc.team3501.robot.commands.intakearm;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeContinuous extends Command {
  private double speed;

  public IntakeContinuous(double speed) {
    this.speed = speed;
    requires(Robot.intake);
  }

  @Override
  protected void initialize() {
    Robot.intake.intakeBall(speed);

  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.intake.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
