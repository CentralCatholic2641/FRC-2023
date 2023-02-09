package frc.team2641.frc2023;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team2641.frc2023.commands.*;
import frc.team2641.frc2023.subsystems.*;
import frc.team2641.lib.control.Gamepad;

public class RobotContainer {
	private Drivetrain drivetrain = Drivetrain.getInstance();
	private Shoulder shoulder = Shoulder.getInstance();

	public Gamepad driver = new Gamepad(Constants.Controllers.driver);
	public Joystick joystick = new Joystick(2);
	public Gamepad controller = new Gamepad(Constants.Controllers.controller);

	public JoystickButton trigger = new JoystickButton(joystick, 1);
	public JoystickButton button2 = new JoystickButton(joystick, 2);

	public boolean controllerShift = false;
	public boolean driverShift = false;

	public RobotContainer() {
		configureButtonBindings();
		drivetrain.setDefaultCommand(new Drive());
		shoulder.setDefaultCommand(new MoveArm());
	}

	private void configureButtonBindings() {
		driver.aButton().toggleOnTrue(new ToggleClaw());
		trigger.onTrue(new SetArm());
		button2.onTrue(new ClawCommand(true)).onFalse(new ClawCommand(false));
	}
}