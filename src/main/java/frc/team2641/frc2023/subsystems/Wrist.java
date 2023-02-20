// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.frc2023.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.frc2023.Constants;

public class Wrist extends SubsystemBase {
  private static Wrist instance = null;

  public static Wrist getInstance() {
    if (instance == null)
      instance = new Wrist();
    return instance;
  }

  private WPI_TalonFX wrist = new WPI_TalonFX(Constants.CAN.wrist);

  public Wrist() {
    wrist.configFactoryDefault();

    wrist.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);

    wrist.setSensorPhase(true);

    wrist.setInverted(false);

    wrist.configNominalOutputForward(0, 30);
    wrist.configNominalOutputReverse(0, 30);
    wrist.configPeakOutputForward(Constants.Arm.elbowGains.kPeakOutput, 30);
    wrist.configPeakOutputReverse(-Constants.Arm.elbowGains.kPeakOutput, 30);

    wrist.configAllowableClosedloopError(0, Constants.Arm.elbowGains.kAllowableError, 30);

    wrist.config_kP(0, Constants.Arm.elbowGains.kP, 30);
    wrist.config_kI(0, Constants.Arm.elbowGains.kI, 30);
    wrist.config_kD(0, Constants.Arm.elbowGains.kD, 30);
    wrist.config_kF(0, Constants.Arm.elbowGains.kF, 30);
  }

  public void set(double value) {
    wrist.set(Constants.Arm.wristRateLimiter.calculate(value));
  }

  public void setPos(double pos) {
    wrist.set(ControlMode.Position, pos);
  }

  public int getEncoder() {
    return (int) wrist.getSelectedSensorPosition();
  }

  public void setEncoder(double value) {
    wrist.setSelectedSensorPosition(value);
  }

  @Override
  public void periodic() {
  }
}
