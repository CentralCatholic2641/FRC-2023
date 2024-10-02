// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team2641.resurgence2023.Constants;
import frc.team2641.resurgence2023.commands.SetArm;
import frc.team2641.resurgence2023.commands.Wait;
import frc.team2641.resurgence2023.helpers.ArmPosition;
import frc.team2641.resurgence2023.subsystems.Arm;
import frc.team2641.resurgence2023.subsystems.Elbow;
import frc.team2641.resurgence2023.subsystems.Intake;
import frc.team2641.resurgence2023.subsystems.Shoulder;

public class ArmSequences {
	private static Intake intake = Intake.getInstance();
	private static Arm arm = Arm.getInstance();
	private static Shoulder shoulder = Shoulder.getInstance();
	private static Elbow elbow = Elbow.getInstance();

	public static Command Stow() {
		return Commands.sequence(
			new SetArm(new ArmPosition((int) shoulder.getEncoder(), 0, 0)),
			new SetArm(Constants.Arm.Positions.start)
		);
	}

	public static Command Intake() {
		return new SetArm(Constants.Arm.Positions.intake);
	}

	public static Command AutoIntake() {
		return Commands.sequence(
				Commands.parallel(
						new SetArm(Constants.Arm.Positions.intake),
						Commands.sequence(
								new InstantCommand(() -> intake.forward(0.5), intake),
								new Wait(0.5),
								new InstantCommand(() -> intake.stop(), intake))),
				new Wait(0.1),
				new SetArm(Constants.Arm.Positions.start));
	}

	public static Command ScoreTop() {
		return Commands.sequence(
				new SetArm(Constants.Arm.Positions.topRowStart),
				new SetArm(Constants.Arm.Positions.topRow),
				new InstantCommand(() -> intake.reverse(0.15), intake),
				new Wait(0.1),
				new SetArm(Constants.Arm.Positions.start));
	}

	public static Command MoveToScoreTop() {
		return Commands.sequence(
				new SetArm(Constants.Arm.Positions.topRowStart),
				new SetArm(Constants.Arm.Positions.topRow));
	}

	public static Command ScoreMid() {
		return Commands.sequence(
				new SetArm(Constants.Arm.Positions.middleRowStart),
				new SetArm(Constants.Arm.Positions.middleRow),
				new InstantCommand(() -> intake.reverse(0.15), intake),
				new Wait(0.1),
				new SetArm(Constants.Arm.Positions.start));
	}

	public static Command MoveToScoreMid() {
		return Commands.sequence(
			new SetArm(Constants.Arm.Positions.middleRowStart),
			new SetArm(Constants.Arm.Positions.middleRow)
		);
	}

	public static Command MoveToScoreBot() {
		return new SetArm(Constants.Arm.Positions.bottomRow);
	}

	public static Command ScoreBot() {
		return Commands.sequence(
				new SetArm(Constants.Arm.Positions.bottomRow),
				new InstantCommand(() -> intake.reverse(0.15), intake),
				new Wait(0.1),
				new SetArm(Constants.Arm.Positions.start));
	}

	public static Command MoveToSingle() {
		return new SetArm(Constants.Arm.Positions.singleSubstation);
	}

	public static Command MoveToDouble() {
		return Commands.sequence(
			new SetArm(Constants.Arm.Positions.doubleSubstationStart),
			new SetArm(Constants.Arm.Positions.doubleSubstation)
		);
	}
}
