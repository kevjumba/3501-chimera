package org.usfirst.frc.team3501.robot.commands.intakearm;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Expel extends Command {
  private double speed;

  public Expel(double timeout, double speed) {
    super(timeout);
    this.speed = speed;
    requires(Robot.intake);
  }

  @Override
  protected void initialize() {
    Robot.intake.expelBall(speed);

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
