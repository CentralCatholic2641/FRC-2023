// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.resurgence2023.Constants;
import frc.team2641.resurgence2023.subsystems.Drivetrain;

public class AutoBalance extends CommandBase {
  private Drivetrain drivetrain = Drivetrain.getInstance();

  private double drive = 0.0;
  private boolean hasTilted = false;
  private boolean onBoard = false;

  public AutoBalance() {
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    drivetrain.configRamps(0.0);
    drive = -0.425;
  }

  @Override
  public void execute() {
    System.out.println("Pitch: " + drivetrain.getPitch());
    if (!onBoard && (drivetrain.getPitch() < -15 || drivetrain.getPitch() > 15))
      onBoard = true;

    if (onBoard) {
      System.out.println("balancing...");
      if (!hasTilted && drivetrain.getPitch() != 0)
        hasTilted = true;

      if (hasTilted && (drivetrain.getPitch() > 18)) {
        System.out.println("tipping backward!");
        drive = 0.2;
      }

      if (hasTilted && (drivetrain.getPitch() < -18)) {
        System.out.println("tipping forward!");
        drive = -0.2;
      }

      if (hasTilted && ((drivetrain.getPitch() >= -18) && (drivetrain.getPitch() <= -8)))
        drive = -drivetrain.getPitch()/44.75;

      if (hasTilted && ((drivetrain.getPitch() <= 18) && (drivetrain.getPitch() >= 8)))
        drive = -drivetrain.getPitch()/46.25;

      if (hasTilted && ((drivetrain.getPitch() > -12) && (drivetrain.getPitch() < 12))) {
        System.out.println("balanced");
        drive = 0.0;
      }

      else
        drivetrain.aDriveUnlimited(0, drive);
      }

    else
      System.out.println("finding board...");
      drivetrain.aDriveUnlimited(0, drive);
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.configRamps(Constants.Drive.rampSpeed);
  }

  @Override
  public boolean isFinished() {
    return hasTilted && drivetrain.getPitch() == 0;
  }
}
