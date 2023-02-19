// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

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
        Commands.parallel(new SetArm(Constants.Arm.Positions.start),
            FollowPath.goTo(new PathPoint(new Translation2d(2.4, 2.7), Rotation2d.fromDegrees(0)))),
        new AutoBalance());
  }
}
