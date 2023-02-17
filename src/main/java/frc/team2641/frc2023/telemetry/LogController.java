// Copyright (c) 2023 FRC Team 2641
// Use of this source code is governed by the MIT license

package frc.team2641.frc2023.telemetry;

import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;

public class LogController {
  private static LogController instance = null;

  public static LogController getInstance() {
    if (instance == null)
      instance = new LogController();
    return instance;
  }

  public LogController() {
  }

  public void start() {
    DataLogManager.start();
    DriverStation.startDataLog(DataLogManager.getLog());
  }
}
