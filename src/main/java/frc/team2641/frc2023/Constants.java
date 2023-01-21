package frc.team2641.frc2023;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import frc.team2641.lib.PIDGains;

public final class Constants {
	public static final class Controllers {
		public static final int driver = 0;
		public static final int controller = 1;
	}

	public static final class CAN {
		public static final int rightSlave1 = 0;
		public static final int rightMaster = 1;
		public static final int rightSlave2 = 2;
		public static final int leftSlave1 = 3;
		public static final int leftMaster = 4;
		public static final int leftSlave2 = 5;
		public static final int leftShoulder = 6;
		public static final int PH = 21;
		public static final int PDH = 20;
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
		public static final SlewRateLimiter rateLimiter = new SlewRateLimiter(0.5);
	}

	public static final class Pneumatics {
		public static final int minPressure = 80;
		public static final int maxPressure = 120;
	}

	public static final class Pipelines {
		public static final int AprilTag = 0;
		public static final int SeekTarget = 1;
	}

	public static final class Shoulder {
		public static final int timeoutMs = 30;
		public static final PIDGains gains = new PIDGains(0.15, 0.0, 1.0, 0.0, 0, 1.0);
	}

	public static Mode currentMode = Mode.REAL;

  public static enum Mode {
    /** Running on a real robot. */
    REAL,

    /** Running a physics simulator. */
    SIM,

    /** Replaying from a log file. */
    REPLAY
  }
}