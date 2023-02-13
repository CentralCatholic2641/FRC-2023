// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.frc2023.Constants;

public class Shoulder extends SubsystemBase {
  private static Shoulder instance = null;

  public static Shoulder getInstance() {
    if (instance == null)
      instance = new Shoulder();
    return instance;
  }
  
  private WPI_TalonFX leftShoulder = new WPI_TalonFX(Constants.CAN.leftShoulder);
  private WPI_TalonFX rightShoulder = new WPI_TalonFX(Constants.CAN.rightShoulder);

  public Shoulder() {}
  /** Creates a new Shoulder. */
  public void set(double value){
    leftShoulder.set(value);
    rightShoulder.set(-value);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
