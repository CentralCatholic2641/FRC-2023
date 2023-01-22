package frc.team2641.frc2023;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PowerDistribution;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team2641.frc2023.subsystems.Drivetrain;
import frc.team2641.frc2023.subsystems.Shoulder;
import frc.team2641.frc2023.telemetry.LogController;
import frc.team2641.frc2023.telemetry.ShuffleboardController;
import frc.team2641.lib.control.Buttons.Gamepad;
import frc.team2641.lib.limelight.Limelight;

public class Robot extends LoggedRobot {
  Command autoCommand;

  public static RobotContainer robotContainer;
  private static Field2d field = new Field2d();
  private static LogController logController = LogController.getInstance();
  private static ShuffleboardController shuffleboardController = ShuffleboardController.getInstance();
  private static Limelight limelight = Limelight.getInstance();
  private static PowerDistribution pdh = new PowerDistribution(Constants.CAN.PDH, PowerDistribution.ModuleType.kRev);
  private static PneumaticHub ph = new PneumaticHub(Constants.CAN.PH);
  private Drivetrain drivetrain = Drivetrain.getInstance();
  private Shoulder shoulder = Shoulder.getInstance();

  @Override
  public void robotInit() {
    if (isReal())
      Constants.currentMode = Constants.Mode.REAL;
    else
      Constants.currentMode = Constants.Mode.SIM;

    robotContainer = new RobotContainer();
    SmartDashboard.putData(field);
    logController.start();
    SmartDashboard.putNumber("steeringAdjust", 0);
    SmartDashboard.putNumber("distanceAdjust", 0);
    shoulder.set(2048);

    Logger logger = Logger.getInstance();

    logger.recordMetadata("ProjectName", "ChargedUp2023");

    switch (Constants.currentMode) {
      case REAL:
        logger.addDataReceiver(new WPILOGWriter("/media/sda1/"));
        logger.addDataReceiver(new NT4Publisher());
        break;
      case SIM:
        logger.addDataReceiver(new WPILOGWriter(""));
        logger.addDataReceiver(new NT4Publisher());
        break;
      case REPLAY:
        setUseTiming(false);
        String logPath = LogFileUtil.findReplayLog();
        logger.setReplaySource(new WPILOGReader(logPath));
        logger.addDataReceiver(new WPILOGWriter(LogFileUtil.addPathSuffix(logPath, "_sim")));
        break;
    }

    logger.start();
  }

  @Override
  public void robotPeriodic() {
    if (robotContainer.driver.getRawButton(Gamepad.leftBumper)) {
      robotContainer.driverShift = true;
    } else {
      robotContainer.driverShift = false;
    }

    CommandScheduler.getInstance().run();

    if (limelight.hasPose())
      drivetrain.resetPose(limelight.getPose());
    field.setRobotPose(drivetrain.getPose());
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

    if (autoCommand != null)
      autoCommand.schedule();
  }

  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
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

  public static PowerDistribution getPDH() {
    return pdh;
  }

  public static PneumaticHub getPH() {
    return ph;
  }

  public static Field2d getField() {
    return field;
  }
}