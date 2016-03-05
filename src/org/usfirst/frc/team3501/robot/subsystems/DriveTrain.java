package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;
import org.usfirst.frc.team3501.robot.commands.driving.JoystickDrive;
import org.usfirst.frc.team3501.robot.sensors.GyroLib;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class DriveTrain extends PIDSubsystem {
  // Current Drive Mode Default Drive Mode is Manual
  private int DRIVE_MODE = 1;
  private static double pidOutput = 0;

  private Encoder leftEncoder, rightEncoder;

  private CANTalon frontLeft, frontRight, rearLeft, rearRight;
  private RobotDrive robotDrive;
  private DoubleSolenoid leftGearPiston, rightGearPiston;

  private GyroLib gyro;

  public DriveTrain() {
    super(Constants.DriveTrain.kp, Constants.DriveTrain.ki,
        Constants.DriveTrain.kd);

    frontLeft = new CANTalon(Constants.DriveTrain.FRONT_LEFT);
    frontRight = new CANTalon(Constants.DriveTrain.FRONT_RIGHT);
    rearLeft = new CANTalon(Constants.DriveTrain.REAR_LEFT);
    rearRight = new CANTalon(Constants.DriveTrain.REAR_RIGHT);

    robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);

    leftEncoder = new Encoder(Constants.DriveTrain.ENCODER_LEFT_A,
        Constants.DriveTrain.ENCODER_LEFT_B, false, EncodingType.k4X);
    rightEncoder = new Encoder(Constants.DriveTrain.ENCODER_RIGHT_A,
        Constants.DriveTrain.ENCODER_RIGHT_B, false, EncodingType.k4X);
    leftEncoder.setDistancePerPulse(Constants.DriveTrain.INCHES_PER_PULSE);
    rightEncoder.setDistancePerPulse(Constants.DriveTrain.INCHES_PER_PULSE);

    leftEncoder.setDistancePerPulse(Constants.DriveTrain.INCHES_PER_PULSE);
    rightEncoder.setDistancePerPulse(Constants.DriveTrain.INCHES_PER_PULSE);

    gyro = new GyroLib(I2C.Port.kOnboard, false);

    DRIVE_MODE = Constants.DriveTrain.ENCODER_MODE;
    setEncoderPID();
    this.disable();

    leftGearPiston = new DoubleSolenoid(Constants.DriveTrain.MODULE_B_ID,
        Constants.DriveTrain.LEFT_FORWARD, Constants.DriveTrain.LEFT_REVERSE);
    rightGearPiston = new DoubleSolenoid(Constants.DriveTrain.MODULE_B_ID,
        Constants.DriveTrain.RIGHT_FORWARD, Constants.DriveTrain.RIGHT_REVERSE);

  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new JoystickDrive());
  }

  // Print tne PID Output
  public void printOutput() {
    System.out.println("PIDOutput: " + pidOutput);
  }

  private double getAvgEncoderDistance() {
    return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
  }

  // Whether or not the PID Controller thinks we have reached the target
  // setpoint
  public boolean reachedTarget() {
    if (this.onTarget()) {
      this.disable();
      return true;
    } else {
      return false;
    }
  }

  public void stop() {
    drive(0, 0);
  }

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public double getRightSpeed() {
    return rightEncoder.getRate(); // in inches per second
  }

  public double getLeftSpeed() {
    return leftEncoder.getRate(); // in inches per second
  }

  public double getSpeed() {
    return (getLeftSpeed() + getRightSpeed()) / 2.0; // in inches per second
  }

  public double getRightDistance() {
    return rightEncoder.getDistance(); // in inches
  }

  public double getLeftDistance() {
    return leftEncoder.getDistance(); // in inches
  }

  // Get error between the setpoint of PID Controller and the current state of
  // the robot
  public double getError() {
    if (DRIVE_MODE == Constants.DriveTrain.ENCODER_MODE)
      return Math.abs(this.getSetpoint() - getAvgEncoderDistance());
    else
      return Math.abs(this.getSetpoint() + getGyroAngle());
  }

  public double getGyroAngle() {
    return gyro.getRotationZ().getAngle();
  }

  public void resetGyro() {
    gyro.reset();
  }

  /*
   * returns the PID output that is returned by the PID Controller
   */
  public double getOutput() {
    return pidOutput;
  }

  // Updates the PID constants based on which control mode is being used
  public void updatePID() {
    if (DRIVE_MODE == Constants.DriveTrain.ENCODER_MODE)
      this.getPIDController().setPID(Constants.DriveTrain.kp,
          Constants.DriveTrain.ki, Constants.DriveTrain.kd);
    else
      this.getPIDController().setPID(Constants.DriveTrain.gp,
          Constants.DriveTrain.gd, Constants.DriveTrain.gi);
  }

  public CANTalon getFrontLeft() {
    return frontLeft;
  }

  public CANTalon getFrontRight() {
    return frontRight;
  }

  public CANTalon getRearLeft() {
    return rearLeft;
  }

  public CANTalon getRearRight() {
    return rearRight;
  }

  public int getMode() {
    return DRIVE_MODE;
  }

  /*
   * Method is a required method that the PID Subsystem uses to return the
   * calculated PID value to the driver
   * 
   * @param Gives the user the output from the PID algorithm that is calculated
   * internally
   * 
   * Body: Uses the output, does some filtering and drives the robot
   */
  @Override
  protected void usePIDOutput(double output) {
    double left = 0;
    double right = 0;
    if (DRIVE_MODE == Constants.DriveTrain.ENCODER_MODE) {
      double drift = this.getLeftDistance() - this.getRightDistance();
      if (Math.abs(output) > 0 && Math.abs(output) < 0.3)
        output = Math.signum(output) * 0.3;
      left = output;
      right = output + drift * Constants.DriveTrain.kp / 10;
    } else if (DRIVE_MODE == Constants.DriveTrain.GYRO_MODE) {
      left = output;
      right = -output;
    }
    drive(left, right);
    pidOutput = output;
  }

  @Override
  protected double returnPIDInput() {
    return sensorFeedback();
  }

  /*
   * Checks the drive mode
   * 
   * @return the current state of the robot in each state Average distance from
   * both sides of tank drive for Encoder Mode Angle from the gyro in GYRO_MODE
   */
  private double sensorFeedback() {
    if (DRIVE_MODE == Constants.DriveTrain.ENCODER_MODE)
      return getAvgEncoderDistance();
    else if (DRIVE_MODE == Constants.DriveTrain.GYRO_MODE)
      return -this.getGyroAngle();
    // counterclockwise is positive on joystick but we want it to be negative
    else
      return 0;
  }

  /*
   * @param left and right setpoints to set to the left and right side of tank
   * inverted is for Logan, wants the robot to invert all controls left = right
   * and right = left negative input is required for the regular rotation
   * because RobotDrive tankdrive method drives inverted
   */
  public void drive(double left, double right) {
    robotDrive.tankDrive(-left, -right);
  }

  public void arcadeDrive(double y, double twist) {
    robotDrive.arcadeDrive(y, twist);
  }

  /*
   * constrains the distance to within -100 and 100 since we aren't going to
   * drive more than 100 inches
   * 
   * Configure Encoder PID
   * 
   * Sets the setpoint to the PID subsystem
   */
  public void driveDistance(double dist, double maxTimeOut) {
    dist = MathLib.constrain(dist, -100, 100);
    setEncoderPID();
    setSetpoint(dist);
  }

  /*
   * Sets the encoder mode Updates the PID constants sets the tolerance and sets
   * output/input ranges Enables the PID controllers
   */
  public void setEncoderPID() {
    DRIVE_MODE = Constants.DriveTrain.ENCODER_MODE;
    this.updatePID();
    this.setAbsoluteTolerance(Constants.DriveTrain.encoderTolerance);
    this.setOutputRange(-1.0, 1.0);
    this.setInputRange(-200.0, 200.0);
    this.enable();
  }

  /*
   * Sets the Gyro Mode Updates the PID constants, sets the tolerance and sets
   * output/input ranges Enables the PID controllers
   */
  private void setGyroPID() {
    DRIVE_MODE = Constants.DriveTrain.GYRO_MODE;
    this.updatePID();
    this.getPIDController().setPID(Constants.DriveTrain.gp,
        Constants.DriveTrain.gi, Constants.DriveTrain.gd);

    this.setAbsoluteTolerance(Constants.DriveTrain.gyroTolerance);
    this.setOutputRange(-1.0, 1.0);
    this.setInputRange(-360.0, 360.0);
    this.enable();
  }

  /*
   * Turning method that should be used repeatedly in a command
   * 
   * First constrains the angle to within -360 and 360 since that is as much as
   * we need to turn
   * 
   * Configures Gyro PID and sets the setpoint as an angle
   */
  public void turnAngle(double angle) {
    angle = MathLib.constrain(angle, -360, 360);
    setGyroPID();
    setSetpoint(angle);
  }

  public void setMotorSpeeds(double left, double right) {
    // positive setpoint to left side makes it go backwards
    // positive setpoint to right side makes it go forwards.
    frontLeft.set(-left);
    rearLeft.set(-left);
    frontRight.set(right);
    rearRight.set(right);
  }

  /*
   * @return a value that is the current setpoint for the piston kReverse or
   * kForward
   */
  public Value getLeftGearPistonValue() {
    return leftGearPiston.get();
  }

  /*
   * @return a value that is the current setpoint for the piston kReverse or
   * kForward
   */
  public Value getRightGearPistonValue() {
    return rightGearPiston.get();
  }

  /*
   * Changes the ball shift gear assembly to high
   */
  public void setHighGear() {
    changeGear(Constants.DriveTrain.HIGH_GEAR);
  }

  /*
   * Changes the ball shift gear assembly to low
   */
  public void setLowGear() {
    changeGear(Constants.DriveTrain.LOW_GEAR);
  }

  /*
   * changes the gear to a DoubleSolenoid.Value
   */
  public void changeGear(DoubleSolenoid.Value gear) {
    leftGearPiston.set(gear);
    rightGearPiston.set(gear);
  }

}
