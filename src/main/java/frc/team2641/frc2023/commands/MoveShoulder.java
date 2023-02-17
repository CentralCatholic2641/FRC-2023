// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.frc2023.Robot;
import frc.team2641.frc2023.subsystems.Shoulder;
import frc.team2641.lib.control.Buttons.Gamepad;

public class MoveShoulder extends CommandBase {
  private Shoulder shoulder;

  public MoveShoulder() {
    this.shoulder = Shoulder.getInstance();
    addRequirements(this.shoulder);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double output = Robot.robotContainer.controller.getRawAxis(Gamepad.lyAxis);
    shoulder.set(output);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
