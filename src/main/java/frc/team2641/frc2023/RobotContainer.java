package frc.team2641.frc2023;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team2641.frc2023.commands.*;
import frc.team2641.frc2023.subsystems.*;

public class RobotContainer {
	private DrivingSubsystem drivingSubsystem = DrivingSubsystem.getInstance();

	public XboxController driver = new XboxController(Constants.Controllers.driver);
	public JoystickButton driverClawButton = new JoystickButton(driver, Constants.GamepadButtons.aButton);

	public XboxController controller = new XboxController(Constants.Controllers.controller);

	public boolean controllerShift = false;
	public boolean driverShift = false;

	public RobotContainer() {
		configureButtonBindings();
		drivingSubsystem.setDefaultCommand(new Drive());
	}

	private void configureButtonBindings() {
		driverClawButton.toggleOnTrue(new ToggleClaw());
	}
}