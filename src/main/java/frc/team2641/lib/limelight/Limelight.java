// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.lib.limelight;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.networktables.DoubleArraySubscriber;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Notifier;
import frc.team2641.lib.limelight.ControlMode.*;

public class Limelight {

  private boolean isConnected = false;
  private String name = "limelight";
  private NetworkTable table;
  private double _heartBeatPeriod = 0.1;
  private DoubleArraySubscriber posesub;

  class PeriodicRunnable implements java.lang.Runnable {
    public void run() {
      resetPilelineLatency();
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (getPipelineLatency() == 0.0) {
        isConnected = false;
      } else {
        isConnected = true;
      }
    }
  }

  private Notifier _heartBeat = new Notifier(new PeriodicRunnable());

  private static Limelight instance = null;

  /**
   * Returns a prexisting instance of Limelight, or creates and returns a new one
   * 
   * @return instance of Limelight
   */
  public static Limelight getInstance() {
    if (instance == null)
      instance = new Limelight();
    return instance;
  }

  private Limelight() {
    this.table = NetworkTableInstance.getDefault().getTable(this.name);
    this.posesub = this.table.getDoubleArrayTopic("botpose").subscribe(new double[] {});
    _heartBeat.startPeriodic(_heartBeatPeriod);
  }

  private Limelight(String name) {
    this.name = name;
    this.table = NetworkTableInstance.getDefault().getTable(this.name);
    this.posesub = this.table.getDoubleArrayTopic("botpose").subscribe(new double[] {});
    _heartBeat.startPeriodic(_heartBeatPeriod);
  }

