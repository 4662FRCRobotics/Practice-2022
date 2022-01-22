// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveConstants {
        public static final int kLeftMotor1Port = 2;
        public static final int kLeftMotor2Port = 3;
        public static final int kRightMotor1Port = 4;
        public static final int kRightMotor2Port = 5;
        public static final boolean kIS_DRIVE_INVERTED = true;

        public static final double kRAMP_RATE = 1.0;
        public static final int kCURRENT_LIMT = 40;

        public static final double kGEARBOX_REDUCTION = (50.0/12.0) * (60.0/14.0);
        public static final double kTIRE_SIZE_IN = 7.9;
        public static final double kTIRE_SIZE_M = Units.inchesToMeters(kTIRE_SIZE_IN);
        public static final int kPULSE_PER_ROTATION = 1;
        public static final double kENCODER_DISTANCE_PER_PULSE_M = ((double) kPULSE_PER_ROTATION / kGEARBOX_REDUCTION) * (kTIRE_SIZE_M * Math.PI);
        public static final double kTRACK_WIDTH_M = 0.64;

        //public static final DifferentialDriveKinematics K_DRIVE_KINEMATICS = new DifferentialDriveKinematics(kTRACK_WIDTH_M);
        

        public static final double kDRIVE_P = 0.3;
        public static final double kDRIVE_I = 0.0;
        public static final double kDRIVE_D = 0.0;
        public static final double kDRIVE_TOLERANCE = 2;
        public static final double kDRIVE_PID_LIMIT = 0.75;
        //public static final double kTURN_ANGLE_TOLERANCE_DEG_PER_S = 10;
        /*
        public static final double kKEEP_HEADING_P =  0.2;
		public static final double kKEEP_HEADING_I =  0.0;
		public static final double kKEEP_HEADING_D = 0.4;
        public static final double kKEEP_HEADING_TOLERANCE =  1;
        */
    }
    
}
