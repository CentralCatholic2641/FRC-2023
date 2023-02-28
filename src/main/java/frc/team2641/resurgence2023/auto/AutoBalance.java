// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.resurgence2023.subsystems.Drivetrain;

public class AutoBalance extends CommandBase {
  private Drivetrain drivetrain = Drivetrain.getInstance();

  private double drive = 0.0;
  private boolean hasTilted = false;

  public AutoBalance() {
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    drive = 0.4;
  }

  @Override
  public void execute() {
    System.out.println("balancing...");
    if (!hasTilted && drivetrain.getPitch() != 0)
      hasTilted = true;

    if (hasTilted)
      drive = drivetrain.getPitch() / 150;

    if (hasTilted && drivetrain.getPitch() == 0)
      end(false);
    else
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
