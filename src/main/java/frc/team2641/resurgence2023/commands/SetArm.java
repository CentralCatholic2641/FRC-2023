// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.team2641.resurgence2023.helpers.ArmPosition;
import frc.team2641.resurgence2023.subsystems.Arm;
import frc.team2641.resurgence2023.subsystems.Intake;

public class SetArm extends Command {
  private Arm arm = Arm.getInstance();
  private Intake intake = Intake.getInstance();
  private ArmPosition position;

  public SetArm(ArmPosition position) {
    addRequirements(arm);
    this.position = position;
  }

  @Override
  public void initialize() {
    arm.setAuto(true);
    arm.set(position);
  }

  @Override
  public void execute() {
    if (arm.atPosition()) {
      end(false);
    } else {
      intake.forward(0.5);
      System.out.println("moving...");
    }
  }

  @Override
  public void end(boolean interrupted) {
    arm.setAuto(false);
    intake.stop();
  }

  @Override
  public boolean isFinished() {
    return arm.atPosition();
  }
}
