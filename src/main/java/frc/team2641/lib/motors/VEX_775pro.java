// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.lib.motors;

import java.util.ArrayList;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class VEX_775pro {
  public static VEX_775pro instance = null;
  private static ArrayList<VEX_775pro> instances = new ArrayList<VEX_775pro>();

  private int id;
  private WPI_TalonSRX talonSRX = null;

  /**
   * Returns a prexisting instance of a VEX 775pro motor, or creates and returns a
   * new one
   * 
   * @return instance of VEX_775pro
   */
  public static VEX_775pro getInstance(int id) {
    VEX_775pro instance = null;
    for (VEX_775pro i : instances) {
      if (i.getID() == id) {
        instance = i;
      }
    }
    if (instance == null) {
      instance = new VEX_775pro(id);
      instances.add(instance);
    }
    return instance;
  }

  private VEX_775pro(int id) {
    talonSRX = new WPI_TalonSRX(id);
    talonSRX.clearStickyFaults();
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
   * Returns the TalonSRX instance
   * 
   * @return WPI_TalonSRX
   */
  public WPI_TalonSRX getTalon() {
    return talonSRX;
  }

  /**
   * Gets the encoder position
   * 
   * @return encoder position
   */
  public double getEncoder() {
    return talonSRX.getSelectedSensorPosition();
  }

  /**
   * Resets the encoder position to 0
   */
  public void resetEncoder() {
    talonSRX.setSelectedSensorPosition(0.0);
  }

  /**
   * Gets the velocity of the encoder
   * 
   * @return encoder velocity
   */
  public double getVelocity() {
    return talonSRX.getSelectedSensorVelocity();
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

    talonSRX.setNeutralMode(input);
  }
}
