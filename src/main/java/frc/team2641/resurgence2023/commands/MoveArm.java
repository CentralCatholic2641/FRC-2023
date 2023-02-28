// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.resurgence2023.Robot;
import frc.team2641.resurgence2023.helpers.ArmPosition;
import frc.team2641.resurgence2023.subsystems.Arm;
import frc.team2641.resurgence2023.subsystems.Elbow;
import frc.team2641.resurgence2023.subsystems.Shoulder;
import frc.team2641.resurgence2023.subsystems.Wrist;
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
      double shoulderStick = Robot.robotContainer.operator.getRawAxis(Gamepad.lyAxis);
      double elbowStick = Robot.robotContainer.operator.getRawAxis(4);
      double wristStick = Robot.robotContainer.operator.getRawAxis(3);

      int shoulder = (int) this.shoulder.getEncoder();
      int elbow = this.elbow.getEncoder();
      int wrist = this.wrist.getEncoder();

      if (Math.abs(shoulderStick) >= 0.05)
        shoulder = (int) this.shoulder.getEncoder()
            + (int) (shoulderStick * 10000);

      if (Math.abs(elbowStick) >= 0.05)
        elbow = (int) this.elbow.getEncoder()
            + (int) (elbowStick * 5000);

      if (Math.abs(wristStick) >= 0.05)
        wrist = (int) this.wrist.getEncoder()
            + (int) (wristStick * 5000);

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
