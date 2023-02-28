// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.auto;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPoint;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.team2641.resurgence2023.Constants;
import frc.team2641.resurgence2023.subsystems.Drivetrain;

public class FollowPath {
	private static Drivetrain drivetrain = Drivetrain.getInstance();

	public static Command get(String trajectory, boolean reset) {
		return drivetrain.followTrajectoryCommand(trajectory, reset);
	}

	public static Command get(PathPlannerTrajectory trajectory, boolean reset) {
		return drivetrain.followTrajectoryCommand(trajectory, reset);
	}

	public static Command goTo(PathPoint endpoint) {
		Pose2d pose = drivetrain.getPose();
		PathPlannerTrajectory trajectory = PathPlanner.generatePath(
				new PathConstraints(Constants.Drive.kMaxSpeedMetersPerSecond,
						Constants.Drive.kMaxAccelerationMetersPerSecondSquared),
				new PathPoint(pose.getTranslation(), pose.getRotation()),
				endpoint);

		return drivetrain.followTrajectoryCommand(trajectory, true);
	}
}
