package frc.team2641.frc2023;

import frc.team2641.frc2023.commands.*;
import frc.team2641.frc2023.subsystems.*;
import frc.team2641.lib.control.Gamepad;

public class RobotContainer {
	private DrivingSubsystem drivingSubsystem = DrivingSubsystem.getInstance();

	public Gamepad driver = new Gamepad(Constants.Controllers.driver);
	public Gamepad controller = new Gamepad(Constants.Controllers.controller);

	public boolean controllerShift = false;
	public boolean driverShift = false;

	public RobotContainer() {
		configureButtonBindings();
		drivingSubsystem.setDefaultCommand(new Drive());
	}

	private void configureButtonBindings() {
		driver.A().toggleOnTrue(new ToggleClaw());
	}
}