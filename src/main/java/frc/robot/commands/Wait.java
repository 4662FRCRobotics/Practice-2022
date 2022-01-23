package frc.robot.commands;

import java.util.function.IntSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Timer;

public class Wait extends CommandBase{
  protected Timer m_timer = new Timer();
  private final double m_duration;
  int m_iwaitCount;

  /**
  * Creates a variation on WPILIB WaitCommand. This command will do nothing, and end after the specified duration * count.
  *
  */

  public Wait(double seconds, IntSupplier waitCount) {
    m_iwaitCount = waitCount.getAsInt();
    m_duration = seconds;
    SendableRegistry.setName(this, getName() + ": " + seconds + " seconds");
  }

  public Wait(double seconds){
    this(seconds, () -> 1);
  }

  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.start();
    m_iwaitCount--;
  }

  @Override
  public void end(boolean interrupted) {
    m_timer.stop();
  }
  
  @Override
  public boolean isFinished() {
    boolean isTimeUp = m_timer.hasElapsed(m_duration);
    if (isTimeUp) {
      if (m_iwaitCount > 0) {
        isTimeUp = false;
        end(false);
        initialize();
      }
    }
    return isTimeUp;
  }

  @Override
  public boolean runsWhenDisabled() {
    return true;
  }

}
