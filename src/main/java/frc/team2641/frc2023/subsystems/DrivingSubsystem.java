package frc.team2641.frc2023.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.frc2023.Constants;

import com.kauailabs.navx.frc.AHRS;

public class DrivingSubsystem extends SubsystemBase {

  private static DrivingSubsystem instance = null;

  public static DrivingSubsystem getInstance() {
    if (instance == null)
      instance = new DrivingSubsystem();
    return instance;
  }

  private WPI_TalonFX leftMotor1 = new WPI_TalonFX(Constants.Motors.leftMotor1);
  private WPI_TalonFX leftMotor2 = new WPI_TalonFX(Constants.Motors.leftMotor2);
  private WPI_TalonFX leftMotor3 = new WPI_TalonFX(Constants.Motors.leftMotor3);

  private WPI_TalonFX rightMotor1 = new WPI_TalonFX(Constants.Motors.rightMotor1);
  private WPI_TalonFX rightMotor2 = new WPI_TalonFX(Constants.Motors.rightMotor2);
  private WPI_TalonFX rightMotor3 = new WPI_TalonFX(Constants.Motors.rightMotor3);

  private MotorControllerGroup leftGroup = new MotorControllerGroup(leftMotor1, leftMotor2, leftMotor3);
  private MotorControllerGroup rightGroup = new MotorControllerGroup(rightMotor1, rightMotor2, rightMotor3);

  private WPI_TalonFX leftEncoder = leftMotor2;
  private WPI_TalonFX rightEncoder = rightMotor2;

  private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);

  private AHRS ahrs = new AHRS();
  private DifferentialDriveOdometry odometry;
  private Pose2d pose;

  private DrivingSubsystem() {
    leftMotor1.clearStickyFaults();
    leftMotor2.clearStickyFaults();
    leftMotor3.clearStickyFaults();
    rightMotor1.clearStickyFaults();
    rightMotor2.clearStickyFaults();
    rightMotor3.clearStickyFaults();

    configBrakes(Constants.MotorSpeeds.brakes);
    configRamps(Constants.MotorSpeeds.driveRampSpeed);

    odometry = new DifferentialDriveOdometry(
        getAngle(), getLeftEncoder(), getRightEncoder());
    pose = odometry.getPoseMeters();
  }

  public void aDrive(double rotation, double speed) {
    drive.arcadeDrive(rotation * 0.5, -speed * 0.5, true);
  }

  public void tDrive(double left, double right) {
    drive.tankDrive(left, right, true);
  }

  public void tDriveVolts(double leftVolts, double rightVolts) {
    System.out.println("left:" + leftVolts + " right: " + rightVolts);
    leftGroup.setVoltage(leftVolts);
    rightGroup.setVoltage(rightVolts);
    drive.feed();
  }

  public void halt() {
    configRamps(Constants.MotorSpeeds.driveRampSpeed);
    configBrakes(Constants.MotorSpeeds.brakes);
    leftMotor1.stopMotor();
    leftMotor2.stopMotor();
    leftMotor3.stopMotor();
    rightMotor1.stopMotor();
    rightMotor2.stopMotor();
    rightMotor3.stopMotor();
  }

  public void configBrakes(boolean brakesOn) {
    NeutralMode input;
    if (brakesOn)
      input = NeutralMode.Brake;
    else
      input = NeutralMode.Coast;

    leftMotor1.setNeutralMode(input);
    leftMotor2.setNeutralMode(input);
    leftMotor3.setNeutralMode(input);
    rightMotor1.setNeutralMode(input);
    rightMotor2.setNeutralMode(input);
    rightMotor3.setNeutralMode(input);
  }

  public void configRamps(double driveRampSpeed) {
    leftMotor1.configOpenloopRamp(driveRampSpeed);
    leftMotor2.configOpenloopRamp(driveRampSpeed);
    leftMotor3.configOpenloopRamp(driveRampSpeed);
    rightMotor1.configOpenloopRamp(driveRampSpeed);
    rightMotor2.configOpenloopRamp(driveRampSpeed);
    rightMotor3.configOpenloopRamp(driveRampSpeed);
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(leftEncoder.getSelectedSensorVelocity(),
        rightEncoder.getSelectedSensorVelocity());
  }

  public DifferentialDriveOdometry getOdometry() {
    return odometry;
  }

  public void resetPose(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(
        getAngle(), getLeftEncoder(), getRightEncoder(), pose);
  }

  public void updatePose() {
    pose = odometry.update(getAngle(), getLeftEncoder(), getRightEncoder());
  }

  public Pose2d getPose() {
    pose = odometry.getPoseMeters();
    return pose;
  }

  public double getLeftEncoder() {
    return leftEncoder.getSelectedSensorPosition() / Constants.oneRotation / 10.71;
  }

  public double getRightEncoder() {
    return rightEncoder.getSelectedSensorPosition() / Constants.oneRotation / 10.71;
  }

  public void resetEncoders() {
    leftEncoder.setSelectedSensorPosition(0.0);
    rightEncoder.setSelectedSensorPosition(0.0);
  }

  public Rotation2d getAngle() {
    return Rotation2d.fromDegrees(-ahrs.getAngle());
  }

  public void zeroHeading() {
    ahrs.reset();
  }

  @Override
  public void periodic() {
    updatePose();
  }
}