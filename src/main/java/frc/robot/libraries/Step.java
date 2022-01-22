package frc.robot.libraries;

import java.util.function.BooleanSupplier;

public class Step {
    String m_name;
    BooleanSupplier m_enabled;

    public Step(String name, BooleanSupplier enabled) {
        m_name = name;
        m_enabled = enabled;
    }

    public boolean isTrue() {
        return this.m_enabled.getAsBoolean(); 
    }

    public String getName() {
        return this.m_name;
    }
}
