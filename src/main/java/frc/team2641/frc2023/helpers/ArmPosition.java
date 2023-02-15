// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.helpers;

/** Add your docs here. */
public class ArmPosition {

  public int shoulder;
  public int elbow;
  public int wrist;
  public boolean clawOpen;

  public ArmPosition(int shoulder, int elbow, int wrist, boolean clawOpen) {
    this.shoulder = shoulder;
    this.elbow = elbow;
    this.wrist = wrist;
    this.clawOpen = clawOpen;
  }
}
