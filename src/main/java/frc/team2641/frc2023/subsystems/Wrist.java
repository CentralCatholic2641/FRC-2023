// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.frc2023.Constants;

public class Wrist extends SubsystemBase {
  private static Wrist instance = null;

  public static Wrist getInstance() {
    if (instance == null)
      instance = new Wrist();
    return instance;
  }
  private WPI_TalonFX wrist = new WPI_TalonFX(Constants.CAN.wrist);
  /** Creates a new Wrist. */
  public Wrist() {}
    public void set (double value) {
      wrist.set(value);
      
    }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
