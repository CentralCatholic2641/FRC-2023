// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.resurgence2023.Constants;

public class Elbow extends SubsystemBase {
  private static Elbow instance = null;

  public static Elbow getInstance() {
    if (instance == null)
      instance = new Elbow();
    return instance;
  }

  private TalonFX elbow = new TalonFX(Constants.CAN.elbow);

  public Elbow() {
    elbow.getConfigurator().apply(new TalonFXConfiguration());

    TalonFXConfiguration config = new TalonFXConfiguration();

    elbow.setInverted(false);
    config.Slot0.kP = Constants.Arm.elbowGains.kP;
    config.Slot0.kI = Constants.Arm.elbowGains.kI;
    config.Slot0.kD = Constants.Arm.elbowGains.kD;
    config.Voltage.PeakForwardVoltage = Constants.Arm.elbowGains.kPeakOutput;
    config.Voltage.PeakReverseVoltage = -Constants.Arm.elbowGains.kPeakOutput;

    elbow.getConfigurator().apply(config);
  }

  public void set(double value) {
    elbow.set(Constants.Arm.elbowRateLimiter.calculate(value));
  }

  public void setPos(double pos) {
    elbow.setPosition(pos);
  }

  public double getEncoder() {
    return (double) elbow.getPosition().getValue();
  }

  public void setEncoder(double value) {
    elbow.setPosition(value);
  }

  @Override
  public void periodic() {
  }
}
