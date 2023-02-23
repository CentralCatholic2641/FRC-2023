// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.frc2023.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.frc2023.Constants;
import frc.team2641.frc2023.Robot;
import frc.team2641.frc2023.helpers.ArmPosition;
import frc.team2641.frc2023.subsystems.Arm;
import frc.team2641.frc2023.subsystems.Elbow;
import frc.team2641.frc2023.subsystems.Shoulder;
import frc.team2641.frc2023.subsystems.Wrist;
import frc.team2641.lib.control.Buttons.Gamepad;

public class MoveArm extends CommandBase {
  private Arm arm = Arm.getInstance();
  private Shoulder shoulder = Shoulder.getInstance();
  private Elbow elbow = Elbow.getInstance();
  private Wrist wrist = Wrist.getInstance();

  public MoveArm() {
    addRequirements(arm, shoulder, elbow, wrist);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (!arm.isAuto()) {
      int shoulder = (int) this.shoulder.getEncoder() + (int) (Robot.robotContainer.operator.getRawAxis(Gamepad.lyAxis) * 10000);
      int elbow = (int) this.elbow.getEncoder() + (int) (Robot.robotContainer.operator.getRawAxis(Gamepad.ryAxis) * 10000);
      int wrist = (int) this.wrist.getEncoder() + (int) (Robot.robotContainer.operator.getRawAxis(Gamepad.rxAxis) * 5000);
      System.out.println("calculating... " + shoulder);
      arm.set(new ArmPosition(shoulder, elbow, wrist));
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
