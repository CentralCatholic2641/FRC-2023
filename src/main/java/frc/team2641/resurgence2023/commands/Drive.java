// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.resurgence2023.Constants;
import frc.team2641.resurgence2023.Robot;
import frc.team2641.resurgence2023.subsystems.Drivetrain;
import frc.team2641.lib.control.Buttons.Gamepad;

public class Drive extends CommandBase {
  private Drivetrain drivetrain;

  // private boolean prevDriverTankShift = false;
  private boolean prevDriverSlowShift = false;

  public Drive() {
    this.drivetrain = Drivetrain.getInstance();
    addRequirements(this.drivetrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (Robot.robotContainer.driverTankShift) {
      double left = Robot.robotContainer.driver.getRawAxis(Gamepad.lyAxis);
      double right = Robot.robotContainer.driver.getRawAxis(Gamepad.ryAxis);
      drivetrain.tDrive(-left, right);
      // prevDriverTankShift = true;
    } else {
      double drive = Robot.robotContainer.driver.getRawAxis(Gamepad.lyAxis);
      double rotation = Robot.robotContainer.driver.getRawAxis(Gamepad.rxAxis);
      drivetrain.aDrive(drive, rotation);
      // prevDriverTankShift = false;
    }

    if (Robot.robotContainer.driverSlowShift != prevDriverSlowShift) {
      if (Robot.robotContainer.driverSlowShift) {
        drivetrain.configBrakes(true);
        drivetrain.configRamps(0);
        drivetrain.configDriveLimit(Constants.Drive.slowDrive);
        drivetrain.configSteerLimit(Constants.Drive.slowSteer);
      } else {
        drivetrain.configBrakes(Constants.Drive.brakes);
        drivetrain.configRamps(Constants.Drive.rampSpeed);
        drivetrain.configDriveLimit(Constants.Drive.maxDrive);
        drivetrain.configSteerLimit(Constants.Drive.maxSteer);
      }
      prevDriverSlowShift = Robot.robotContainer.driverSlowShift;
    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}