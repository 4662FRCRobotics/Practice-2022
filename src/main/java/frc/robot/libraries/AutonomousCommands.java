// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.libraries;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import static edu.wpi.first.wpilibj.util.ErrorMessages.requireNonNullParam;

/** Add your docs here. */
public class AutonomousCommands {

    private final Map<String, Command> m_autoCommand = new HashMap<>();
    private String m_defaultChoice = "";

public AutonomousCommands() {}

    public void setDefaultOption(String name, Command object) {
        String setDefaultOption;
        requireNonNullParam(name, "name", "setDefaultOption");

        m_defaultChoice = name;
        addOption(name, object);
    }

    public void addOption(String name, Command command) {
        m_autoCommand.put(name, command);
    }

    public Command getSelected(String autoCmdName) {
        Command autoCommand = m_autoCommand.get(autoCmdName);
        boolean bIsCommandFound = autoCommand != null;
        SmartDashboard.putBoolean("Auto Found", bIsCommandFound);
        if (!bIsCommandFound) {
            autoCommand = m_autoCommand.get(m_defaultChoice);
        }
        return autoCommand;
    }

}
