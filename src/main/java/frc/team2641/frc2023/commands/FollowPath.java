// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.commands;

// import java.util.List;
// import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.math.controller.RamseteController;
// import edu.wpi.first.math.controller.SimpleMotorFeedforward;
// import edu.wpi.first.math.geometry.Pose2d;
// import edu.wpi.first.math.geometry.Rotation2d;
// import edu.wpi.first.math.geometry.Translation2d;
// import edu.wpi.first.math.trajectory.Trajectory;
// import edu.wpi.first.math.trajectory.TrajectoryConfig;
// import edu.wpi.first.math.trajectory.TrajectoryGenerator;
// import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
// import edu.wpi.first.wpilibj2.command.RamseteCommand;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import frc.team2641.frc2023.Constants;
// import frc.team2641.frc2023.Robot;
import edu.wpi.first.wpilibj2.command.Command;
import frc.team2641.frc2023.subsystems.Drivetrain;

public class FollowPath {

	private static Drivetrain drivetrain = Drivetrain.getInstance();

	// public static SequentialCommandGroup get() {
	// var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
	// new SimpleMotorFeedforward(
	// Constants.Drive.ksVolts,
	// Constants.Drive.kvVoltSecondsPerMeter,
	// Constants.Drive.kaVoltSecondsSquaredPerMeter),
	// drivetrain.getKinematics(),
	// 10);

	// // Create config for trajectory
	// TrajectoryConfig config = new TrajectoryConfig(
	// Constants.Drive.kMaxSpeedMetersPerSecond,
	// Constants.Drive.kMaxAccelerationMetersPerSecondSquared)
	// // Add kinematics to ensure max speed is actually obeyed
	// .setKinematics(drivetrain.getKinematics())
	// // Apply the voltage constraint
	// .addConstraint(autoVoltageConstraint);

	// // An example trajectory to follow. All units in meters.
	// Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
	// // Start at the origin facing the +X direction
	// new Pose2d(0, 0, new Rotation2d(0)),
	// // Pass through these two interior waypoints, making an 's' curve path
	// List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
	// // End 3 meters straight ahead of where we started, facing forward
	// new Pose2d(3, 0, new Rotation2d(0)),
	// // Pass config
	// config);

	// RamseteCommand ramseteCommand = new RamseteCommand(
	// exampleTrajectory,
	// drivetrain::getPose,
	// new RamseteController(Constants.Drive.kRamseteB,
	// Constants.Drive.kRamseteZeta),
	// new SimpleMotorFeedforward(
	// Constants.Drive.ksVolts,
	// Constants.Drive.kvVoltSecondsPerMeter,
	// Constants.Drive.kaVoltSecondsSquaredPerMeter),
	// drivetrain.getKinematics(),
	// drivetrain::getWheelSpeeds,
	// new PIDController(Constants.Drive.kPDriveVel, 0, 0),
	// new PIDController(Constants.Drive.kPDriveVel, 0, 0),
	// // RamseteCommand passes volts to the callback
	// drivetrain::tDriveVolts,
	// drivetrain);

	// Robot.getField().getObject("traj").setTrajectory(exampleTrajectory);

	// // Reset odometry to the starting pose of the trajectory.
	// drivetrain.resetPose(exampleTrajectory.getInitialPose());

	// return ramseteCommand.andThen(() -> drivetrain.tDriveVolts(0, 0));
	// }

	public static Command get() {
		return drivetrain.followTrajectoryCommand("test", true);
	}
}
