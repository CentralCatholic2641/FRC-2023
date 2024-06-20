// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.resurgence2023.Constants;
import frc.team2641.resurgence2023.Robot;
import com.ctre.phoenix6.configs.ClosedLoopRampsConfigs;
import com.ctre.phoenix6.configs.OpenLoopRampsConfigs;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class Drivetrain extends SubsystemBase {
  private static Drivetrain instance = null;

  public static Drivetrain getInstance() {
    if (instance == null)
      instance = new Drivetrain();
    return instance;
  }

  private TalonFX leftMaster = new TalonFX(Constants.CAN.leftMaster);
  private TalonFX leftSlave1 = new TalonFX(Constants.CAN.leftSlave1);
  private TalonFX leftSlave2 = new TalonFX(Constants.CAN.leftSlave2);

  private TalonFX rightMaster = new TalonFX(Constants.CAN.rightMaster);
  private TalonFX rightSlave1 = new TalonFX(Constants.CAN.rightSlave1);
  private TalonFX rightSlave2 = new TalonFX(Constants.CAN.rightSlave2);

  private DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

  private double driveLimit = Constants.Drive.maxDrive;
  private double steerLimit = Constants.Drive.maxSteer;

  private Drivetrain() {
    leftSlave1.setControl(new Follower(leftMaster.getDeviceID(), false));
    leftSlave2.setControl(new Follower(leftMaster.getDeviceID(), false));
    rightSlave1.setControl(new Follower(rightMaster.getDeviceID(), false));
    rightSlave2.setControl(new Follower(rightMaster.getDeviceID(), false));

    configBrakes(Constants.Drive.brakes);
    configRamps(Constants.Drive.rampSpeed);
    configDriveLimit(Constants.Drive.maxDrive);
    configSteerLimit(Constants.Drive.maxSteer);
  }

  public void aDrive(double speed, double rotation) {
    double speedOut = Constants.Drive.driveRateLimiter.calculate(((0.35 - driveLimit) * Robot.robotContainer.driver.getRightTrigger() + driveLimit) * -speed);
    double rotateOut = Constants.Drive.rotationRateLimiter.calculate(((0.35 - steerLimit) * Robot.robotContainer.driver.getRightTrigger() + steerLimit) * rotation);

    if (Math.abs(speedOut) >= 0.01 || Math.abs(rotateOut) >= 0.01) {
      drive.arcadeDrive( rotateOut, speedOut, true);
    } else {
      drive.arcadeDrive(0, 0);
    }
  }

  public void aDriveUnlimited(double speed, double rotation) {
    drive.arcadeDrive(-speed, -rotation, true);
  }

  public void tDrive(double left, double right) {
    drive.tankDrive(Constants.Drive.driveRateLimiter.calculate(left * driveLimit),
        Constants.Drive.driveRateLimiter.calculate(right * steerLimit), true);
  }

  public void configBrakes(boolean brakesOn) {
    NeutralModeValue input;
    if (brakesOn)
      input = NeutralModeValue.Brake;
    else
      input = NeutralModeValue.Coast;

    leftMaster.setNeutralMode(input);
    leftSlave1.setNeutralMode(input);
    leftSlave2.setNeutralMode(input);
    rightMaster.setNeutralMode(input);
    rightSlave1.setNeutralMode(input);
    rightSlave2.setNeutralMode(input);
  }

  public void configRamps(double rampSpeed) {
    leftMaster.getConfigurator().apply(new OpenLoopRampsConfigs().withVoltageOpenLoopRampPeriod(rampSpeed));
    leftSlave1.getConfigurator().apply(new OpenLoopRampsConfigs().withVoltageOpenLoopRampPeriod(rampSpeed));
    leftSlave2.getConfigurator().apply(new OpenLoopRampsConfigs().withVoltageOpenLoopRampPeriod(rampSpeed));
    rightMaster.getConfigurator().apply(new OpenLoopRampsConfigs().withVoltageOpenLoopRampPeriod(rampSpeed));
    rightSlave1.getConfigurator().apply(new OpenLoopRampsConfigs().withVoltageOpenLoopRampPeriod(rampSpeed));
    rightSlave2.getConfigurator().apply(new OpenLoopRampsConfigs().withVoltageOpenLoopRampPeriod(rampSpeed));
    
    leftMaster.getConfigurator().apply(new ClosedLoopRampsConfigs().withVoltageClosedLoopRampPeriod(rampSpeed));
    leftSlave1.getConfigurator().apply(new ClosedLoopRampsConfigs().withVoltageClosedLoopRampPeriod(rampSpeed));
    leftSlave2.getConfigurator().apply(new ClosedLoopRampsConfigs().withVoltageClosedLoopRampPeriod(rampSpeed));
    rightMaster.getConfigurator().apply(new ClosedLoopRampsConfigs().withVoltageClosedLoopRampPeriod(rampSpeed));
    rightSlave1.getConfigurator().apply(new ClosedLoopRampsConfigs().withVoltageClosedLoopRampPeriod(rampSpeed));
    rightSlave2.getConfigurator().apply(new ClosedLoopRampsConfigs().withVoltageClosedLoopRampPeriod(rampSpeed));
  }

  public void configDriveLimit(double driveLimit) {
    this.driveLimit = driveLimit;
  }

  public void configSteerLimit(double steerLimit) {
    this.steerLimit = steerLimit;
  }
}