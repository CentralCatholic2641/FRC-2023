// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

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

  private ArmPosition position;

  public Arm() {
  }

  public void set(ArmPosition position) {
    this.position = position;
    shoulder.setPos(position.shoulder);
    elbow.setPos(position.elbow);
    wrist.setPos(position.wrist);
  }

  public ArmPosition get() {
    return position;
  }

  public boolean atPosition() {
    boolean value = true;
    if (shoulder.getEncoder() != position.shoulder)
      value = false;
    if (elbow.getEncoder() != position.elbow)
      value = false;
    if (wrist.getEncoder() != position.wrist)
      value = false;
    return value;
  }

  public void reset() {
    set(Constants.Arm.Positions.start);
  }

  @Override
  public void periodic() {
  }
}
