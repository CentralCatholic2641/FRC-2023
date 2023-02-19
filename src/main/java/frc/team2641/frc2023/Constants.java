// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.frc2023;

import edu.wpi.first.math.filter.SlewRateLimiter;
import frc.team2641.frc2023.helpers.ArmPosition;
import frc.team2641.lib.PIDGains;

public final class Constants {
	public static final class Controllers {
		public static final int driver = 0;
		public static final int controller = 1;
	}

	public static final class CAN {
		public static final int leftSlave1 = 0;
		public static final int leftMaster = 1;
		public static final int leftSlave2 = 2;
		public static final int rightSlave1 = 3;
		public static final int rightMaster = 4;
		public static final int rightSlave2 = 5;
		public static final int leftShoulder = 6;
		public static final int rightShoulder = 7;
		public static final int elbow = 8;
		public static final int wrist = 9;
		public static final int PDH = 20;
		public static final int PH = 21;
	}

	public static final class Drive {
		public static final double maxDrive = 0.6;
		public static final double maxSteer = 0.5;
		public static final boolean brakes = true;
		public static final double oneRotation = 2048.0;
		public static final double gearRatio = 6;
		public static final double wheelDiameter = 0.1524;
		public static final double wheelCircumference = 2 * Math.PI * (wheelDiameter / 2);
		public static final double encoderToMeters = (double) oneRotation / gearRatio * 32 / wheelCircumference;
		public static final double ksVolts = 0.59198;
		public static final double kvVoltSecondsPerMeter = 2.3765;
		public static final double kaVoltSecondsSquaredPerMeter = 0.21168;
		public static final double kPDriveVel = 2.8757;
		public static final double kTrackwidthMeters = 0.5588;
		public static final double kMaxSpeedMetersPerSecond = 3;
		public static final double kMaxAccelerationMetersPerSecondSquared = 1;
		public static final double kRamseteB = 2;
		public static final double kRamseteZeta = 0.7;
		public static final SlewRateLimiter driveRateLimiter = new SlewRateLimiter(5);
		public static final SlewRateLimiter rotationRateLimiter = new SlewRateLimiter(5);
		public static final PIDGains PID = new PIDGains(0, 0, 0, 0, 0.25, 500);
	}

	public static final class Pneumatics {
		public static final int minPressure = 80;
		public static final int maxPressure = 120;
	}

	public static final class Pipelines {
		public static final int AprilTag = 0;
		public static final int SeekTarget = 1;
	}

	public static final class Arm {
		public static final class Positions {
			public static final ArmPosition start = new ArmPosition(0, 0, 0);
			public static final ArmPosition intake = new ArmPosition(0, 0, 0);
			public static final ArmPosition bottomRow = new ArmPosition(0, 0, 0);
			public static final ArmPosition middleRow = new ArmPosition(0, 0, 0);
			public static final ArmPosition topRow = new ArmPosition(0, 0, 0);
			public static final ArmPosition singleSubstation = new ArmPosition(0, 0, 0);
			public static final ArmPosition doubleSubstation = new ArmPosition(0, 0, 0);
		}

		public static final PIDGains shoulderGains = new PIDGains(0, 0, 0, 0, 0.25, 500);
		public static final PIDGains elbowGains = new PIDGains(0, 0, 0, 0, 0.25, 500);
		public static final PIDGains wristGains = new PIDGains(0, 0, 0, 0, 0.25, 50);
	}

	public static Mode currentMode = Mode.REAL;

	public static enum Mode {
		REAL,
		SIM,
		REPLAY
	}
}