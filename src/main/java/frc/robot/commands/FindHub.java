// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Vision;

public class FindHub extends CommandBase {
  Vision m_vision;

  /** Creates a new FindHub. */
  public FindHub(Vision vision) {
    m_vision = vision; 
    addRequirements(m_vision);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_vision.enableHubTargeting();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_vision.getTarget();

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_vision.disableHubTargeting();
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_vision.haveTarget();
  }
}
