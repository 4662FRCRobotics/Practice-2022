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
import frc.robot.libraries.AutoStepCommand;

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
    Step m_step[] = { new Step(AutoStepCommand.DRIVE1.name(), () -> true)
                    , new Step(AutoStepCommand.WAIT2.name(), () -> true)
                    , new Step(AutoStepCommand.DRIVE1.name(), () -> true)
    };

    public AutoControl(ConsoleJoystick console, DriveSubsystem drive) {
        m_console = console;
        m_drive = drive;

        m_autoStepCommand = new AutonomousCommands();

        m_autoStepCommand.addOption(AutoStepCommand.DRIVE1.name(), new AutoDriveDistance(1, m_drive));
        m_autoStepCommand.addOption(AutoStepCommand.TURNP90.name(), new AutoTurnAngle(90.0, m_drive));
        m_autoStepCommand.addOption(AutoStepCommand.TURNM90.name(), new AutoTurnAngle(-90.0, m_drive));
        m_autoStepCommand.addOption(AutoStepCommand.WAIT2.name(), new WaitCommand(2));
        m_autoStepCommand.addOption(AutoStepCommand.WAITLOOP.name(), new WaitForCount(1, () -> m_console.getROT_SW_1()));
        m_autoStepCommand.addOption(AutoStepCommand.END.name(), new End());
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
        if (m_currentStepName == AutoStepCommand.END.name()) {
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

        System.out.println("getNextActiveCommand");

        String returnStepName = "";

        while (returnStepName == "") {
            m_stepIndex++;
            if (m_stepIndex >= m_step.length) {
                returnStepName = AutoStepCommand.END.name();
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
