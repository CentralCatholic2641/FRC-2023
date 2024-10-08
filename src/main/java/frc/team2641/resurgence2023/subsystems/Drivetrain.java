// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.subsystems;

import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.resurgence2023.Constants;
import frc.team2641.resurgence2023.Robot;
import frc.team2641.resurgence2023.auto.ArmSequences;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

public class Drivetrain extends SubsystemBase {
  private static Drivetrain instance = null;

  public static Drivetrain getInstance() {
    if (instance == null)
      instance = new Drivetrain();
    return instance;
  }

  private WPI_TalonFX leftMaster = new WPI_TalonFX(Constants.CAN.leftMaster);
  private WPI_TalonFX leftSlave1 = new WPI_TalonFX(Constants.CAN.leftSlave1);
  private WPI_TalonFX leftSlave2 = new WPI_TalonFX(Constants.CAN.leftSlave2);

  private WPI_TalonFX rightMaster = new WPI_TalonFX(Constants.CAN.rightMaster);
  private WPI_TalonFX rightSlave1 = new WPI_TalonFX(Constants.CAN.rightSlave1);
  private WPI_TalonFX rightSlave2 = new WPI_TalonFX(Constants.CAN.rightSlave2);

  private MotorControllerGroup leftGroup = new MotorControllerGroup(leftMaster, leftSlave1, leftSlave2);
  private MotorControllerGroup rightGroup = new MotorControllerGroup(rightMaster, rightSlave1, rightSlave2);

