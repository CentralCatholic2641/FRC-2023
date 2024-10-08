// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.team2641.lib.control.Buttons.Gamepad;
import frc.team2641.resurgence2023.Constants;
import frc.team2641.resurgence2023.Robot;
import frc.team2641.resurgence2023.commands.DriveFor;
import frc.team2641.resurgence2023.subsystems.Drivetrain;

public class AutoBalance extends Command {
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
    drive = 0.465;
  }

  @Override
  public void execute() {
    System.out.println("Pitch: " + drivetrain.getPitch());

    if (!onBoard && (drivetrain.getPitch() < -15 || drivetrain.getPitch() > 15))
      onBoard = true;

    else if (onBoard) {
      if (!hasTilted && ((drivetrain.getPitch() > 9) || (drivetrain.getPitch() < -9))) {
        hasTilted = true;
      }

      if (hasTilted && (drivetrain.getPitch() > 18)) {
        System.out.println("tipping backward!");
        drive = 0.3;
      }

      if (hasTilted && (drivetrain.getPitch() < -18)) {
        System.out.println("tipping forward!");
        drive = -0.3;
      }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
      if (hasTilted && ((drivetrain.getPitch() >= -18) && (drivetrain.getPitch() <= -14.5))) {
        System.out.println("balancing...");
        Timer.delay(.75);
        drive = 0.33;
      }

      if (hasTilted && ((drivetrain.getPitch() <= 18) && (drivetrain.getPitch() >= 14.5))) {
        System.out.println("balancing...");
        Timer.delay(.75);
        drive = -0.32;
      }                                                                                                                                                                                                                                                                                                                     

      if (hasTilted && ((drivetrain.getPitch() > -14.5) && (drivetrain.getPitch() < 14.5))) {
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
    onBoard = false;
  }

  @Override
  public boolean isFinished() {
    return (Math.abs(Robot.robotContainer.driver.getRawAxis(Gamepad.lyAxis)) > 0.1) || (Math.abs(Robot.robotContainer.driver.getRawAxis(Gamepad.rxAxis)) > 0.1);
  }
}
