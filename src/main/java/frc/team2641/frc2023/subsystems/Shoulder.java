// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.subsystems;

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

  public Shoulder() {
    left.configFactoryDefault();

    left.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, Constants.Shoulder.timeoutMs);

    left.setSensorPhase(true);

    left.configNominalOutputForward(0, Constants.Shoulder.timeoutMs);
    left.configNominalOutputReverse(0, Constants.Shoulder.timeoutMs);
    left.configPeakOutputForward(1, Constants.Shoulder.timeoutMs);
    left.configPeakOutputReverse(-1, Constants.Shoulder.timeoutMs);

    left.configAllowableClosedloopError(0, 0, Constants.Shoulder.timeoutMs);

    left.config_kP(0, Constants.Shoulder.gains.kP, Constants.Shoulder.timeoutMs);
    left.config_kI(0, Constants.Shoulder.gains.kI, Constants.Shoulder.timeoutMs);
    left.config_kD(0, Constants.Shoulder.gains.kD, Constants.Shoulder.timeoutMs);
    left.config_kF(0, Constants.Shoulder.gains.kF, Constants.Shoulder.timeoutMs);
  }

  public void set(double pos) {
    left.set(TalonFXControlMode.Position, pos);
  }

  public double getEncoder() {
    return left.getSelectedSensorPosition();
  }

  @Override
  public void periodic() {
  }
}
