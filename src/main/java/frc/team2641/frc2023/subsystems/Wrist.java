// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.frc2023.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.frc2023.Constants;

public class Wrist extends SubsystemBase {
  private static Wrist instance = null;

  public static Wrist getInstance() {
    if (instance == null)
      instance = new Wrist();
    return instance;
  }

  private boolean auto = false;

  private WPI_TalonSRX wrist = new WPI_TalonSRX(Constants.CAN.wrist);

  public Wrist() {
    wrist.configFactoryDefault();

    wrist.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);

    wrist.setSensorPhase(false);

    wrist.setInverted(true);

    wrist.configNominalOutputForward(0, 30);
    wrist.configNominalOutputReverse(0, 30);
    wrist.configPeakOutputForward(Constants.Arm.wristGains.kPeakOutput, 30);
    wrist.configPeakOutputReverse(-Constants.Arm.wristGains.kPeakOutput, 30);

    wrist.configAllowableClosedloopError(0, Constants.Arm.wristGains.kAllowableError, 30);

    wrist.config_kP(0, Constants.Arm.wristGains.kP, 30);
    wrist.config_kI(0, Constants.Arm.wristGains.kI, 30);
    wrist.config_kD(0, Constants.Arm.wristGains.kD, 30);
    wrist.config_kF(0, Constants.Arm.wristGains.kF, 30);
  }

  public void set(double value) {
    wrist.set(Constants.Arm.wristRateLimiter.calculate(value * Constants.Arm.wristGains.kPeakOutput));
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

  public boolean isAuto() {
    return auto;
  }

  public void setAuto(boolean auto) {
    this.auto = auto;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("wrist", getEncoder());
  }
}
