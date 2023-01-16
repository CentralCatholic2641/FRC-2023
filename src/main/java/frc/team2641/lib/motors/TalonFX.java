// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.lib.motors;

import java.util.ArrayList;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class TalonFX {
  public static TalonFX instance = null;
  private static ArrayList<TalonFX> instances = new ArrayList<TalonFX>();

  private int id;
  private WPI_TalonFX talonFX = null;

  /**
   * Returns a prexisting instance of a Talon FX-controlled motor, or creates and
   * returns a new one
   * 
   * @return instance of TalonFX
   */
  public static TalonFX getInstance(int id) {
    TalonFX instance = null;
    for (TalonFX i : instances) {
      if (i.getID() == id) {
        instance = i;
      }
    }
    if (instance == null) {
      instance = new TalonFX(id);
      instances.add(instance);
    }
    return instance;
  }

  private TalonFX(int id) {
    talonFX = new WPI_TalonFX(id);
    talonFX.clearStickyFaults();
  }

  /**
   * Stops the motor
   */
  public void stop() {
    talonFX.stopMotor();
  }

  /**
   * Returns the CAN ID of the motor
   * 
   * @return CAN ID of the motor
   */
  public int getID() {
    return id;
  }

  /**
   * Returns the TalonFX instance
   * 
   * @return WPI_TalonFX
   */
  public WPI_TalonFX getTalon() {
    return talonFX;
  }

  /**
   * Gets the encoder position
   * 
   * @return encoder position
   */
  public double getEncoder() {
    return talonFX.getSelectedSensorPosition();
  }

  /**
   * Resets the encoder position to 0
   */
  public void resetEncoder() {
    talonFX.setSelectedSensorPosition(0.0);
  }

  /**
   * Resets the encoder position to 0
   * 
   * @param double position
   */
  public void resetEncoder(double position) {
    talonFX.setSelectedSensorPosition(position);
  }

  /**
   * Gets the velocity of the encoder
   * 
   * @return encoder velocity
   */
  public double getVelocity() {
    return talonFX.getSelectedSensorVelocity();
  }

  /**
   * Configures the neutral mode
   * 
   * @param boolean on
   */
  public void configBrakes(boolean on) {
    NeutralMode input;
    if (on)
      input = NeutralMode.Brake;
    else
      input = NeutralMode.Coast;

    talonFX.setNeutralMode(input);
  }

  /**
   * Configures the ramp speed
   * 
   * @param double rampSpeed
   */
  public void configRamps(double rampSpeed) {
    talonFX.configClosedloopRamp(rampSpeed);
  }
}
