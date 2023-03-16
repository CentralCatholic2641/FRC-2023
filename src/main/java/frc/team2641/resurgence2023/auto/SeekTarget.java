// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2641.resurgence2023.Constants.Pipelines;
import frc.team2641.resurgence2023.subsystems.Drivetrain;
import frc.team2641.lib.limelight.Limelight;

public class SeekTarget extends CommandBase {
  private Drivetrain drivetrain = Drivetrain.getInstance();
  private Limelight limelight = Limelight.getInstance();

  private double kPaim = -0.055;
  private double kPdistance = -0.05;
  private double kIdistance = -0.0002;
  private double kDdistance = -0.015;
  private double minAim = 0.025;

  private double distanceErrorI = 0.0;
  private double prevDistanceError = 0.0;

  public SeekTarget() {
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    limelight.setPipeline(Pipelines.AprilTag);
    distanceErrorI = 0.0;
    prevDistanceError = 0.0;
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

    if (Math.abs(distanceAdjust) < 0.25 && Math.abs(steeringAdjust) < 0.05)
      end(false);
    else { 
      System.out.println("Steering: " + steeringAdjust + " Distance: " + distanceAdjust);
      drivetrain.aDriveUnlimited(distanceAdjust, steeringAdjust);
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
