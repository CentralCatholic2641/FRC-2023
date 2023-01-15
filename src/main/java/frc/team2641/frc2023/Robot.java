package frc.team2641.frc2023;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team2641.frc2023.subsystems.DrivingSubsystem;
import frc.team2641.frc2023.telemetry.LogController;
import frc.team2641.frc2023.telemetry.ShuffleboardController;
import frc.team2641.lib.control.Buttons.Gamepad;
import frc.team2641.lib.limelight.Limelight;

public class Robot extends TimedRobot {
  Command autoCommand;

  public static RobotContainer robotContainer;
  public static final Field2d field = new Field2d();
  private static LogController logController = LogController.getInstance();
  private static ShuffleboardController shuffleboardController = ShuffleboardController.getInstance();
  private static Limelight limelight = Limelight.getInstance();

  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
    SmartDashboard.putData(field);
    field.setRobotPose(DrivingSubsystem.getInstance().getPose());
    logController.start();
  }

  @Override
  public void robotPeriodic() {
    if (robotContainer.driver.getRawButton(Gamepad.leftBumper)) {
      robotContainer.driverShift = true;
    } else {
      robotContainer.driverShift = false;
    }

    System.out.println(limelight.getAprilTagID());

    CommandScheduler.getInstance().run();

    // field.setRobotPose(DrivingSubsystem.getInstance().getPose());
    field.setRobotPose(limelight.getPose());
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    autoCommand = shuffleboardController.getAutonomousCommand();
    DrivingSubsystem.getInstance().configRamps(0);

    if (autoCommand != null)
      autoCommand.schedule();
  }

  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    DrivingSubsystem.getInstance().configRamps(Constants.Drive.rampSpeed);

    if (autoCommand != null)
      autoCommand.cancel();
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void simulationInit() {
  }

  @Override
  public void simulationPeriodic() {
  }
}