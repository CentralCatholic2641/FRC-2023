// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.lib;

public class PIDGains {
  public final double kP;
  public final double kI;
  public final double kD;
  public final double kF;
  public final double kPeakOutput;
  public final double kAllowableError;

  public PIDGains(double kP, double kI, double kD, double kF, double kPeakOutput,
      double kAllowableError) {
    this.kP = kP;
    this.kI = kI;
    this.kD = kD;
    this.kF = kF;
    this.kPeakOutput = kPeakOutput;
    this.kAllowableError = kAllowableError;
  }
}