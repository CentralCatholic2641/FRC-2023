package frc.team2641.frc2023.telemetry;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.team2641.frc2023.auto.*;

public class ShuffleboardController {
  public ShuffleboardTab preMatchTab = Shuffleboard.getTab("Pre-Match");
  public ShuffleboardTab autoTab = Shuffleboard.getTab("Auto");
  public ShuffleboardTab teleopTab = Shuffleboard.getTab("Teleop");
  public ShuffleboardTab testTab = Shuffleboard.getTab("Test");

  SendableChooser<Command> autoChooser = new SendableChooser<>();

  private static ShuffleboardController instance = null;

  public static ShuffleboardController getInstance() {
    if (instance == null)
      instance = new ShuffleboardController();
    return instance;
  }

  public ShuffleboardController() {
    // Pre-match
    autoChooser.setDefaultOption("Default", Sequences.ScoreConeThenBalance());

    preMatchTab.add("Auto", autoChooser).withSize(2, 1);
    preMatchTab.addCamera("Camera", "Camera", "/dev/video0");

    // Test
    // testTab.add("Test", new TestCommand()).withSize(2, 1);

    // Autonomous
    autoTab.addCamera("Camera", "Camera", "/dev/video0").withPosition(0, 0).withSize(5, 5);

    // Teleop
    teleopTab.addCamera("Camera", "Camera", "/dev/video0").withPosition(0, 0).withSize(5, 5);
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
}
