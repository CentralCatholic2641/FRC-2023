package frc.team2641.frc2023.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.frc2023.Constants;
import frc.team2641.frc2023.Robot;
import frc.team2641.frc2023.subsystems.DrivingSubsystem;

public class Drive extends CommandBase {
  private DrivingSubsystem drivingSubsystem;

  public Drive() {
    this.drivingSubsystem = DrivingSubsystem.getInstance();
    addRequirements(this.drivingSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (Robot.robotContainer.driverShift == true) {
      double left = Robot.robotContainer.driver.getRawAxis(Constants.GamepadButtons.lyAxis);
      double right = Robot.robotContainer.driver.getRawAxis(Constants.GamepadButtons.ryAxis);
      drivingSubsystem.tDrive(-left, right);
    } else {
      double rotation = Robot.robotContainer.driver.getRawAxis(Constants.GamepadButtons.lxAxis);
      double drive = Robot.robotContainer.driver.getRawAxis(Constants.GamepadButtons.ryAxis);
      drivingSubsystem.aDrive(rotation, drive);
    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}