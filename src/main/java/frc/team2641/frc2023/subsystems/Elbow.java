// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.frc2023.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.frc2023.Constants;

public class Elbow extends SubsystemBase {
  private static Elbow instance = null;

  public static Elbow getInstance() {
    if (instance == null)
      instance = new Elbow();
    return instance;
  }

  private WPI_TalonFX elbow = new WPI_TalonFX(Constants.CAN.elbow);

  public Elbow() {
    elbow.configFactoryDefault();

    elbow.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);

    elbow.setSensorPhase(true);

    elbow.setInverted(false);

    elbow.configNominalOutputForward(0, 30);
    elbow.configNominalOutputReverse(0, 30);
    elbow.configPeakOutputForward(Constants.Arm.elbowGains.kPeakOutput, 30);
    elbow.configPeakOutputReverse(-Constants.Arm.elbowGains.kPeakOutput, 30);

    elbow.configAllowableClosedloopError(0, Constants.Arm.elbowGains.kAllowableError, 30);

    elbow.config_kP(0, Constants.Arm.elbowGains.kP, 30);
    elbow.config_kI(0, Constants.Arm.elbowGains.kI, 30);
    elbow.config_kD(0, Constants.Arm.elbowGains.kD, 30);
    elbow.config_kF(0, Constants.Arm.elbowGains.kF, 30);
  }

  public void set(double value) {
    elbow.set(Constants.Arm.elbowRateLimiter.calculate(value));
  }

  public void setPos(double pos) {
    elbow.set(ControlMode.Position, pos);
  }

  public int getEncoder() {
    return (int) elbow.getSelectedSensorPosition();
  }

  public void setEncoder(double value) {
    elbow.setSelectedSensorPosition(value);
  }

  @Override
  public void periodic() {
  }
}
