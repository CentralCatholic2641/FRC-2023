// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.resurgence2023.Constants;

public class Shoulder extends SubsystemBase {
  private static Shoulder instance = null;

  public static Shoulder getInstance() {
    if (instance == null)
      instance = new Shoulder();
    return instance;
  }

  private TalonFX left = new TalonFX(Constants.CAN.leftShoulder);
  private TalonFX right = new TalonFX(Constants.CAN.rightShoulder);

  private PositionVoltage request = new PositionVoltage(0).withSlot(0);

  public Shoulder() {
    TalonFXConfiguration leftConfig = new TalonFXConfiguration();
    TalonFXConfiguration rightConfig = new TalonFXConfiguration();

    left.setInverted(false);
    right.setInverted(true);

    leftConfig.Slot0.kP = Constants.Arm.shoulderGains.kP;
    leftConfig.Slot0.kI = Constants.Arm.shoulderGains.kI;
    leftConfig.Slot0.kD = Constants.Arm.shoulderGains.kD;

    rightConfig.Slot0.kP = Constants.Arm.shoulderGains.kP;
    rightConfig.Slot0.kI = Constants.Arm.shoulderGains.kI;
    rightConfig.Slot0.kD = Constants.Arm.shoulderGains.kD;

    leftConfig.Voltage.PeakForwardVoltage = Constants.Arm.shoulderGains.kPeakOutput;
    leftConfig.Voltage.PeakReverseVoltage = -Constants.Arm.shoulderGains.kPeakOutput;
    rightConfig.Voltage.PeakForwardVoltage = Constants.Arm.shoulderGains.kPeakOutput;
    rightConfig.Voltage.PeakReverseVoltage = -Constants.Arm.shoulderGains.kPeakOutput;

    left.getConfigurator().apply(leftConfig);
    right.getConfigurator().apply(rightConfig);
  }

  public void set(double value) {
    double limited = Constants.Arm.shoulderRateLimiter.calculate(value);
    left.set(limited);
    right.set(limited);
  }

  public void setPos(double pos) {
    PositionVoltage r = request.withPosition(pos);
    left.setControl(r);
    right.setControl(r);
  }

  public double getEncoder() {
    return (left.getPosition().getValue() + right.getPosition().getValue()) / 2;
  }

  public void setEncoder(double value) {
    left.setPosition(value);
    right.setPosition(value);
  }

  @Override
  public void periodic() {
  }
}
