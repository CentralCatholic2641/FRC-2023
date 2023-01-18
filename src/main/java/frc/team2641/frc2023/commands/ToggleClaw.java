// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.frc2023.subsystems.Claw;

public class ToggleClaw extends CommandBase {

  private Claw claw;

  public ToggleClaw() {
    this.claw = Claw.getInstance();
    addRequirements(this.claw);
  }

  @Override
  public void initialize() {
    claw.clamp();
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    claw.release();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
