package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.subsystems.Intake;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
  // Operator Interface
  public static OI oi;
  // subsystems
  public static DriveTrain driveTrain;
  public static Intake intake;
  public static Shooter shooter;
  public static Compressor compressor;

  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    shooter = new Shooter();
    intake = new Intake();
    oi = new OI();
    compressor = new Compressor();
  }

  @Override
  public void autonomousInit() {
    compressor.start();
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    compressor.start();
  }

  @Override
  public void teleopPeriodic() {
  }

}
