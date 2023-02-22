// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.frc2023.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.frc2023.Robot;
import frc.team2641.frc2023.subsystems.Wrist;
import frc.team2641.lib.control.Buttons.Gamepad;

public class MoveWrist extends CommandBase {
  private Wrist wrist;

  public MoveWrist() {
    this.wrist = Wrist.getInstance();
    addRequirements(this.wrist);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double output = Robot.robotContainer.operator.getRawAxis(Gamepad.rxAxis);
    wrist.set(output);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
