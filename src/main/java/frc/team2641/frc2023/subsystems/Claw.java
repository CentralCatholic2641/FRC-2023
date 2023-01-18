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

  private DoubleSolenoid clawSolenoid = Robot.getPH().makeDoubleSolenoid(0, 1);
  private DoubleSolenoid pressureSolenoid = Robot.getPH().makeDoubleSolenoid(2, 3);

  private Claw() {
    pressurize();
  }

  public void pressurize() {
    pressureSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void depressurize() {
    release();
    pressureSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void clamp() {
    clawSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void release() {
    clawSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  @Override
  public void periodic() {
  }
}
