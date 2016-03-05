package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * The Constants stores constant values for all subsystems. This includes the
 * port values for motors and sensors, as well as important operational
 * constants for subsystems such as max and min values.
 */

public class Constants {
  public static class OI {
    // Computer Ports
    public final static int LEFT_STICK_PORT = 0;
    public final static int RIGHT_STICK_PORT = 1;

    public final static int LEFT_TRIGGER_PORT = 1;
    public final static int LEFT_JOYSTICK_TOP_LEFT_BUTTON = 4;
    public final static int LEFT_JOYSTICK_TOP_RIGHT_BUTTON = 5;
    public final static int LEFT_JOYSTICK_TOP_CENTER_PORT = 3;
    public final static int LEFT_JOYSTICK_TOP_LOW_PORT = 2;

    public final static int LEFT_JOYSTICK_BOTTOM_BACK_LEFT_BUTTON = 8;
    public final static int LEFT_JOYSTICK_BOTTOM_BACK_RIGHT_BUTTON = 9;
    public final static int LEFT_JOYSTICK_BOTTOM_LEFT_FORWARD_BUTTON = 6;
    public final static int LEFT_JOYSTICK_BOTTOM_LEFT_BACK_BUTTON = 7;
    public final static int LEFT_JOYSTICK_BOTTOM_RIGHT_FORWARD_BUTTON = 11;
    public final static int LEFT_JOYSTICK_BOTTOM_RIGHT_BACK_BUTTON = 10;

    public final static int RIGHT_JOYSTICK_TRIGGER_PORT = 1;
    public final static int RIGHT_JOYSTICK_THUMB_PORT = 2;
    public final static int RIGHT_JOYSTICK_TOP_RIGHT_PORT = 3;
    public final static int TOGGLE_SCALING_PORT = 0;
    public final static int TOGGLE_COMPRESSOR_PORT = 12;

  }

  public static class DriveTrain {
    // Drivetrain Motor Related Ports
    public static final int FRONT_LEFT = 1;
    public static final int FRONT_RIGHT = 6;
    public static final int REAR_LEFT = 2;
    public static final int REAR_RIGHT = 5;

    // Encoder related ports
    public final static int ENCODER_LEFT_A = 0;
    public final static int ENCODER_LEFT_B = 1;
    public final static int ENCODER_RIGHT_A = 9;
    public final static int ENCODER_RIGHT_B = 8;

    public final static int FORWARD_CHANNEL = 0;
    public final static int REVERSE_CHANNEL = 1;

    public static final double INCHES_PER_PULSE = ((3.66 / 5.14) * 6 * Math.PI) / 256;

    public static double kp = 0.013, ki = 0.000015, kd = -0.002;
    public static double gp = 0.018, gi = 0.000015, gd = 0;
    public static double encoderTolerance = 8.0, gyroTolerance = 5.0;

    public static final int MANUAL_MODE = 1, ENCODER_MODE = 2, GYRO_MODE = 3;

    public static final int MODULE_A_ID = 9, MODULE_B_ID = 10;

    // Gearing solenoid ports

    public static final int LEFT_FORWARD = 1, LEFT_REVERSE = 5,
        RIGHT_FORWARD = 0, RIGHT_REVERSE = 6;

    // Gearing constants
    public static final Value HIGH_GEAR = DoubleSolenoid.Value.kForward;
    public static final Value LOW_GEAR = DoubleSolenoid.Value.kReverse;
  }

  public static class Scaler {
  }

  public static class Shooter {
    public static final int CATAPULT1_FORWARD = 0;
    public static final int CATAPULT1_REVERSE = 1;
    public static final int CATAPULT2_FORWARD = 2;
    public static final int CATAPULT2_REVERSE = 3;// TODO Determine actual
    // shooter ports

    public static final Value shoot = Value.kForward;
    public static final Value reset = Value.kReverse;
    public static final double WAIT_TIME = 2.0; // In seconds
  }

  public static class Intake {
    public static final int ROLLER_PORT = 0;
    public static final int LEFT_FORWARD = 0;
    public static final int LEFT_REVERSE = 1;
    public static final int RIGHT_FORWARD = 2;
    public static final int RIGHT_REVERSE = 3;
  }

  public static class DefenseArm {
  }

  public static class Auton {
  }

  public enum Direction {
    UP, DOWN, RIGHT, LEFT, FORWARD, BACKWARD;
  }

  public enum Defense {
    PORTCULLIS, SALLY_PORT, ROUGH_TERRAIN, LOW_BAR, CHEVAL_DE_FRISE, DRAWBRIDGE, MOAT, ROCK_WALL, RAMPART;
  }
}
