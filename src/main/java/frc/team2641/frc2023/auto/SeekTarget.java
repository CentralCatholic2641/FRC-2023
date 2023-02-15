// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.frc2023.Constants.Pipelines;
import frc.team2641.frc2023.subsystems.Drivetrain;
import frc.team2641.lib.limelight.Limelight;

public class SeekTarget extends CommandBase {
  private Drivetrain drivetrain = Drivetrain.getInstance();
  private Limelight limelight = Limelight.getInstance();

  private double kPaim = -0.03;
  private double kPdistance = 0.02;
  private double kIdistance = 0.0;
  private double kDdistance = 0.125;
  private double minAim = 0.025;

  private double distanceErrorI = 0.0;
  private double prevDistanceError = 0.0;

  public SeekTarget() {
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    limelight.setPipeline(Pipelines.SeekTarget);
  }

  @Override
  public void execute() {
    double tx = limelight.getRotationToTargetDeg();
    double ty = limelight.getVerticalToTargetDeg();
    boolean tv = limelight.isTargetFound();

    double headingError = -tx;
    double distanceErrorP = -ty;
    double steeringAdjust = 0;
    double distanceAdjust = 0;

    distanceErrorI += distanceErrorP;

    if (tv) {
      if (tx > 0.2) {
        steeringAdjust = kPaim * headingError - minAim;
      } else if (tx < -0.2) {
        steeringAdjust = kPaim * headingError + minAim;
      }
      distanceAdjust = (kPdistance * distanceErrorP) + (kIdistance * distanceErrorI)
          + (kDdistance * (distanceErrorP - prevDistanceError));
    } else {
      distanceAdjust = 0;
      steeringAdjust = 0;
      // steeringAdjust = 0.25;
    }

    // if (distanceAdjust < 0.25)
    // end(false);

    SmartDashboard.putNumber("distanceAdjust", distanceAdjust);
    SmartDashboard.putNumber("steeringAdjust", steeringAdjust);
    System.out.println("Steering: " + steeringAdjust + " Distance: " + distanceAdjust);
    drivetrain.aDriveUnlimited(distanceAdjust, steeringAdjust);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
