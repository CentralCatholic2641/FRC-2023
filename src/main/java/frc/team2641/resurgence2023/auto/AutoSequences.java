// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team2641.resurgence2023.commands.Wait;
import frc.team2641.resurgence2023.subsystems.Drivetrain;

public class AutoSequences {
	private static Drivetrain drivetrain = Drivetrain.getInstance();

	public static Command NoAuto() {
		return new InstantCommand();
	}

	// public static Command ScorePreloadCubePickupScorePrestagedConeDock(String start) {
	// 	return FollowPath.get(start + "_Score Preload Score Prestaged Dock", true);
	// }

	// public static Command ScorePreloadCubePickupScorePrestagedCone(String start) {
	// 	return FollowPath.get(start + "_Score Preload Score Prestaged", true);
	// }

	// public static Command ScorePreload(String start) {
	// 	return FollowPath.get(start + "_Score Preload", true);
	// }

	// public static Command Mobility(String start) {
	// 	return FollowPath.get(start + "_Mobility", true);
	// }

	public static Command ScorePreload(String start) {
		return Commands.sequence(
			ArmSequences.ScoreTop(),
			new InstantCommand(() -> drivetrain.aDrive(0.4, 0)),
			new Wait(1),
			new InstantCommand(() -> drivetrain.aDrive(0, 0))
		);
	}
}
