// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

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
