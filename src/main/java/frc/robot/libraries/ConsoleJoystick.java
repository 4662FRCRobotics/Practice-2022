package frc.robot.libraries;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.*;

/** Add your docs here. */
public class ConsoleJoystick extends Joystick {

    public Button cnsl_btn_1 = new JoystickButton(this, 1);

    private static final int kAXIS_X = 0;
    private static final int kAXIS_THROTTLE = 4;
    private static final int kAXIS_Y = 1;
    private static final int kAXIS_TWIST = 3;

    private static final int kPOV_SW_0 = 0;
    private static final int kPOV_SW_1 = 1;

    public int getROT_SW_0() {
        return this.getPOV(kPOV_SW_0) / 45;
    }

    public int getROT_SW_1() {
        // System.out.println("Get ROT SW 1 " + this.getPOV(kPOV_SW_1)/45);
        return this.getPOV(kPOV_SW_1) / 45;
    }

    public double getXAxis() {
        return getRawAxis(kAXIS_X);
    }

    public double getYAxis() {
        return getRawAxis(kAXIS_Y);
    }

    public double getTwistAxis() {
        return getRawAxis(kAXIS_TWIST);
    }

    public double getThrottleAxis() {
        return getRawAxis(kAXIS_THROTTLE);
    }

    public ConsoleJoystick(final int port) {
        super(port);
    }

}
