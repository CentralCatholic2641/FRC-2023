// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team2641.resurgence2023.Constants;
import frc.team2641.resurgence2023.commands.SetArm;
import frc.team2641.resurgence2023.commands.Wait;
import frc.team2641.resurgence2023.subsystems.Claw;

public class ArmSequences {
	private static Claw claw = Claw.getInstance();

	public static Command Stow() {
		return new SetArm(Constants.Arm.Positions.start);
	}

	public static Command Intake() {
		return new SetArm(Constants.Arm.Positions.intake);
	}

	public static Command ScoreTop() {
		return Commands.sequence(
				new SetArm(Constants.Arm.Positions.topRowStart),
				new SetArm(Constants.Arm.Positions.topRow),
				new InstantCommand(() -> claw.release(), claw),
				new Wait(0.1),
				Commands.parallel(
						Commands.sequence(
								new Wait(0.25),
								new InstantCommand(() -> claw.clamp(), claw)),
						new SetArm(Constants.Arm.Positions.start)));
	}

	public static Command MoveToScoreTop() {
		return Commands.sequence(
				new SetArm(Constants.Arm.Positions.topRowStart),
				new SetArm(Constants.Arm.Positions.topRow));
	}

	public static Command ScoreMid() {
		return Commands.sequence(
				new SetArm(Constants.Arm.Positions.middleRow),
				new InstantCommand(() -> claw.release(), claw),
				new Wait(0.1),
				Commands.parallel(
						Commands.sequence(
								new Wait(0.25),
								new InstantCommand(() -> claw.clamp(), claw)),
						new SetArm(Constants.Arm.Positions.start)));
	}

	public static Command MoveToScoreMid() {
		return new SetArm(Constants.Arm.Positions.middleRow);
	}

	public static Command MoveToScoreBot() {
		return new SetArm(Constants.Arm.Positions.bottomRow);
	}

	public static Command ScoreBot() {
		return Commands.sequence(
				new SetArm(Constants.Arm.Positions.bottomRow),
				new InstantCommand(() -> claw.release(), claw),
				new Wait(0.1),
				Commands.parallel(
						Commands.sequence(
								new Wait(0.25),
								new InstantCommand(() -> claw.clamp(), claw)),
						new SetArm(Constants.Arm.Positions.start)));
	}

	public static Command MoveToSingle() {
		return new SetArm(Constants.Arm.Positions.singleSubstation);
	}

	public static Command MoveToDouble() {
		return new SetArm(Constants.Arm.Positions.doubleSubstation);
	}
}
