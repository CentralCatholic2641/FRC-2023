// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.resurgence2023.telemetry;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.team2641.resurgence2023.auto.*;

public class ShuffleboardController {
  public ShuffleboardTab preMatchTab = Shuffleboard.getTab("Pre-Match");
  public ShuffleboardTab autoTab = Shuffleboard.getTab("Auto");
  public ShuffleboardTab teleopTab = Shuffleboard.getTab("Teleop");
  public ShuffleboardTab testTab = Shuffleboard.getTab("Test");

  private Field2d field = new Field2d();

  SendableChooser<Command> autoChooser = new SendableChooser<>();

  private static ShuffleboardController instance = null;

  public static ShuffleboardController getInstance() {
    if (instance == null)
      instance = new ShuffleboardController();
    return instance;
  }

  public ShuffleboardController() {
    SmartDashboard.putData(field);

    // Pre-match
    autoChooser.addOption("No Auto", AutoSequences.NoAuto());
    autoChooser.addOption("Just Score Top", ArmSequences.ScoreTop());
    autoChooser.setDefaultOption("Score Preload Top, Mobility", AutoSequences.ScorePreload("top"));

    // autoChooser.setDefaultOption("TOP - Score Preloaded Cube, Score Top Prestaged
    // Cone, Dock",
    // AutoSequences.ScorePreloadCubePickupScorePrestagedConeDock("top"));
    // autoChooser.addOption("TOP - Score Preloaded Cone, Score Top Prestaged Cube",
    // AutoSequences.ScorePreloadCubePickupScorePrestagedCone("top"));
    // autoChooser.addOption("TOP - Score Preload, Mobility",
    //     AutoSequences.ScorePreload("top"));
    // autoChooser.addOption("TOP - Mobility Only",
    // AutoSequences.Mobility("top"));

    // autoChooser.addOption("MID - Score Preloaded Cube, Score Top Prestaged Cone,
    // Dock",
    // AutoSequences.ScorePreloadCubePickupScorePrestagedConeDock("mid"));
    // autoChooser.addOption("MID - Score Preloaded Cone, Score Top Prestaged Cube",
    // AutoSequences.ScorePreloadCubePickupScorePrestagedCone("mid"));
    // autoChooser.addOption("MID - Score Preload, Mobility",
    //     AutoSequences.ScorePreload("mid"));
    // autoChooser.addOption("MID - Mobility Only",
    // AutoSequences.Mobility("mid"));

    // autoChooser.addOption("BOT - Score Preloaded Cube, Score Bottom Prestaged
    // Cone, Dock",
    // AutoSequences.ScorePreloadCubePickupScorePrestagedConeDock("bot"));
    // autoChooser.setDefaultOption("BOT - Score Preloaded Cone, Score Bottom Prestaged Cube",
    //     AutoSequences.ScorePreloadCubePickupScorePrestagedCone("bot"));
    // autoChooser.addOption("BOT - Score Preload, Mobility",
    //     AutoSequences.ScorePreload("bot"));
    // autoChooser.addOption("BOT - Mobility Only",
    //     AutoSequences.Mobility("bot"));

    preMatchTab.add("Auto", autoChooser).withSize(2, 1);
    // preMatchTab.addCamera("Claw", "Claw", "/dev/video0");
    // preMatchTab.addCamera("Side", "Side", "/dev/video1");

    // Autonomous
    // autoTab.addCamera("Claw", "Claw", "/dev/video0");
    // autoTab.addCamera("Side", "Side", "/dev/video1");

    // Teleop
    // teleopTab.addCamera("Claw", "Claw", "/dev/video0");
    // teleopTab.addCamera("Side", "Side", "/dev/video1");
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }

  public void preMatch() {
    Shuffleboard.selectTab("Pre-Match");
  }

  public void auto() {
    Shuffleboard.selectTab("Auto");
  }

  public void teleop() {
    Shuffleboard.selectTab("Teleop");
  }

  public void disabled() {
    Shuffleboard.selectTab("Pre-Match");
  }

  public void test() {
    Shuffleboard.selectTab("Test");
  }

  public void setRobotPose(Pose2d pose) {
    field.setRobotPose(pose);
  }
}
