// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.lib.limelight;

import java.util.HashMap;
import java.util.Map;

public class ControlMode {
  public enum LEDMode {
    kPipeLine(0),
    kForceOff(1),
    kForceBlink(2),
    kForceOn(3);

    private static final Map<Double, LEDMode> MAP = new HashMap<Double, LEDMode>();

    static {
      for (LEDMode LEDMode : values()) {
        MAP.put(LEDMode.getValue(), LEDMode);
      }
    }

    private double value;

    private LEDMode(double value) {
      this.value = value;
    }

    public double getValue() {
      return value;
    }

    public static LEDMode getByValue(double value) {
      return MAP.get(value);
    }

    public String toString() {
      return name();
    }

  }

  public enum CamMode {
    kVision(0),
    kDriver(1);

    private static final Map<Double, CamMode> MAP = new HashMap<Double, CamMode>();

    static {
      for (CamMode CamMode : values()) {
        MAP.put(CamMode.getValue(), CamMode);
      }
    }

    private double value;

    private CamMode(double value) {
      this.value = value;
    }

    public double getValue() {
      return value;
    }

    public static CamMode getByValue(double value) {
      return MAP.get(value);
    }

    public String toString() {
      return name();
    }
  }

  public enum StreamType {
    kStandard(0),
    kPiPMain(1),
    kPiPSecondary(2);

    private static final Map<Double, StreamType> MAP = new HashMap<Double, StreamType>();

    static {
      for (StreamType StreamType : values()) {
        MAP.put(StreamType.getValue(), StreamType);
      }
    }

    private double value;

    private StreamType(double value) {
      this.value = value;
    }

    public double getValue() {
      return value;
    }

    public static StreamType getByValue(double value) {
      return MAP.get(value);
    }

    public String toString() {
      return name();
    }

  }

  public enum Snapshot {

    kon(1), koff(0);

    private static final Map<Double, Snapshot> MAP = new HashMap<Double, Snapshot>();

    static {
      for (Snapshot Snapshot : values()) {
        MAP.put(Snapshot.getValue(), Snapshot);
      }
    }

    private double value;

    private Snapshot(double value) {
      this.value = value;
    }

    public double getValue() {
      return value;
    }

    public static Snapshot getByValue(double value) {
      return MAP.get(value);
    }

    public String toString() {
      return name();
    }

  }
}