// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.frc2023.Constants;

public class Shoulder extends SubsystemBase {

  private static Shoulder instance = null;

  public static Shoulder getInstance() {
    if (instance == null)
      instance = new Shoulder();
    return instance;
  }

  private WPI_TalonFX left = new WPI_TalonFX(Constants.CAN.leftShoulder);
  private WPI_TalonFX right = new WPI_TalonFX(Constants.CAN.rightShoulder);

  public Shoulder() {
    left.configFactoryDefault();
    right.configFactoryDefault();

    left.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, Constants.Shoulder.timeoutMs);
    right.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, Constants.Shoulder.timeoutMs);

    left.setSensorPhase(true);
    right.setSensorPhase(false);

    left.configNominalOutputForward(0, Constants.Shoulder.timeoutMs);
    left.configNominalOutputReverse(0, Constants.Shoulder.timeoutMs);
    left.configPeakOutputForward(0.25, Constants.Shoulder.timeoutMs);
    left.configPeakOutputReverse(-0.25, Constants.Shoulder.timeoutMs);
    right.configNominalOutputForward(0, Constants.Shoulder.timeoutMs);
    right.configNominalOutputReverse(0, Constants.Shoulder.timeoutMs);
    right.configPeakOutputForward(0.25, Constants.Shoulder.timeoutMs);
    right.configPeakOutputReverse(-0.25, Constants.Shoulder.timeoutMs);

    left.configAllowableClosedloopError(0, 0, Constants.Shoulder.timeoutMs);
    right.configAllowableClosedloopError(0, 0, Constants.Shoulder.timeoutMs);

    left.config_kP(0, Constants.Shoulder.gains.kP, Constants.Shoulder.timeoutMs);
    left.config_kI(0, Constants.Shoulder.gains.kI, Constants.Shoulder.timeoutMs);
    left.config_kD(0, Constants.Shoulder.gains.kD, Constants.Shoulder.timeoutMs);
    left.config_kF(0, Constants.Shoulder.gains.kF, Constants.Shoulder.timeoutMs);
    right.config_kP(0, Constants.Shoulder.gains.kP, Constants.Shoulder.timeoutMs);
    right.config_kI(0, Constants.Shoulder.gains.kI, Constants.Shoulder.timeoutMs);
    right.config_kD(0, Constants.Shoulder.gains.kD, Constants.Shoulder.timeoutMs);
    right.config_kF(0, Constants.Shoulder.gains.kF, Constants.Shoulder.timeoutMs);
  }

  public void setPos(double pos) {
    left.set(TalonFXControlMode.Position, pos * 48);
    right.set(TalonFXControlMode.Position, pos * 48);
  }

  public void set(double pos) {
    left.set(ControlMode.PercentOutput, pos);
    right.set(ControlMode.PercentOutput, pos);
  }

  public double getEncoder() {
    return ((left.getSelectedSensorPosition() / 48) + (right.getSelectedSensorPosition() / 48)) / (double) 2;
  }

  public void setEncoder(double value) {
    left.setSelectedSensorPosition(value);
    right.setSelectedSensorPosition(value);
  }

  @Override
  public void periodic() {
  }
}