  /**
   * Gets whether the Limelight has any valid targets (tv)
   * 
   * @return true if a target is found
   */
  public boolean isTargetFound() {
    NetworkTableEntry tv = table.getEntry("tv");
    double v = tv.getDouble(0);
    if (v == 0.0f) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Gets the horizontal offset from crosshair to target (tx)
   * 
   * @return horizontal offset from crosshair to target (-29.8 to 29.8 degrees)
   */
  public double getRotationToTargetDeg() {
    NetworkTableEntry tx = table.getEntry("tx");
    double x = tx.getDouble(0.0);
    return x;
  }

  /**
   * Gets the vertical offset from crosshair to target (ty)
   * 
   * @return vertical offset from crosshair to target (-24.85 to 24.85 degrees)
   */
  public double getVerticalToTargetDeg() {
    NetworkTableEntry ty = table.getEntry("ty");
    double y = ty.getDouble(0.0);
    return y;
  }

  /**
   * Gets the target area (ta)
   * 
   * @return the target area (0% of image to 100% of image)
   */
  public double getTargetArea() {
    NetworkTableEntry ta = table.getEntry("ta");
    double a = ta.getDouble(0.0);
    return a;
  }

  /**
   * Gets the skew, or rotation, (ts)
   * 
   * @return skew (-90 degrees to 0 degrees)
   */
  public double getSkew() {
    NetworkTableEntry ts = table.getEntry("ts");
    double s = ts.getDouble(0.0);
    return s;
  }

  /**
   * Gets the pipeline’s latency contribution, add at least 11ms for image capture
   * latency (tl)
   * 
   * @return pipeline’s latency contribution (ms)
   */
  public double getPipelineLatency() {
    NetworkTableEntry tl = table.getEntry("tl");
    double l = tl.getDouble(0.0);
    return l;
  }

  /**
   * Gets the sidelength of shortest side of the fitted bounding box (tshort)
   * 
   * @return sidelength of shortest side of the fitted bounding box (pixels)
   */
  public double getShortSidelength() {
    NetworkTableEntry tshort = table.getEntry("tshort");
    double shortd = tshort.getDouble(0.0);
    return shortd;
  }

  /**
   * Gets the sidelength of longest side of the fitted bounding box (tlong)
   * 
   * @return sidelength of longest side of the fitted bounding box (pixels)
   */
  public double getLongSidelength() {
    NetworkTableEntry tlong = table.getEntry("tlong");
    double longd = tlong.getDouble(0.0);
    return longd;
  }

  /**
   * Gets the sidelength of horizontal side of the rough bounding box (tlong)
   * 
   * @return sidelength of horizontal side of the rough bounding box (0-320
   *         pixels)
   */
  public double getHorizontalSidelength() {
    NetworkTableEntry thor = table.getEntry("thor");
    double hor = thor.getDouble(0.0);
    return hor;
  }

  /**
   * Gets the sidelength of vertical side of the rough bounding box (tvert)
   * 
   * @return sidelength of vertical side of the rough bounding box (0-320 pixels)
   */
  public double getVerticalSidelength() {
    NetworkTableEntry tvert = table.getEntry("tvert");
    double vert = tvert.getDouble(0.0);
    return vert;
  }

  /**
   * Gets the camera transform in target space of primary AprilTag or solvePnP
   * target (camtran)
   * 
   * @return camera transform in target space of primary AprilTag or solvePnP
   *         target
   */
  public double[] getCameraTransform() {
    NetworkTableEntry camtran = table.getEntry("camtran");
    double[] camtrand = camtran.getDoubleArray(new double[6]);
    return camtrand;
  }

  /**
   * Gets the ID of the primary AprilTag (tid)
   * 
   * @return the ID of the primary AprilTag
   */
  public int getAprilTagID() {
    NetworkTableEntry tid = table.getEntry("tid");
    int id = (int) tid.getInteger(0);
    return id;
  }

  /**
   * Gets the class ID of primary neural detector result or neural classifier
   * result (tclass)
   * 
   * @return the class ID of primary neural detector result or neural classifier
   *         result
   */
  public String getClassID() {
    NetworkTableEntry tclass = table.getEntry("tclass");
    String classd = tclass.getString("");
    return classd;
  }

  /**
   * Gets the robot transform in field-space (botpose)
   * 
   * @return Pose2d of the robot on the field
   */
  public Pose2d getPose() {
    double[] result = posesub.get();
    Translation3d tran3d = new Translation3d(result[0], result[1], result[2]);
    Rotation3d r3d = new Rotation3d(result[3], result[4], result[5]);
    Pose3d p3d = new Pose3d(tran3d, r3d);
    return p3d.toPose2d();
  }

  /**
   * Checks if connected to Limelight
   * 
   * @return true if connected
   */
  public boolean isConnected() {
    return isConnected;
  }

  /**
   * Resets pipeline latency
   */
  private void resetPilelineLatency() {
    table.getEntry("tl").setValue(0.0);
  }

  /**
   * Sets Limelight’s LED state (ledMode)
   * 
   * kPipeLine use the LED Mode set in the current pipeline
   * kForceOff force off
   * kForceBlink force blink
   * kForceOn force on
   * 
   * @param LEDMode ledMode
   */
  public void setLEDMode(LEDMode ledMode) {
    table.getEntry("ledMode").setValue(ledMode.getValue());
  }

  /**
   * Gets the current LED mode of the Limelight (ledMode)
   * 
   * @return LEDMode
   */
  public LEDMode getLEDMode() {
    NetworkTableEntry ledMode = table.getEntry("ledMode");
    double led = ledMode.getDouble(0.0);
    LEDMode mode = LEDMode.getByValue(led);
    return mode;
  }

  /**
   * Sets limelight’s operation mode (camMode)
   * 
   * kVision Vision processor
   * kDriver Driver camera (increases exposure, disables vision processing)
   * 
   * @param CamMode camMode
   */

  public void setCamMode(CamMode camMode) {
    table.getEntry("camMode").setValue(camMode.getValue());
  }

  /**
   * Gets the current camera mode of the Limelight (camMode)
   * 
   * @return CamMode
   */
  public CamMode getCamMode() {
    NetworkTableEntry camMode = table.getEntry("camMode");
    double cam = camMode.getDouble(0.0);
    CamMode mode = CamMode.getByValue(cam);
    return mode;
  }

  /**
   * Sets Limelight’s current pipeline (pipeline)
   * 
   * @param int pipeline 0-9
   */
  public void setPipeline(int pipeline) {
    if (pipeline < 0) {
      pipeline = 0;
      throw new IllegalArgumentException("Pipeline can not be less than zero");
    } else if (pipeline > 9) {
      pipeline = 9;
      throw new IllegalArgumentException("Pipeline can not be greater than nine");
    }
    table.getEntry("pipeline").setValue(pipeline);
  }

  /**
   * Gets the current pipeline of the Limelight (pipeline)
   * 
   * @return the current pipeline (int 0-9)
   */
  public int getPipeline() {
    NetworkTableEntry pipeline = table.getEntry("pipeline");
    int pipe = (int) pipeline.getInteger(0);
    return pipe;
  }

  /**
   * Sets limelight’s streaming mode (stream)
   * 
   * kStandard Side-by-side streams if a webcam is attached to Limelight
   * kPiPMain The secondary camera stream is placed in the lower-right corner of
   * the primary camera stream
   * kPiPSecondary The primary camera stream is placed in the lower-right corner
   * of the secondary camera stream
   * 
   * @param StreamType stream
   */
  public void setStreamMode(StreamType stream) {
    table.getEntry("stream").setValue(stream.getValue());
  }

  /**
   * Gets the current streaming mode of the Limelight (stream)
   * 
   * @return StreamType
   */
  public StreamType getStreamMode() {
    NetworkTableEntry stream = table.getEntry("stream");
    double st = stream.getDouble(0.0);
    StreamType mode = StreamType.getByValue(st);
    return mode;
  }

  /**
   * Take snapshots during a match (snapshot)
   * 
   * kOn Stop taking snapshots
   * kOff Take two snapshots per second
   * 
   * @param Snapshot snapshot
   */
  public void setSnapshot(Snapshot snapshot) {
    table.getEntry("snapshot").setValue(snapshot.getValue());
  }

  /**
   * Gets the current snapshot mode of the Limelight (snapshot)
   * 
   * @return Snapshot
   */
  public Snapshot getSnapshot() {
    NetworkTableEntry snapshot = table.getEntry("snapshot");
    double snshot = snapshot.getDouble(0.0);
    Snapshot mode = Snapshot.getByValue(snshot);
    return mode;
  }
}
