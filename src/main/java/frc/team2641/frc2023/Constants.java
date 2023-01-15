package frc.team2641.frc2023;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

public final class Constants {
	public static final class Controllers {
		public static final int driver = 0;
		public static final int controller = 1;
	}

	public static final class CAN {
		public static final int rightMotor1 = 0;
		public static final int rightMotor2 = 1;
		public static final int rightMotor3 = 2;
		public static final int leftMotor1 = 3;
		public static final int leftMotor2 = 4;
		public static final int leftMotor3 = 5;
		public static final int PCM = 21;
	}

	public static final class Drive {
		public static final double maxDrive = 1.0;
		public static final double rampSpeed = 0.5;
		public static final boolean brakes = true;
		public static final double oneRotation = 2048.0;
		public static final double gearRatio = 10.71;
		public static final double ksVolts = 0.59198;
		public static final double kvVoltSecondsPerMeter = 2.3765;
		public static final double kaVoltSecondsSquaredPerMeter = 0.21168;
		public static final double kPDriveVel = 2.8757;
		public static final double kTrackwidthMeters = 0.5588;
		public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(
				kTrackwidthMeters);
		public static final double kMaxSpeedMetersPerSecond = 3;
		public static final double kMaxAccelerationMetersPerSecondSquared = 1;
		public static final double kRamseteB = 2;
		public static final double kRamseteZeta = 0.7;
	}
}