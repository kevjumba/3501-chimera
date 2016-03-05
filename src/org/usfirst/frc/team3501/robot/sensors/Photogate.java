package org.usfirst.frc.team3501.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;

/***
 * The photogate is a pair of IR LED and phototransistor sensor that uses a
 * reflective method to sense the presence of the boulder within the robot's
 * shooting chamber. This class specifically checks for the ball's presence
 * using a threshold of voltages outputted from the phototransistor.
 *
 * @author niyatisriram
 */
public class Photogate extends AnalogInput {

  private double threshold = 1.8;

  /***
   * The constructor inputs the channel of the transistor and the threshold
   * value.
   * The threshold is a specific value, representing the outputted voltage of
   * the phototransistor. This value will be somewhere within the range [0,
   * 4095] Find the value by testing and finding an average value for which the
   * ball is present when the output is greater, and absent when the output is
   * less.
   */
  public Photogate(int channel, int threshold) {
    super(channel);
    this.threshold = threshold;
  }

  /***
   * @return whether the ball is present or not
   */
  public boolean isBallPresent() {
    if (this.getVoltage() > threshold)
      return true;
    else
      return false;

  }

  /***
   * @param threshold
   *          (range [0, 4095])
   */
  public void setThreshold(int threshold) {
    this.threshold = threshold;
  }
}
