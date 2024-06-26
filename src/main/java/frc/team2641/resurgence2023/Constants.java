// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023;

import edu.wpi.first.math.filter.SlewRateLimiter;
import frc.team2641.resurgence2023.helpers.ArmPosition;
import frc.team2641.lib.PIDGains;

public final class Constants {
	public static final class Controllers {
		public static final int driver = 0;
		public static final int operator = 1;
		public static final int joystick = 2;
	}

	public static final class CAN {
		public static final int leftSlave1 = 0;
		public static final int leftMaster = 1;
		public static final int leftSlave2 = 2;
		public static final int rightSlave1 = 3;
		public static final int rightMaster = 4;
		public static final int rightSlave2 = 5;
		public static final int rightShoulder = 6;
		public static final int leftShoulder = 7;
		public static final int elbow = 8;
		public static final int wrist = 9;
		public static final int intake = 10;
		public static final int PDH = 20;
	}

	public static final class Drive {
		public static final double maxDrive = 0.7;
		public static final double slowDrive = 0.4;
		public static final double maxSteer = 0.45;
		public static final double slowSteer = 0.4;
		public static final double rampSpeed = 1.5;
		public static final boolean brakes = true;
		public static final double oneRotation = 2048.0;
		public static final double gearRatio = 6;
		public static final double wheelDiameter = 0.1524;
		public static final double wheelCircumference = Math.PI * wheelDiameter;
		public static final double encoderToMeters = (double) oneRotation / gearRatio * 32 / wheelCircumference;
		public static final double ksVolts = 0;
		public static final double kvVoltSecondsPerMeter = 1.25;
		public static final double kaVoltSecondsSquaredPerMeter = 0;
		public static final double kPDriveVel = 0;
		public static final double kTrackwidthMeters = 0.5588;
		public static final double kMaxSpeedMetersPerSecond = 2;
		public static final double kMaxAccelerationMetersPerSecondSquared = 1.5;
		public static final SlewRateLimiter driveRateLimiter = new SlewRateLimiter(3);
		public static final SlewRateLimiter rotationRateLimiter = new SlewRateLimiter(5);
		public static final SlewRateLimiter autoLeftRateLimiter = new SlewRateLimiter(3);
		public static final SlewRateLimiter autoRightRateLimiter = new SlewRateLimiter(3);
		public static final PIDGains PID = new PIDGains(0, 0, 0, 0, 0.25, 500);
	}

	public static final class Pneumatics {
		public static final int minPressure = 80;
		public static final int maxPressure = 120;
	}

	public static final class Pipelines {
		public static final int AprilTag = 0;
		public static final int Retroreflective = 1;
		public static final int GamePiece = 2;
	}

	public static final class Arm {
		public static final class Positions {
			public static final ArmPosition start = new ArmPosition(0, 0, 0);
			public static final ArmPosition intake = new ArmPosition(-6900, -110000, 0);
			public static final ArmPosition bottomRow = new ArmPosition(38000, 132620, 10000);
			public static final ArmPosition middleRowStart = new ArmPosition(83000, 28000, 0);
			public static final ArmPosition middleRow = new ArmPosition(83000, 85000, 10000);
			public static final ArmPosition topRowStart = new ArmPosition(70000, 0, 0);
			public static final ArmPosition topRow = new ArmPosition(145000, 162000, 10000);
			public static final ArmPosition singleSubstation = new ArmPosition(-68000, -88000, 0);
			public static final ArmPosition doubleSubstationStart = new ArmPosition(-75000, 0, 0);
			public static final ArmPosition doubleSubstation = new ArmPosition(-137000, -165000, 0);
		}

		public static final PIDGains shoulderGains = new PIDGains(0.4, 0, 0, 0, 5, 1);
		public static final PIDGains elbowGains = new PIDGains(0.4, 0, 0, 0, 5, 1);
		public static final PIDGains wristGains = new PIDGains(0.25, 0, 0, 0, 2.5, 0.5);

		public static final SlewRateLimiter shoulderRateLimiter = new SlewRateLimiter(4.5);
		public static final SlewRateLimiter elbowRateLimiter = new SlewRateLimiter(10);
		public static final SlewRateLimiter wristRateLimiter = new SlewRateLimiter(6);
	}
}