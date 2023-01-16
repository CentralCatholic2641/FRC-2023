// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.lib.control;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class Gamepad extends XboxController {
  private final double joystickDeadband;

  protected CommandBase rumbleAnimationCommand;

  /**
   * Construct an instance of a controller.
   *
   * @param port             The port index on the Driver Station that the
   *                         controller is plugged into.
   * @param joystickDeadband The deadband for the joysticks.
   */
  public Gamepad(int port, double joystickDeadband) {
    super(port);
    this.joystickDeadband = joystickDeadband;
  }

  /**
   * Construct an instance of a controller.
   *
   * @param port The port index on the Driver Station that the controller is
   *             plugged into.
   */
  public Gamepad(int port) {
    this(port, 0.25);
  }

  public Trigger A() {
    return new Trigger(this::getAButton);
  }

  public Trigger B() {
    return new Trigger(this::getBButton);
  }

  public Trigger X() {
    return new Trigger(this::getXButton);
  }

  public Trigger Y() {
    return new Trigger(this::getYButton);
  }

  public Trigger LB() {
    return new Trigger(this::getLeftBumper);
  }

  public Trigger RB() {
    return new Trigger(this::getRightBumper);
  }

  public Trigger START() {
    return new Trigger(this::getStartButton);
  }

  public Trigger BACK() {
    return new Trigger(this::getBackButton);
  }

  public Trigger LS() {
    return new Trigger(this::getLeftStickButton);
  }

  public Trigger RS() {
    return new Trigger(this::getRightStickButton);
  }

  public Trigger LT() {
    return new Trigger(() -> this.getLeftTriggerAxis() > 0);
  }

  public Trigger RT() {
    return new Trigger(() -> this.getRightTriggerAxis() > 0);
  }

  public Trigger DUp() {
    return new Trigger(() -> this.getPOV() == 0);
  }

  public Trigger DRight() {
    return new Trigger(() -> this.getPOV() == 90);
  }

  public Trigger DDown() {
    return new Trigger(() -> this.getPOV() == 180);
  }

  public Trigger DLeft() {
    return new Trigger(() -> this.getPOV() == 270);
  }

  @Override
  public double getLeftX() {
    return this.calculateDeadband(super.getLeftX());
  }

  @Override
  public double getLeftY() {
    return this.calculateDeadband(super.getLeftY());
  }

  @Override
  public double getRightX() {
    return this.calculateDeadband(super.getRightX());
  }

  @Override
  public double getRightY() {
    return this.calculateDeadband(super.getRightY());
  }

  protected double calculateDeadband(double value) {
    if (Math.abs(value) < this.joystickDeadband) {
      return 0.0;
    }
    return value;
  }
}