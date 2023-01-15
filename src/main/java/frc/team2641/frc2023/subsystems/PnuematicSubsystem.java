// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team2641.frc2023.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PnuematicSubsystem extends SubsystemBase {

  private static PnuematicSubsystem instance = null;

  public static PnuematicSubsystem getInstance() {
    if (instance == null)
      instance = new PnuematicSubsystem();
    return instance;
  }

  private Compressor compressor = new Compressor(21, PneumaticsModuleType.CTREPCM);

  private PnuematicSubsystem() {
  }

  public void enable() {
    compressor.enableDigital();
  }

  public void disable() {
    compressor.disable();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
