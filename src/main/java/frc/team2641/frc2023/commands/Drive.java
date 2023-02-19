// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.frc2023.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.frc2023.Robot;
import frc.team2641.frc2023.subsystems.Drivetrain;
import frc.team2641.lib.control.Buttons.Gamepad;

public class Drive extends CommandBase {
  private Drivetrain drivetrain;

  public Drive() {
    this.drivetrain = Drivetrain.getInstance();
    addRequirements(this.drivetrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (Robot.robotContainer.driverShift) {
      double left = Robot.robotContainer.driver.getRawAxis(Gamepad.lyAxis);
      double right = Robot.robotContainer.driver.getRawAxis(Gamepad.ryAxis);
      drivetrain.tDrive(-left, right);
    } else {
      double drive = Robot.robotContainer.driver.getRawAxis(Gamepad.lyAxis);
      double rotation = Robot.robotContainer.driver.getRawAxis(Gamepad.rxAxis);
      drivetrain.aDrive(drive, rotation);
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