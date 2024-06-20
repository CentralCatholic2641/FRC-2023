// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
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

  public Shoulder() {
    left.configFactoryDefault();
    right.configFactoryDefault();

    left.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
    right.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);

    left.setSensorPhase(true);
    right.setSensorPhase(false);

    left.setInverted(false);
    right.setInverted(true);

    left.configNominalOutputForward(0, 30);
    left.configNominalOutputReverse(0, 30);
    left.configPeakOutputForward(Constants.Arm.shoulderGains.kPeakOutput, 30);
    left.configPeakOutputReverse(-Constants.Arm.shoulderGains.kPeakOutput, 30);
    right.configNominalOutputForward(0, 30);
    right.configNominalOutputReverse(0, 30);
    right.configPeakOutputForward(Constants.Arm.shoulderGains.kPeakOutput, 30);
    right.configPeakOutputReverse(-Constants.Arm.shoulderGains.kPeakOutput, 30);

    left.configAllowableClosedloopError(0, Constants.Arm.shoulderGains.kAllowableError, 30);
    right.configAllowableClosedloopError(0, Constants.Arm.shoulderGains.kAllowableError, 30);

    left.config_kP(0, Constants.Arm.shoulderGains.kP, 30);
    left.config_kI(0, Constants.Arm.shoulderGains.kI, 30);
    left.config_kD(0, Constants.Arm.shoulderGains.kD, 30);
    left.config_kF(0, Constants.Arm.shoulderGains.kF, 30);
    right.config_kP(0, Constants.Arm.shoulderGains.kP, 30);
    right.config_kI(0, Constants.Arm.shoulderGains.kI, 30);
    right.config_kD(0, Constants.Arm.shoulderGains.kD, 30);
    right.config_kF(0, Constants.Arm.shoulderGains.kF, 30);
  }

  public void set(double value) {
    double limited = Constants.Arm.shoulderRateLimiter.calculate(value);
    left.set(limited);
    right.set(limited);
  }

  public void setPos(double pos) {
    left.set(ControlMode.Position, pos);
    right.set(ControlMode.Position, pos);
  }

  public double getEncoder() {
    return (left.getSelectedSensorPosition() + right.getSelectedSensorPosition()) / 2;
  }

  public void setEncoder(double value) {
    left.setSelectedSensorPosition(value);
    right.setSelectedSensorPosition(value);
  }

  @Override
  public void periodic() {
  }
}
