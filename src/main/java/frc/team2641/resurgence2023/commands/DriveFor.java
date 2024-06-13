// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.resurgence2023.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.team2641.resurgence2023.subsystems.Drivetrain;

public class DriveFor extends Command {
  private Drivetrain drivetrain = Drivetrain.getInstance();
  private double time;
  private Timer timer = new Timer();

  public DriveFor(double time) {
    addRequirements(drivetrain);
    this.time = time;
  }

  @Override
  public void initialize() {
    timer.start();
  }

  @Override
  public void execute() {
    System.out.println(timer.get());
    if (timer.get() >= time) {
      timer.stop();
      end(false);
    } else {
      drivetrain.aDrive(-0.5, 0);
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
