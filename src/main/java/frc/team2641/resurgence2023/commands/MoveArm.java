// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.team2641.resurgence2023.Robot;
import frc.team2641.resurgence2023.helpers.ArmPosition;
import frc.team2641.resurgence2023.subsystems.Arm;
import frc.team2641.resurgence2023.subsystems.Elbow;
import frc.team2641.resurgence2023.subsystems.Intake;
import frc.team2641.resurgence2023.subsystems.Shoulder;
import frc.team2641.resurgence2023.subsystems.Wrist;
import frc.team2641.lib.control.Buttons.Gamepad;

public class MoveArm extends Command {
  private Arm arm = Arm.getInstance();
  private Shoulder shoulder = Shoulder.getInstance();
  private Elbow elbow = Elbow.getInstance();
  private Wrist wrist = Wrist.getInstance();
  private Intake intake = Intake.getInstance();

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
      double elbowStick = Robot.robotContainer.operator.getRawAxis(5);
      double wristStick = Robot.robotContainer.operator.getRawAxis(4);

      int shoulder = (int) this.shoulder.getEncoder();
      int elbow = this.elbow.getEncoder();
      int wrist = this.wrist.getEncoder();

      if (Math.abs(shoulderStick) >= 0.05)
        shoulder = (int) this.shoulder.getEncoder()
            + (int) (shoulderStick * 10000);

      if (Math.abs(elbowStick) >= 0.05)
        elbow = (int) this.elbow.getEncoder()
            + (int) (elbowStick * 7500);

      if (Math.abs(wristStick) >= 0.05)
        wrist = (int) this.wrist.getEncoder()
            + (int) (wristStick * 5000);

      if (Math.abs(shoulderStick) > 0.05 || Math.abs(elbowStick) > 0.05 || Math.abs(wristStick) > 0.05) {
        intake.forward(0.25);
      } else {
        intake.stop();
      }

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
