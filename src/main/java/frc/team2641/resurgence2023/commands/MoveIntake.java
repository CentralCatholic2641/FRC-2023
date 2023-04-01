// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.resurgence2023.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.resurgence2023.subsystems.Intake;

public class MoveIntake extends CommandBase {
  private Intake intake = Intake.getInstance();
  private boolean direction;

  public MoveIntake(boolean direction) {
    addRequirements(intake);
    this.direction = direction;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (this.direction) {
      intake.forward(0.75);
    } else {
      intake.reverse(0.3);
    }
  }

  @Override
  public void end(boolean interrupted) {
    intake.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
