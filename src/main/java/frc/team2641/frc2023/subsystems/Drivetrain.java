package frc.team2641.frc2023.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.frc2023.Constants;
import frc.team2641.lib.motors.TalonFX;

import com.kauailabs.navx.frc.AHRS;

public class Drivetrain extends SubsystemBase {

  private static Drivetrain instance = null;

  public static Drivetrain getInstance() {
    if (instance == null)
      instance = new Drivetrain();
    return instance;
  }

  private TalonFX leftMotor1 = TalonFX.getInstance(Constants.CAN.leftMotor1);
  private TalonFX leftMotor2 = TalonFX.getInstance(Constants.CAN.leftMotor2);
  private TalonFX leftMotor3 = TalonFX.getInstance(Constants.CAN.leftMotor3);

  private TalonFX rightMotor1 = TalonFX.getInstance(Constants.CAN.rightMotor1);
  private TalonFX rightMotor2 = TalonFX.getInstance(Constants.CAN.rightMotor2);
  private TalonFX rightMotor3 = TalonFX.getInstance(Constants.CAN.rightMotor3);

  private MotorControllerGroup leftGroup = new MotorControllerGroup(leftMotor1.getTalon(), leftMotor2.getTalon(),
      leftMotor3.getTalon());
  private MotorControllerGroup rightGroup = new MotorControllerGroup(rightMotor1.getTalon(), rightMotor2.getTalon(),
      rightMotor3.getTalon());

  private TalonFX leftEncoder = leftMotor2;
  private TalonFX rightEncoder = rightMotor2;

  private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);

  private AHRS ahrs = new AHRS();
  private DifferentialDriveOdometry odometry;
  private Pose2d pose;

  private Drivetrain() {
    configBrakes(Constants.Drive.brakes);
    configRamps(Constants.Drive.rampSpeed);

    odometry = new DifferentialDriveOdometry(
        getAngle(), getLeftEncoder(), getRightEncoder());
    pose = odometry.getPoseMeters();
  }

  public void aDrive(double rotation, double speed) {
    drive.arcadeDrive(rotation * Constants.Drive.maxDrive, -speed * Constants.Drive.maxDrive, true);
  }

  public void tDrive(double left, double right) {
    drive.tankDrive(left * Constants.Drive.maxDrive, right * Constants.Drive.maxDrive, true);
  }

  public void tDriveVolts(double leftVolts, double rightVolts) {
    System.out.println("left:" + leftVolts + " right: " + rightVolts);
    leftGroup.setVoltage(leftVolts);
    rightGroup.setVoltage(rightVolts);
    drive.feed();
  }

  public void halt() {
    configRamps(Constants.Drive.rampSpeed);
    configBrakes(Constants.Drive.brakes);
    leftMotor1.stop();
    leftMotor2.stop();
    leftMotor3.stop();
    rightMotor1.stop();
    rightMotor2.stop();
    rightMotor3.stop();
  }

  public void configBrakes(boolean brakesOn) {
    leftMotor1.configBrakes(brakesOn);
    leftMotor2.configBrakes(brakesOn);
    leftMotor3.configBrakes(brakesOn);
    rightMotor1.configBrakes(brakesOn);
    rightMotor2.configBrakes(brakesOn);
    rightMotor3.configBrakes(brakesOn);
  }

  public void configRamps(double driveRampSpeed) {
    leftMotor1.configRamps(driveRampSpeed);
    leftMotor2.configRamps(driveRampSpeed);
    leftMotor3.configRamps(driveRampSpeed);
    rightMotor1.configRamps(driveRampSpeed);
    rightMotor2.configRamps(driveRampSpeed);
    rightMotor3.configRamps(driveRampSpeed);
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
    return leftEncoder.getEncoder() / Constants.Drive.oneRotation / Constants.Drive.gearRatio;
  }

  public double getRightEncoder() {
    return rightEncoder.getEncoder() / Constants.Drive.oneRotation / Constants.Drive.gearRatio;
  }

  public void resetEncoders() {
    leftEncoder.resetEncoder();
    rightEncoder.resetEncoder();
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