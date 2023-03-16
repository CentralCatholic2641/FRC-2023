// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
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
    drive = 0.35;
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

      if (hasTilted && (drivetrain.getPitch() > 20)) {
        System.out.println("tipping backward!");
        drive = -0.5;
      }

      if (hasTilted && (drivetrain.getPitch() < -20)) {
        System.out.println("tipping forward!");
        drive = 0.5;
      }

      if (hasTilted && ((drivetrain.getPitch() >= -20) && (drivetrain.getPitch() <= 20)))
        drive = drivetrain.getPitch()/65;

      if (hasTilted && ((drivetrain.getPitch() < -1) && (drivetrain.getPitch() > 1))) {
        System.out.println("balanced");
        end(false);
      }

      else
        drivetrain.aDriveUnlimited(drive, 0);
      }

    else
      System.out.println("finding board...");
      drivetrain.aDriveUnlimited(drive, 0);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return hasTilted && drivetrain.getPitch() == 0;
  }
}
