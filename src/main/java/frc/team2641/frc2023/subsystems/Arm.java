// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.frc2023.Constants;
import frc.team2641.frc2023.helpers.ArmPosition;

public class Arm extends SubsystemBase {

  private static Arm instance = null;

  public static Arm getInstance() {
    if (instance == null)
      instance = new Arm();
    return instance;
  }

  private Shoulder shoulder = Shoulder.getInstance();
  private Elbow elbow = Elbow.getInstance();
  private Wrist wrist = Wrist.getInstance();
  private Claw claw = Claw.getInstance();

  private ArmPosition position;

  public Arm() {
  }

  public void set(ArmPosition position) {
    this.position = position;
    shoulder.setPos(position.shoulder);
    elbow.setPos(position.elbow);
    wrist.setPos(position.wrist);
    if (position.clawOpen)
      claw.release();
    else
      claw.clamp();
  }

  public ArmPosition get() {
    return position;
  }

  public void reset() {
    set(Constants.Arm.Positions.start);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
