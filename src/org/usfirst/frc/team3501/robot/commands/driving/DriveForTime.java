package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command drives the robot for the specified time and specified speed. (If
 * a speed is not specified, a default speed is used
 *
 *
 * dependency on subsystems: drivetrain
 *
 * pre-condition: robot is on
 *
 * post condition: robot has driven for the specified amount of time
 */
public class DriveForTime extends Command {

  private final static double DEFAULT_SPEED = 0.5;
  private double speed;
  private double seconds;

  private Timer timer;

  public DriveForTime(double seconds, double speed) {
    this.seconds = seconds;
    this.speed = speed;
  }

  @Override
  protected void initialize() {
    timer = new Timer();
    timer.start();

    Robot.driveTrain.drive(speed, speed);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    if (timer.get() >= seconds)
      return true;
    return false;
  }

  @Override
  protected void end() {
    Robot.driveTrain.stop();
  }

  @Override
  protected void interrupted() {
  }
}
