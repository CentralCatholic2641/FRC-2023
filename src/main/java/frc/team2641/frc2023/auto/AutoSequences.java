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

public class AutoSequences {
	private static Claw claw = Claw.getInstance();

	public static Command ScoreConeThenBalance() {
		return Commands.sequence(
				Commands.parallel(new SetArm(Constants.Arm.Positions.topRow), FollowPath.get("Right Grid Left Cone")),
				new InstantCommand(() -> claw.release(), claw),
				Commands.parallel(new SetArm(Constants.Arm.Positions.start),
						FollowPath.goTo(new PathPoint(new Translation2d(2.4, 2.7), Rotation2d.fromDegrees(0)))),
				new AutoBalance());
	}

	public static Command ScoreCubeThenPickupAndScoreCone() {
		return Commands.sequence(
				FollowPath.goTo(new PathPoint(new Translation2d(1.9, 4.4), Rotation2d.fromDegrees(180))),
				ArmSequences.ScoreHigh(),
				FollowPath.goTo(new PathPoint(new Translation2d(6.36, 4.64), Rotation2d.fromDegrees(180))),
				ArmSequences.Intake(),
				FollowPath.goTo(new PathPoint(new Translation2d(1.9, 4.97), Rotation2d.fromDegrees(180))),
				ArmSequences.ScoreHigh());
	}
}
