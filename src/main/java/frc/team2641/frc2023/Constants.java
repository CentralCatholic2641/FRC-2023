package frc.team2641.frc2023;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

public final class Constants {
	public final class JoystickButtons {
		public static final int trigger = 1;
		public static final int thumb = 2;
		public static final int threeButton = 3;
		public static final int fourButton = 4;
		public static final int fiveButton = 5;
		public static final int sixButton = 6;
		public static final int sevenButton = 7;
		public static final int eightButton = 8;
		public static final int nineButton = 9;
		public static final int tenButton = 10;
		public static final int elevenButton = 11;
		public static final int twelveButton = 12;

		public static final int xAxis = 0;
		public static final int yAxis = 1;
		public static final int zRotate = 2;
		public static final int slider = 3;
	}

	public final class GamepadButtons {
		public static final int lxAxis = 0;
		public static final int lyAxis = 1;
		public static final int leftTrigger = 2;
		public static final int rightTrigger = 3;
		public static final int rxAxis = 4;
		public static final int ryAxis = 5;

		public static final int aButton = 1;
		public static final int bButton = 2;
		public static final int xButton = 3;
		public static final int yButton = 4;
		public static final int leftBumper = 5;
		public static final int rightBumper = 6;
		public static final int backButton = 7;
		public static final int startButton = 8;
	}

	public final class Controllers {
		public static final int driver = 0;
		public static final int controller = 1;
	}

	public final class Motors {
		public static final int leftMotor1 = 3;
		public static final int leftMotor2 = 4;
		public static final int leftMotor3 = 5;
		public static final int rightMotor1 = 0;
		public static final int rightMotor2 = 1;
		public static final int rightMotor3 = 2;
		public static final int climberMotor = 6;
		public static final int shooterMotor = 7;
		public static final int intakeMotor = 12;
		public static final int hopperMotor1 = 13;
		public static final int hopperMotor2 = 14;
		public static final int indexerMotor = 15;
	}

	public static final class DriveConstants {
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

	// Encoders
	public static final int rightEncoder = 2;
	public static final int leftEncoder = 4;

	public final class PID {
		public static final double kP = 0.8;
		public static final double kI = 0.00475;
		public static final double kD = 0.005;

		public static final double kSVolts = 0;
		public static final double kVVoltSecondsPerRotation = 0;
	}

	public static final class MotorSpeeds {
		public static final double maxDrive = 1.0;
		public static final double rampSpeed = 0;
		public static final double rotateFactor = 0.8;
		public static final double driveRampSpeed = 0.5;
		public static final double climberSpeed = 0.35;
		public static final double intakeSpeed = 0.5;
		public static final double hopperSpeed = 0.5;
		public static final double indexerSpeed = 0.35;
		// public static final double highShooterSpeed = 0.65;
		public static final double highShooterSpeed = 1;
		public static final double lowShooterSpeed = 0.35;
		public static final boolean brakes = true;
	}

	// Other
	public static final double wheelDiameter = 0.5;
	public static final double driftCompensation = 0.04;
	public static final double oneRotation = 2048.0;
}