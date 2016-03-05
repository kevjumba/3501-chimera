package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {

  private CANTalon intakeRoller;
  private DoubleSolenoid leftIntake, rightIntake;

  public Intake() {
    intakeRoller = new CANTalon(Constants.Intake.ROLLER_PORT);
    leftIntake = new DoubleSolenoid(Constants.Intake.LEFT_FORWARD,
        Constants.Intake.LEFT_REVERSE);
    rightIntake = new DoubleSolenoid(Constants.Intake.RIGHT_FORWARD,
        Constants.Intake.RIGHT_REVERSE);
  }

  public void deploy() {
    leftIntake.set(DoubleSolenoid.Value.kForward);
    rightIntake.set(DoubleSolenoid.Value.kForward);
  }

  public void retractIntake() {
    leftIntake.set(DoubleSolenoid.Value.kReverse);
    rightIntake.set(DoubleSolenoid.Value.kReverse);
  }

  public void intakeBall(double speed) {
    intakeRoller.set(speed);
  }

  public void expelBall(double speed) {
    intakeRoller.set(-speed);
  }

  @Override
  protected void initDefaultCommand() {
  }

  public void stop() {
    intakeRoller.set(0);
  }
}
