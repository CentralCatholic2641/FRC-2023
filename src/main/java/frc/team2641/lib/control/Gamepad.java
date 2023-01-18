// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.lib.control;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class Gamepad extends XboxController {
  private final double joystickDeadband;

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
   * Construct an instance of a controller with a default deadband of 0.25.
   *
   * @param port The port index on the Driver Station that the controller is
   *             plugged into.
   */
  public Gamepad(int port) {
    this(port, 0.25);
  }

  public Trigger aButton() {
    return new Trigger(this::getAButton);
  }

  public Trigger bButton() {
    return new Trigger(this::getBButton);
  }

  public Trigger xButton() {
    return new Trigger(this::getXButton);
  }

  public Trigger yButton() {
    return new Trigger(this::getYButton);
  }

  public Trigger leftBumper() {
    return new Trigger(this::getLeftBumper);
  }

  public Trigger rightBumper() {
    return new Trigger(this::getRightBumper);
  }

  public Trigger start() {
    return new Trigger(this::getStartButton);
  }

  public Trigger back() {
    return new Trigger(this::getBackButton);
  }

  public Trigger leftStickButton() {
    return new Trigger(this::getLeftStickButton);
  }

  public Trigger rightStickButton() {
    return new Trigger(this::getRightStickButton);
  }

  public Trigger leftTriggerPressed() {
    return new Trigger(() -> this.getLeftTriggerAxis() > 0);
  }

  public Trigger rightTriggerPressed() {
    return new Trigger(() -> this.getRightTriggerAxis() > 0);
  }

  public Trigger povUp() {
    return new Trigger(() -> this.getPOV() == 0);
  }

  public Trigger povRight() {
    return new Trigger(() -> this.getPOV() == 90);
  }

  public Trigger povDown() {
    return new Trigger(() -> this.getPOV() == 180);
  }

  public Trigger povLeft() {
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

  public double getRightTrigger() {
    return this.calculateDeadband(super.getRightTriggerAxis());
  }

  public double getLeftTrigger() {
    return this.calculateDeadband(super.getLeftTriggerAxis());
  }

  protected double calculateDeadband(double value) {
    if (Math.abs(value) < this.joystickDeadband) {
      return 0.0;
    }
    return value;
  }
}