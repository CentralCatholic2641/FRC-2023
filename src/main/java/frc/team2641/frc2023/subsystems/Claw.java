// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

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
    clamp();
  }

  public void depressurize() {
    claw.set(DoubleSolenoid.Value.kOff);
  }

  public void clamp() {
    System.out.println("clamp");
    claw.set(DoubleSolenoid.Value.kReverse);
  }

  public void release() {
    System.out.println("release");
    claw.set(DoubleSolenoid.Value.kForward);
  }

  public void toggle() {
    if (get().equals(DoubleSolenoid.Value.kReverse))
      claw.set(DoubleSolenoid.Value.kForward);
    else
      claw.set(DoubleSolenoid.Value.kReverse);
  }

  public DoubleSolenoid.Value get() {
    return claw.get();
  }

  @Override
  public void periodic() {
  }
}
