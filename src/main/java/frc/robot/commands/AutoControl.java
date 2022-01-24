package frc.robot.commands;

//import static edu.wpi.first.wpilibj.DriverStation.getInstance;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.libraries.AutonomousCommands;
import frc.robot.libraries.ConsoleJoystick;
import frc.robot.libraries.Step;
import frc.robot.subsystems.DriveSubsystem;

public class AutoControl extends CommandBase {
    AutonomousCommands m_autoStepCommand;
    ConsoleJoystick m_console;
    DriveSubsystem m_drive;
    /*
     * Hopper m_hopper;
     * Intake m_intake;
     * Shooter m_shooter;
     * Vision m_vision;
     */
    WaitForCount m_wait;

    int m_positionSwitch;

    String m_currentStepName;
    Command m_currentCommand;
    int m_waitCount;

    int m_stepIndex = 0;
    Step m_step[] = { new Step("Drive", () -> true)
                    , new Step("wait", () -> true)
                    , new Step("Drive", () -> true)
    };

    public AutoControl(ConsoleJoystick console, DriveSubsystem drive) {
        m_console = console;
        m_drive = drive;

        m_autoStepCommand = new AutonomousCommands();

        m_autoStepCommand.addOption("Drive", new AutoDriveDistance(1, m_drive));
        m_autoStepCommand.addOption("Turn90", new AutoTurnAngle(90.0, m_drive));
        m_autoStepCommand.addOption("Turn-90", new AutoTurnAngle(-90.0, m_drive));
        m_autoStepCommand.addOption("wait", new WaitCommand(2));
        m_autoStepCommand.addOption("WaitCount", new WaitForCount(1, () -> m_console.getROT_SW_1()));
        m_autoStepCommand.addOption("End", new End());
    }

    private void dashboardCmd(String cmdName) {
        SmartDashboard.putString("Auto Cmd Step", cmdName);
    }

    @Override
    public void initialize() {
        // getInstance();
        if (DriverStation.isEnabled()) {
            m_waitCount = 0;
            m_stepIndex = -1;
            m_currentStepName = getNextActiveCommand();
            m_currentCommand = m_autoStepCommand.getSelected(m_currentStepName);
            dashboardCmd(m_currentStepName);
            m_currentCommand.initialize();
        }
    }

    @Override
    public void execute() {
        m_currentCommand.execute();
    }

    @Override
    public void end(boolean interrupted) {
        m_currentCommand.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        boolean areWeThereYet = true;
        if (m_currentCommand.isFinished() == false) {
            areWeThereYet = false;
        } else {
            areWeThereYet = stepNextCommand();
        }
        return areWeThereYet;
    }

    public boolean stepNextCommand() {
        boolean areWeThereYet = true;

        m_currentStepName = getNextActiveCommand();
        if (m_currentStepName == "End") {
            areWeThereYet = true;

        } else {
            dashboardCmd(m_currentStepName);
            switchCommand(m_autoStepCommand.getSelected(m_currentStepName));
            areWeThereYet = false;
        }

        return areWeThereYet;
    }

    private void switchCommand(final Command cmd) {
        m_currentCommand.end(false);
        m_currentCommand = cmd;
        m_currentCommand.initialize();
    }

    private String getNextActiveCommand() {

        System.out.println("gestNextActiveCommand");

        String returnStepName = "";

        while (returnStepName == "") {
            m_stepIndex++;
            if (m_stepIndex >= m_step.length) {
                returnStepName = "End";
            } else {
                if (m_step[m_stepIndex].isTrue()) {
                    returnStepName = m_step[m_stepIndex].getName();
                }
            }
        }
        System.out.println(returnStepName);
        return returnStepName;
    }
}
