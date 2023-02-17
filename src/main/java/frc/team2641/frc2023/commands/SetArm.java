// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

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
