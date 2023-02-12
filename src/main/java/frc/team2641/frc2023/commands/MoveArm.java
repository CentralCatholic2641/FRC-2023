// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.frc2023.Robot;
import frc.team2641.frc2023.subsystems.Shoulder;

public class MoveArm extends CommandBase {

  private Shoulder shoulder = Shoulder.getInstance();

  public MoveArm() {
    addRequirements(this.shoulder);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double output = -Robot.robotContainer.joystick.getRawAxis(1) * 1;
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
