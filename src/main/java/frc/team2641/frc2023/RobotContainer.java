package frc.team2641.frc2023;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.team2641.frc2023.commands.*;
import frc.team2641.frc2023.subsystems.*;
import frc.team2641.lib.control.Gamepad;

public class RobotContainer {
	private Drivetrain drivetrain = Drivetrain.getInstance();
	private Shoulder shoudler = Shoulder.getInstance();
	private Wrist wrist = Wrist.getInstance();

	public Gamepad driver = new Gamepad(Constants.Controllers.driver);
	public Joystick joystick = new Joystick(2);
	public Gamepad controller = new Gamepad(Constants.Controllers.controller);

	public boolean controllerShift = false;
	public boolean driverShift = false;

	public RobotContainer() {
		configureButtonBindings();
		drivetrain.setDefaultCommand(new Drive());
		drivetrain.setDefaultCommand(new MoveShoulder());
		wrist.setDefaultCommand(new MoveWrist());
	}

	private void configureButtonBindings() {
		driver.aButton().toggleOnTrue(new ToggleClaw());
	}
}