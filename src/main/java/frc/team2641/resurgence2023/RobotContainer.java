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
	public POVButton operatorIntake = new POVButton(operator, 0);
	public POVButton operatorBotRow = new POVButton(operator, 90);
	public POVButton operatorMidRow = new POVButton(operator, 180);
	public POVButton operatorTopRow = new POVButton(operator, 270);
	// public POVButton operatorStow = new POVButton(operator, 180);

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

		operatorIntake.onTrue(ArmSequences.Intake());
		operatorBotRow.onTrue(ArmSequences.MoveToScoreBot());
		operatorMidRow.onTrue(ArmSequences.MoveToScoreMid());
		operatorTopRow.onTrue(ArmSequences.MoveToScoreTop());
		// operatorStow.onTrue(ArmSequences.Stow());

		operator.yButton().onTrue(ArmSequences.MoveToSingle());
		operator.start().onTrue(ArmSequences.MoveToDouble());
		operator.bButton().onTrue(ArmSequences.Stow());
		operator.xButton().onTrue(new FlipSide());
	}
}