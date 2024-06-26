// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team2641.resurgence2023.subsystems.Arm;
import frc.team2641.resurgence2023.telemetry.LogController;
import frc.team2641.lib.control.Buttons.Gamepad;
import frc.team2641.lib.limelight.Limelight;
import frc.team2641.lib.limelight.ControlMode.CamMode;

public class Robot extends TimedRobot {
  Command autoCommand;

  private static PowerDistribution pdh;

  private Arm arm;

  private LogController logController;
  private Limelight limelight;

  public static RobotContainer robotContainer;

  @Override
  public void robotInit() {
    pdh = new PowerDistribution(Constants.CAN.PDH, PowerDistribution.ModuleType.kRev);

    arm = Arm.getInstance();

    logController = LogController.getInstance();
    limelight = Limelight.getInstance();

    CameraServer.startAutomaticCapture(0);
    CameraServer.startAutomaticCapture(1);

    robotContainer = new RobotContainer();
    logController.start();

    arm.resetEncoders();

    limelight.setCamMode(CamMode.kDriver);
  }

  @Override
  public void robotPeriodic() {
    if (robotContainer.driver.getRawButton(Gamepad.leftBumper)) {
      robotContainer.driverTankShift = true;
    } else {
      robotContainer.driverTankShift = false;
    }

    if (robotContainer.driver.getRawButton(Gamepad.rightBumper)) {
      robotContainer.driverSlowShift = true;
    } else {
      robotContainer.driverSlowShift = false;
    }

    if (robotContainer.operator.getRawButton(Gamepad.leftBumper)) {
      robotContainer.operatorShift = true;
    } else {
      robotContainer.operatorShift = false;
    }

    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
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
}