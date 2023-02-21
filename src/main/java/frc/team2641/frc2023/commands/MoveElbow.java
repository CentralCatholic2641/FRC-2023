// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.frc2023.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.frc2023.Robot;
import frc.team2641.frc2023.subsystems.Elbow;
import frc.team2641.lib.control.Buttons.Gamepad;

public class MoveElbow extends CommandBase {
  private Elbow elbow;

  public MoveElbow() {
    this.elbow = Elbow.getInstance();
    addRequirements(this.elbow);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (!elbow.isAuto()) {
      double output = Robot.robotContainer.operator.getRawAxis(Gamepad.ryAxis);
      if (output > 0.01 || output < -0.01) {
        elbow.set(output);
      } else {
        elbow.setPos(elbow.getEncoder());
      }
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
