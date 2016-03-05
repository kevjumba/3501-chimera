package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Constants.Direction;
import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TurnForTime extends Command {
  private Direction direction;
  private double seconds;
  private Timer timer;
  private double speed;

  public TurnForTime(double seconds, Direction direction, double speed) {
    this.seconds = seconds;
    this.direction = direction;
    this.speed = speed;
  }

  @Override
  protected void initialize() {
    timer = new Timer();
    timer.start();

    if (direction == Direction.RIGHT) {
      // Robot.driveTrain.drive(speed, -speed);
      Robot.driveTrain.arcadeDrive(0, speed);
    } else if (direction == Direction.LEFT) {
      // Robot.driveTrain.drive(-speed, speed);
      Robot.driveTrain.arcadeDrive(0, -speed);
    }
  }

  @Override
  protected void execute() {

  }

  @Override
  protected boolean isFinished() {
    return (timer.get() >= seconds);
  }

  @Override
  protected void end() {
    Robot.driveTrain.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
