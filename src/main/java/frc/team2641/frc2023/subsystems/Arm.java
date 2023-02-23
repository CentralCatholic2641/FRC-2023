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

  private int side = 1;
  private ArmPosition position;
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

    if (shoulder.getEncoder() > side * position.shoulder + 6000
        || shoulder.getEncoder() < side * position.shoulder - 6000)
      value = false;
    if (elbow.getEncoder() > side * position.elbow + 3000 || elbow.getEncoder() < side * position.elbow - 3000)
      value = false;
    if (wrist.getEncoder() > side * position.wrist + 500 || wrist.getEncoder() < side * position.wrist - 500)
      value = false;

    return value;
  }

  public void reset() {
    set(Constants.Arm.Positions.start);
  }

  public void flipSide() {
    // setAuto(true);
    this.side = this.side * -1;
    this.set(position);
  }

  // public void setAuto(boolean auto) {
  // shoulder.setAuto(auto);
  // elbow.setAuto(auto);
  // wrist.setAuto(auto);
  // }

  public boolean isAuto() {
    return auto;
  }

  public void setAuto(boolean auto) {
    this.auto = auto;
    shoulder.setAuto(auto);
    elbow.setAuto(auto);
    wrist.setAuto(auto);
  }

  @Override
  public void periodic() {
    System.out.println("shoulder: " + shoulder.getEncoder());
    System.out.println("elbow: " + elbow.getEncoder());
    System.out.println("wrist: " + wrist.getEncoder());
  }
}
