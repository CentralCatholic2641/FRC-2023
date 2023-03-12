// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.helpers;

public class ArmPosition {
  public int shoulder;
  public int elbow;
  public int wrist;

  public ArmPosition(int shoulder, int elbow, int wrist) {
    this.shoulder = shoulder;
    this.elbow = elbow;
    this.wrist = wrist;
  }

  public String toString() {
    return "shoulder: " + shoulder + " elbow: " + elbow + " wrist: " + wrist;
  }
}
