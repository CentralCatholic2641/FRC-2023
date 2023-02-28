// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.frc2023.subsystems;

import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.frc2023.Constants;
import frc.team2641.frc2023.auto.ArmSequences;
import java.util.HashMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.auto.PIDConstants;
import com.pathplanner.lib.auto.RamseteAutoBuilder;

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

  private DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

  private AHRS ahrs = new AHRS();
  private DifferentialDriveOdometry odometry;
  private DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(
      Constants.Drive.kTrackwidthMeters);

  private Pose2d pose;

  private HashMap<String, Command> pathEventMap = new HashMap<>();

  private RamseteAutoBuilder pathFollower = new RamseteAutoBuilder(
      this::getPose,
      this::resetPose,
      new RamseteController(),
      this.kinematics,
      new SimpleMotorFeedforward(
          Constants.Drive.ksVolts,
          Constants.Drive.kvVoltSecondsPerMeter,
          Constants.Drive.kaVoltSecondsSquaredPerMeter),
      this::getWheelSpeeds,
      new PIDConstants(Constants.Drive.PID.kP, Constants.Drive.PID.kI, Constants.Drive.PID.kD),
      this::tDriveVolts,
      pathEventMap,
      true,
      this);

  private Drivetrain() {
    init(leftMaster);
    init(leftSlave1);
    init(leftSlave2);
    init(rightMaster);
    init(rightSlave1);
    init(rightSlave2);

    leftSlave1.follow(leftMaster);
    leftSlave2.follow(leftMaster);
    rightSlave1.follow(rightMaster);
    rightSlave2.follow(rightMaster);

    leftMaster.setInverted(false);
    rightMaster.setInverted(true);
    leftSlave1.setInverted(InvertType.FollowMaster);
    leftSlave2.setInverted(InvertType.FollowMaster);
    rightSlave1.setInverted(InvertType.FollowMaster);
    rightSlave2.setInverted(InvertType.FollowMaster);

    leftMaster.configOpenloopRamp(Constants.Drive.rampSpeed);
    leftSlave1.configOpenloopRamp(Constants.Drive.rampSpeed);
    leftSlave2.configOpenloopRamp(Constants.Drive.rampSpeed);
    rightMaster.configOpenloopRamp(Constants.Drive.rampSpeed);
    rightSlave1.configOpenloopRamp(Constants.Drive.rampSpeed);
    rightSlave2.configOpenloopRamp(Constants.Drive.rampSpeed);

    odometry = new DifferentialDriveOdometry(
        getAngle(), getLeftEncoder(), getRightEncoder());

    ahrs.enableLogging(true);

    // pathEventMap.put("intake", ArmSequences.Intake());
    // pathEventMap.put("scoreTop", ArmSequences.ScoreTop());
    pathEventMap.put("intake", new PrintCommand("intake"));
    pathEventMap.put("scoreTop", new PrintCommand("scoreTop"));
  }

  public void aDrive(double speed, double rotation) {
    drive.arcadeDrive(Constants.Drive.driveRateLimiter.calculate(speed * Constants.Drive.maxDrive),
        Constants.Drive.rotationRateLimiter.calculate(-rotation * Constants.Drive.maxSteer), true);
  }

  public void aDriveUnlimited(double speed, double rotation) {
    drive.arcadeDrive(speed, -rotation, true);
  }

  public void tDrive(double left, double right) {
    drive.tankDrive(Constants.Drive.driveRateLimiter.calculate(left * Constants.Drive.maxDrive),
        Constants.Drive.driveRateLimiter.calculate(right * Constants.Drive.maxDrive), true);
  }

  public void tDriveVolts(double leftVolts, double rightVolts) {
    leftMaster.set(ControlMode.PercentOutput, leftVolts / 110);
    rightMaster.set(ControlMode.PercentOutput, rightVolts / 110);
    drive.feed();
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
    double value = leftMaster.getSelectedSensorPosition() / Constants.Drive.encoderToMeters;
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
    return ahrs.getRotation2d();
  }

  public double getPitch() {
    return ahrs.getPitch();
  }

  public void zeroHeading() {
    ahrs.reset();
    ahrs.zeroYaw();
  }

  public void calibrate() {
    ahrs.calibrate();
  }

  private void init(WPI_TalonFX talon) {
    talon.configFactoryDefault();
    TalonFXConfiguration toApply = new TalonFXConfiguration();
    talon.setNeutralMode(NeutralMode.Brake);
    talon.configAllSettings(toApply);
    talon.setSelectedSensorPosition(0);
  }

  public DifferentialDriveKinematics getKinematics() {
    return kinematics;
  }

  public Command followTrajectoryCommand(String trajectory, boolean reset) {
    PathPlannerTrajectory traj = PathPlanner.loadPath(trajectory, new PathConstraints(
        Constants.Drive.kMaxSpeedMetersPerSecond, Constants.Drive.kMaxAccelerationMetersPerSecondSquared));

    if (reset) {
      resetPose(traj.getInitialPose());
      pathFollower.resetPose(traj);
    }
    return pathFollower.fullAuto(traj);
  }

  public Command followTrajectoryCommand(PathPlannerTrajectory trajectory, boolean reset) {
    if (reset) {
      resetPose(trajectory.getInitialPose());
      pathFollower.resetPose(trajectory);
    }
    return pathFollower.fullAuto(trajectory);
  }

  @Override
  public void periodic() {
    updatePose();
  }
}