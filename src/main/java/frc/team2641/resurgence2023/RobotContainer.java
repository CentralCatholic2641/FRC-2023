// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.team2641.resurgence2023.auto.ArmSequences;
import frc.team2641.resurgence2023.commands.*;
import frc.team2641.resurgence2023.subsystems.*;
import frc.team2641.lib.control.Gamepad;

public class RobotContainer {
	private Drivetrain drivetrain = Drivetrain.getInstance();
	private Arm arm = Arm.getInstance();

	public Gamepad driver = new Gamepad(Constants.Controllers.driver);

	public Gamepad operator = new Gamepad(Constants.Controllers.operator);
	public POVButton operatorTopRow = new POVButton(operator, 270);
	public POVButton operatorMiddleRow = new POVButton(operator, 90);
	public POVButton operatorIntake = new POVButton(operator, 0);
	public POVButton operatorStow = new POVButton(operator, 180);

	public Joystick joystick = new Joystick(Constants.Controllers.joystick);

	public boolean driverTankShift = false;
	public boolean driverSlowShift = false;
	public boolean operatorShift = false;

	public RobotContainer() {
		configureButtonBindings();
		drivetrain.setDefaultCommand(new Drive());
		arm.setDefaultCommand(new MoveArm());
	}

	private void configureButtonBindings() {
		operator.aButton().toggleOnTrue(new ToggleClaw());
		operator.leftBumper().onTrue(new ResetEncoders());
		// operatorTopRow.onTrue(new SetArm(Constants.Arm.Positions.topRow));
		operatorTopRow.onTrue(ArmSequences.MoveToScoreTop());
		// operatorMiddleRow.onTrue(ArmSequences.ScoreMid());
		operatorMiddleRow.onTrue(ArmSequences.MoveToScoreMid());
		operatorStow.onTrue(ArmSequences.Stow());
		operatorIntake.onTrue(ArmSequences.Intake());
		// operator.xButton().onTrue(new FlipSide());
	}
}