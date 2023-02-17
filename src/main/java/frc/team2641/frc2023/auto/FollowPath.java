// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.auto;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPoint;
import edu.wpi.first.wpilibj2.command.Command;
import frc.team2641.frc2023.subsystems.Drivetrain;

public class FollowPath {
	private static Drivetrain drivetrain = Drivetrain.getInstance();

	public static Command get(String trajectory) {
		return drivetrain.followTrajectoryCommand(trajectory);
	}

	public static Command get(PathPlannerTrajectory trajectory) {
		return drivetrain.followTrajectoryCommand(trajectory);
	}

	public static Command goTo(PathPoint endpoint) {
		PathPlannerTrajectory trajectory = PathPlanner.generatePath(
				new PathConstraints(4, 3),
				new PathPoint(drivetrain.getPose().getTranslation(), drivetrain.getPose().getRotation()),
				endpoint);
		return drivetrain.followTrajectoryCommand(trajectory);
	}
}
