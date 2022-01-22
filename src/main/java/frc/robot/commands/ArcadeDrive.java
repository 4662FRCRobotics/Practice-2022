// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class ArcadeDrive extends CommandBase {
  /** Creates a new ArcadeDrive. */
  private final DriveSubsystem m_drive;
  private final DoubleSupplier m_velocity;
  private final DoubleSupplier m_heading; 

  public ArcadeDrive(DriveSubsystem subsystem, DoubleSupplier velocity, DoubleSupplier heading) {

    m_drive = subsystem;
    m_velocity = velocity;
    m_heading = heading;
    addRequirements(m_drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.arcadeDrive(m_velocity.getAsDouble(), m_heading.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
