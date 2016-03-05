package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
  private DoubleSolenoid catapult1, catapult2;

  public Shooter() {
    catapult1 = new DoubleSolenoid(10,
        Constants.Shooter.CATAPULT1_FORWARD,
        Constants.Shooter.CATAPULT1_REVERSE);
    catapult2 = new DoubleSolenoid(10,
        Constants.Shooter.CATAPULT2_FORWARD,
        Constants.Shooter.CATAPULT2_REVERSE);
  }

  // Catapult Commands
  public void fireCatapult() {
    catapult1.set(Constants.Shooter.shoot);
    catapult2.set(Constants.Shooter.shoot);
  }

  public void resetCatapult() {
    catapult1.set(Constants.Shooter.reset);
    catapult2.set(Constants.Shooter.reset);
  }

  @Override
  protected void initDefaultCommand() {
  }
}
