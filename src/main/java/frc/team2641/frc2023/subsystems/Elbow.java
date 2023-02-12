// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.frc2023.Constants;

public class Elbow extends SubsystemBase {

  private static Elbow instance = null;

  public static Elbow getInstance() {
    if (instance == null)
      instance = new Elbow();
    return instance;
  }

  private WPI_TalonFX elbow = new WPI_TalonFX(Constants.CAN.leftMaster);
  
  /** Creates a new Elbow. */
  public Elbow() {}

  public void set(double value) {
      elbow.set(value);
  } 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
