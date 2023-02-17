// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.frc2023.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Claw extends SubsystemBase {
  private static Claw instance = null;

  public static Claw getInstance() {
    if (instance == null)
      instance = new Claw();
    return instance;
  }

  private DoubleSolenoid claw = Robot.getPH().makeDoubleSolenoid(0, 1);

  private Claw() {
  }

  public void depressurize() {
    claw.set(DoubleSolenoid.Value.kOff);
  }

  public void clamp() {
    claw.set(DoubleSolenoid.Value.kForward);
  }

  public void release() {
    claw.set(DoubleSolenoid.Value.kReverse);
  }

  public DoubleSolenoid.Value get() {
    return claw.get();
  }

  @Override
  public void periodic() {
  }
}
