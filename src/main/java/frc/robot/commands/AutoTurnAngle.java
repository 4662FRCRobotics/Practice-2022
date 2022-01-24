// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class AutoTurnAngle extends CommandBase {
  /** Creates a new AutoTurn2. */
  DriveSubsystem m_drive;
  DoubleSupplier m_targetAngle;

  public AutoTurnAngle(DoubleSupplier targetAngle, DriveSubsystem drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = drive;
    m_targetAngle = targetAngle;
    addRequirements(m_drive);
  }

  public AutoTurnAngle(Double targetAngle, DriveSubsystem drive) {
    this(() -> targetAngle, drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.initTurnController(m_targetAngle);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //System.out.println("AutoTurn2 execute");
    m_drive.execTurnController();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.endTurnController();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_drive.isTurnAtSetpoint();
  }
}
