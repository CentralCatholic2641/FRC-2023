// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.frc2023.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.frc2023.helpers.ArmPosition;
import frc.team2641.frc2023.subsystems.Arm;

public class SetArm extends CommandBase {
  private Arm arm = Arm.getInstance();
  private ArmPosition position;

  public SetArm(ArmPosition position) {
    addRequirements(arm);
    this.position = position;
  }

  @Override
  public void initialize() {
    arm.set(position);
  }

  @Override
  public void execute() {
    if (arm.atPosition())
      end(false);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return arm.atPosition();
  }
}
