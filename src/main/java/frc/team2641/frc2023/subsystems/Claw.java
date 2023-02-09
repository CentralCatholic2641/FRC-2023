// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.frc2023.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Claw extends SubsystemBase {

  public Compressor compressor = new Compressor(21, PneumaticsModuleType.CTREPCM);
  public DoubleSolenoid clamper = new DoubleSolenoid(21, PneumaticsModuleType.CTREPCM, 0, 1);

  private static Claw instance = null;

  public static Claw getInstance() {
    if (instance == null)
      instance = new Claw();
    return instance;
  }

  // private DoubleSolenoid clawSolenoid = new DoubleSolenoid(21, PneumaticsModuleType.CTREPCM, 0, 1);
  // private DoubleSolenoid pressureSolenoid = Robot.getPH().makeDoubleSolenoid(2, 3);

  private Claw() {
    // pressurize();
    compressor.enableDigital();
    release();
  }

  // public void pressurize() {
  //   pressureSolenoid.set(DoubleSolenoid.Value.kForward);
  // }

  // public void depressurize() {
  //   release();
  //   pressureSolenoid.set(DoubleSolenoid.Value.kReverse);
  // }

  public void clamp() {
    clamper.set(DoubleSolenoid.Value.kForward);
  }

  public void release() {
    clamper.set(DoubleSolenoid.Value.kReverse);
  }

  @Override
  public void periodic() {
  }
}
