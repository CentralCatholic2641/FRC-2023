package frc.team2641.frc2023.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2641.frc2023.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
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

  private DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

  private AHRS ahrs = new AHRS();
  private DifferentialDriveOdometry odometry;
  private Pose2d pose;

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

    odometry = new DifferentialDriveOdometry(
        getAngle(), getLeftEncoder(), getRightEncoder());
    pose = odometry.getPoseMeters();
  }

  public void aDrive(double speed, double rotation) {
    drive.arcadeDrive(Constants.Drive.rateLimiter.calculate(-speed * Constants.Drive.maxDrive),
        rotation * Constants.Drive.maxDrive, true);
  }

  public void tDrive(double left, double right) {
    drive.tankDrive(left * Constants.Drive.maxDrive, right * Constants.Drive.maxDrive, true);
  }

  public void tDriveVolts(double leftVolts, double rightVolts) {
    System.out.println("left:" + leftVolts + " right: " + rightVolts);
    leftMaster.setVoltage(leftVolts);
    rightMaster.setVoltage(rightVolts);
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
    double value = leftMaster.getSelectedSensorPosition() / Constants.Drive.oneRotation / Constants.Drive.gearRatio;
    SmartDashboard.putNumber("leftEncoder", value);
    return value;
  }

  public double getRightEncoder() {
    double value = rightMaster.getSelectedSensorPosition() / Constants.Drive.oneRotation / Constants.Drive.gearRatio;
    SmartDashboard.putNumber("rightEncoder", value);
    return value;
  }

  public void resetEncoders() {
    leftMaster.setSelectedSensorPosition(0);
    rightMaster.setSelectedSensorPosition(0);
  }

  public Rotation2d getAngle() {
    return Rotation2d.fromDegrees(-ahrs.getAngle());
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
    // talon.configClosedloopRamp(Constants.Drive.rampSpeed);
    // talon.configOpenloopRamp(Constants.Drive.rampSpeed);
    talon.configAllSettings(toApply);
    talon.setSelectedSensorPosition(0);
  }

  @Override
  public void periodic() {
    updatePose();
  }
}