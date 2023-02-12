// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.frc2023.subsystems.Shoulder;

public class SetArm extends CommandBase {

  private Shoulder shoulder = Shoulder.getInstance();

  public SetArm() {
    addRequirements(shoulder);
  }

  @Override
  public void initialize() {
    System.out.println("running");
    shoulder.setPos(1200);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
