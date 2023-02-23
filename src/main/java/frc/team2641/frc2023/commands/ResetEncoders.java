// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.frc2023.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.frc2023.subsystems.Elbow;
import frc.team2641.frc2023.subsystems.Shoulder;
import frc.team2641.frc2023.subsystems.Wrist;

public class ResetEncoders extends CommandBase {
  private Shoulder shoulder = Shoulder.getInstance();
  private Elbow elbow = Elbow.getInstance();
  private Wrist wrist = Wrist.getInstance();

  public ResetEncoders() {
    addRequirements(shoulder, elbow, wrist);
  }

  @Override
  public void initialize() {
    shoulder.setEncoder(0);
    elbow.setEncoder(0);
    wrist.setEncoder(0);
    end(false);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