  private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);

  private AHRS ahrs = new AHRS();
  private DifferentialDriveOdometry odometry;
  private DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(
      Constants.Drive.kTrackwidthMeters);

  private Pose2d pose;

  private HashMap<String, Command> pathEventMap = new HashMap<>();

  private double driveLimit = Constants.Drive.maxDrive;
  private double steerLimit = Constants.Drive.maxSteer;

  private Drivetrain() {
    init(leftMaster);
    init(leftSlave1);
    init(leftSlave2);
    init(rightMaster);
    init(rightSlave1);
    init(rightSlave2);

    // leftMaster.setSensorPhase(true);
    // leftSlave1.setSensorPhase(true);
    // leftSlave2.setSensorPhase(true);
    // rightMaster.setSensorPhase(false);
    // rightSlave1.setSensorPhase(false);
    // rightSlave2.setSensorPhase(false);

    // leftMaster.setInverted(false);
    // leftSlave1.setInverted(InvertType.FollowMaster);
    // leftSlave2.setInverted(InvertType.FollowMaster);
    // rightMaster.setInverted(true);
    // rightSlave1.setInverted(InvertType.FollowMaster);
    // rightSlave2.setInverted(InvertType.FollowMaster);
    
    leftSlave1.follow(leftMaster);
    leftSlave2.follow(leftMaster);
    rightSlave1.follow(rightMaster);
    rightSlave2.follow(rightMaster);

    odometry = new DifferentialDriveOdometry(
        getAngle(), getLeftEncoder(), getRightEncoder());

    ahrs.enableLogging(true);

    pathEventMap.put("intake", ArmSequences.AutoIntake());
    pathEventMap.put("scoreTop", ArmSequences.ScoreTop());

    configBrakes(Constants.Drive.brakes);
    configRamps(Constants.Drive.rampSpeed);
    configDriveLimit(Constants.Drive.maxDrive);
    configSteerLimit(Constants.Drive.maxSteer);
  }

  public void aDrive(double speed, double rotation) {
    double speedOut = Constants.Drive.driveRateLimiter.calculate(((0.35 - driveLimit) * Robot.robotContainer.driver.getRightTrigger() + driveLimit) * -speed);
    double rotateOut = Constants.Drive.rotationRateLimiter.calculate(((0.35 - steerLimit) * Robot.robotContainer.driver.getRightTrigger() + steerLimit) * rotation);

    if (Math.abs(speedOut) >= 0.01 || Math.abs(rotateOut) >= 0.01) {
      drive.arcadeDrive( rotateOut, speedOut, true);
    } else {
      drive.arcadeDrive(0, 0);
    }
  }

  public void aDriveUnlimited(double speed, double rotation) {
    drive.arcadeDrive(-speed, -rotation, true);
  }

  public void tDrive(double left, double right) {
    drive.tankDrive(Constants.Drive.driveRateLimiter.calculate(left * driveLimit),
        Constants.Drive.driveRateLimiter.calculate(right * steerLimit), true);
  }

  public void tDriveVolts(double rightVolts, double leftVolts) {
    leftGroup.setVoltage(-leftVolts);
    rightGroup.setVoltage(rightVolts);
    drive.feed();

    SmartDashboard.putNumber("left", -leftVolts);
    SmartDashboard.putNumber("right", rightVolts);
  }

  public void configBrakes(boolean brakesOn) {
    NeutralMode input;
    if (brakesOn)
      input = NeutralMode.Brake;
    else
      input = NeutralMode.Coast;

    leftMaster.setNeutralMode(input);
    leftSlave1.setNeutralMode(input);
    leftSlave2.setNeutralMode(input);
    rightMaster.setNeutralMode(input);
    rightSlave1.setNeutralMode(input);
    rightSlave2.setNeutralMode(input);
  }

  public void configRamps(double rampSpeed) {
    leftMaster.configOpenloopRamp(rampSpeed);
    leftSlave1.configOpenloopRamp(rampSpeed);
    leftSlave2.configOpenloopRamp(rampSpeed);
    rightMaster.configOpenloopRamp(rampSpeed);
    rightSlave1.configOpenloopRamp(rampSpeed);
    rightSlave2.configOpenloopRamp(rampSpeed);
    leftMaster.configClosedloopRamp(rampSpeed);
    leftSlave1.configClosedloopRamp(rampSpeed);
    leftSlave2.configClosedloopRamp(rampSpeed);
    rightMaster.configClosedloopRamp(rampSpeed);
    rightSlave1.configClosedloopRamp(rampSpeed);
    rightSlave2.configClosedloopRamp(rampSpeed);
  }

  public void configDriveLimit(double driveLimit) {
    this.driveLimit = driveLimit;
  }

  public void configSteerLimit(double steerLimit) {
    this.steerLimit = steerLimit;
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftEncoder(),
        getRightEncoder());
  }

  public DifferentialDriveOdometry getOdometry() {
    return odometry;
  }

  public void resetPose(Pose2d pose) {
    resetEncoders();
    this.pose = pose;
    odometry.resetPosition(
        getAngle(), getLeftEncoder(), getRightEncoder(), pose);
  }

  public void updatePose() {
    this.pose = odometry.update(getAngle(), getLeftEncoder(), getRightEncoder());
  }

  public Pose2d getPose() {
    return pose;
  }

  public double getLeftEncoder() {
    double value = -leftMaster.getSelectedSensorPosition() / Constants.Drive.encoderToMeters;
    return value;
  }

  public double getRightEncoder() {
    double value = rightMaster.getSelectedSensorPosition() / Constants.Drive.encoderToMeters;
    return value;
  }

  public void resetEncoders() {
    leftMaster.setSelectedSensorPosition(0);
    rightMaster.setSelectedSensorPosition(0);
  }

  public Rotation2d getAngle() {
    return ahrs.getRotation2d().rotateBy(Rotation2d.fromDegrees(180));
  }

  public float getPitch() {
    return ahrs.getPitch();
  }

  public void zeroHeading() {
    ahrs.reset();
    ahrs.zeroYaw();
  }

  private void init(WPI_TalonFX talon) {
    talon.configFactoryDefault();
    TalonFXConfiguration toApply = new TalonFXConfiguration();
    talon.setNeutralMode(Constants.Drive.brakes ? NeutralMode.Brake : NeutralMode.Coast);
    talon.configAllSettings(toApply);
    talon.setSelectedSensorPosition(0);
  }

  public DifferentialDriveKinematics getKinematics() {
    return kinematics;
  }

  @Override
  public void periodic() {
    updatePose();
  }
}