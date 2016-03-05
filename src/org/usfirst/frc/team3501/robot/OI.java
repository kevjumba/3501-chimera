package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commands.driving.SetGear;
import org.usfirst.frc.team3501.robot.commands.driving.ToggleDrive;
import org.usfirst.frc.team3501.robot.commands.intakearm.IntakeContinuous;
import org.usfirst.frc.team3501.robot.commands.shooter.Shoot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
  public static Joystick leftJoystick, rightJoystick;
  public static JoystickButton setHighGear, intake;
  public static JoystickButton shoot;
  public static JoystickButton toggleDrive;

  public OI() {
    leftJoystick = new Joystick(Constants.OI.LEFT_STICK_PORT);
    rightJoystick = new Joystick(Constants.OI.RIGHT_STICK_PORT);

    setHighGear = new JoystickButton(leftJoystick,
        Constants.OI.LEFT_TRIGGER_PORT);
    setHighGear.whenPressed(new SetGear(Constants.DriveTrain.HIGH_GEAR));
    setHighGear.whenReleased(new SetGear(Constants.DriveTrain.LOW_GEAR));

    intake = new JoystickButton(rightJoystick,
        Constants.OI.RIGHT_JOYSTICK_TRIGGER_PORT);
    intake.whileHeld(new IntakeContinuous(0.3)); // takes a PercentVBus speed

    shoot = new JoystickButton(rightJoystick,
        Constants.OI.RIGHT_JOYSTICK_TOP_RIGHT_PORT);
    shoot.whenPressed(new Shoot());

    toggleDrive = new JoystickButton(rightJoystick,
        Constants.OI.RIGHT_JOYSTICK_THUMB_PORT);
    toggleDrive.whenPressed(new ToggleDrive());
  }
}
