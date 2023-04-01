// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.resurgence2023.Constants;

public class Intake extends SubsystemBase {
  private static Intake instance = null;
  private double stopSpeed = 0.2;

  public static Intake getInstance() {
    if (instance == null)
      instance = new Intake();
    return instance;
  }

  private WPI_TalonSRX intake = new WPI_TalonSRX(Constants.CAN.intake);

  public Intake() {
    intake.configFactoryDefault();

    intake.setInverted(false);

    stop();
  }

  public void forward(double value) {
    intake.set(Math.abs(value));
  }

  public void reverse(double value) {
    intake.set(-Math.abs(value));
  }

  public void stop() {
    intake.set(stopSpeed);
  }

  public void setStopSpeed(double input) {
    this.stopSpeed = input;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
