// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
// import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
// import frc.team2641.resurgence2023.commands.DriveFor;
import frc.team2641.resurgence2023.commands.DriveFor;

public class AutoSequences {
	public static Command NoAuto() {
		return new InstantCommand();
	}

	public static Command ScorePreload(String start) {
		return Commands.sequence(
			ArmSequences.ScoreTop(),
			new DriveFor(6)
		);
	}

	public static Command ScorePreloadOnly(String start) {
		return ArmSequences.ScoreTop();
	}

	public static Command ScorePreloadAutoBalance() {
		return Commands.sequence (
			ArmSequences.ScoreTop(),
			new DriveFor(8),
			new AutoBalance()
		);
	}
}
