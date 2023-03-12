// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.resurgence2023.Constants;
import frc.team2641.resurgence2023.helpers.ArmPosition;

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

  private int side = 1;
  private ArmPosition position = Constants.Arm.Positions.start;
  private boolean auto = false;

  public Arm() {
  }

  public void set(ArmPosition position) {
    this.position = new ArmPosition(position.shoulder, position.elbow, position.wrist);
    shoulder.setPos(side * this.position.shoulder);
    elbow.setPos(side * this.position.elbow);
    wrist.setPos(side * this.position.wrist);
  }

  public ArmPosition get() {
    return position;
  }

  public boolean atPosition() {
    boolean value = true;

    if (shoulder.getEncoder() > side * position.shoulder + side * 6000
        || shoulder.getEncoder() < side * position.shoulder - side * 6000)
      value = false;
    if (elbow.getEncoder() > side * position.elbow + side * 3000
        || elbow.getEncoder() < side * position.elbow - side * 3000)
      value = false;
    if (wrist.getEncoder() > side * position.wrist + side * 1000
        || wrist.getEncoder() < side * position.wrist - side * 1000)
      value = false;

    return value;
  }

  public void reset() {
    set(Constants.Arm.Positions.start);
  }

  public void resetEncoders() {
    shoulder.setEncoder(0);
    elbow.setEncoder(0);
    wrist.setEncoder(0);
  }

  public void flipSide() {
    // setAuto(true);
    this.side = this.side * -1;
    this.set(position);
  }

  public boolean isAuto() {
    return auto;
  }

  public void setAuto(boolean auto) {
    this.auto = auto;
  }

  @Override
  public void periodic() {
    // System.out.println("shoulder: " + shoulder.getEncoder() + " elbow: " +
    // elbow.getEncoder() + " wrist: " + wrist.getEncoder());
    // System.out.println(this.position);
  }
}
