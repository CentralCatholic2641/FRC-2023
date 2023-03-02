// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.auto;

import edu.wpi.first.wpilibj2.command.Command;

public class AutoSequences {
	public static Command ScorePreloadCubePickupScorePrestagedConeDock(String start) {
		return FollowPath.get(start + "_Score Preload Score Prestaged Dock", true);
	}

	public static Command ScorePreloadCubePickupScorePrestagedCone(String start) {
		return FollowPath.get(start + "_Score Preload Score Prestaged", true);
	}

	public static Command ScorePreload(String start) {
		return FollowPath.get(start + "_Score Preload", true);
	}

	public static Command Mobility(String start) {
		return FollowPath.get(start + "_Mobility", true);
	}
}
