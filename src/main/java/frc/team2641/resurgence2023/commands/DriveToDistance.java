// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.team2641.resurgence2023.subsystems.Drivetrain;

public class DriveToDistance extends Command {
  private Drivetrain drivetrain = Drivetrain.getInstance();
  private double distance;

  public DriveToDistance(double distance) {
    addRequirements(drivetrain);
    this.distance = distance;
  }

  @Override
  public void initialize() {
    drivetrain.resetEncoders();
  }

  @Override
  public void execute() {
    if (((drivetrain.getLeftEncoder() + drivetrain.getRightEncoder()) / 2) >= this.distance) {
      drivetrain.aDrive(0, 0);
      end(false);
    } else {
      drivetrain.aDrive(0.4, 0);
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
