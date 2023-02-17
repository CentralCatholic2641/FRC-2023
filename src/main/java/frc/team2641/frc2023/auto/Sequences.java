// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.auto;

import com.pathplanner.lib.PathPoint;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team2641.frc2023.Constants;
import frc.team2641.frc2023.commands.SetArm;
import frc.team2641.frc2023.subsystems.Claw;

public class Sequences {
  private static Claw claw = Claw.getInstance();

  public static Command ScoreConeThenBalance() {
    return Commands.sequence(
        Commands.parallel(new SetArm(Constants.Arm.Positions.topRow), FollowPath.get("Right Grid Left Cone")),
        new InstantCommand(() -> claw.release(), claw),
        FollowPath.goTo(new PathPoint(new Translation2d(2.4, 2.7), Rotation2d.fromDegrees(0))),
        new AutoBalance());
  }
}
