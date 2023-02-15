// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

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