// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.frc2023;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.team2641.frc2023.commands.*;
import frc.team2641.frc2023.subsystems.*;
import frc.team2641.lib.control.Gamepad;

public class RobotContainer {
	private Drivetrain drivetrain = Drivetrain.getInstance();
	private Shoulder shoulder = Shoulder.getInstance();
	private Elbow elbow = Elbow.getInstance();
	private Wrist wrist = Wrist.getInstance();

	public Gamepad driver = new Gamepad(Constants.Controllers.driver);

	public Gamepad operator = new Gamepad(Constants.Controllers.operator);
	public POVButton operatorMiddleRow = new POVButton(operator, 90);
	public POVButton operatorIntake = new POVButton(operator, 0);
	public POVButton operatorStow = new POVButton(operator, 180);

	public Joystick joystick = new Joystick(Constants.Controllers.joystick);

	public boolean controllerShift = false;
	public boolean driverShift = false;

	public RobotContainer() {
		configureButtonBindings();
		drivetrain.setDefaultCommand(new Drive());
		shoulder.setDefaultCommand(new MoveShoulder());
		elbow.setDefaultCommand(new MoveElbow());
		wrist.setDefaultCommand(new MoveWrist());
	}

	private void configureButtonBindings() {
		operator.aButton().toggleOnTrue(new ToggleClaw());
		operator.rightBumper().onTrue(new ResetEncoders());
		operatorMiddleRow.onTrue(new SetArm(Constants.Arm.Positions.middleRow));
		operatorStow.onTrue(new SetArm(Constants.Arm.Positions.start));
		operatorIntake.onTrue(new SetArm(Constants.Arm.Positions.intake));
		operator.xButton().onTrue(new FlipSide());
	}
}