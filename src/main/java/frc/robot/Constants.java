// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.utilities.Rectangle;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final double JOYSTICK_DEADBAND = 0.35; // the deadband for the joysticks
    public static final double JOYSTICK_ANGLE_DEADBAND = 0.2; // the deadband for the angle of the joysticks
    public static final double JOYSTICK_IDLE_DEADBAND = 0.3; // the deadband to check if the joystick is idle

    public static final Rectangle RAMP = new Rectangle(2.91, 1.51, 4.85, 3.98); // in meters, blue alliance
    public static final Rectangle OPEN_AREA = new Rectangle(4.85, 0.0, 11.69, 8.02); // in meters, blue alliance
    public static final Rectangle ENTRANCE_BOTTOM = new Rectangle(2.91, 0.0, 4.85, 1.51); // in meters, blue alliance
    public static final Rectangle ENTRANCE_TOP = new Rectangle(2.91, 3.98, 4.85, 5.49); // in meters, blue alliance
    public static final Rectangle COMMUNITY_BOTTOM = new Rectangle(0.0, 0.0, 2.91, 1.51); // in meters, blue alliance
    public static final Rectangle COMMUNITY_TOP = new Rectangle(0.0, 3.98, 2.91, 5.49); // in meters, blue alliance
    public static final Rectangle COMMUNITY_MIDDLE = new Rectangle(0, 1.51, 2.91, 3.98); // in meters, blue alliance
    public static final Rectangle LOADING_ZONE = new Rectangle(11.69, 5.55, 16.54, 8.02); // in meters, blue alliance

    public static final double FIELD_WIDTH = 16.54; // in meters
    public static final double FIELD_HEIGHT = 8.02; // in meters

    public static final double LOADING_ANGLE = 44;
    public static final double MANUAL_PLACEMENT = 42;
    public static final double DEPLOY_ANGLE = 20;
    public static final double DEPLOY_ANGLE_LOW = 60;
    // public static final double DEPLOY_ANGLE2 = 20;
    public static final double DEPLOY_HIGH_CUBES1 = 33.5;
    // public static final double DEPLOY_HIGH_CUBES2 = 33.5;
    public static final double AUTONOMOUS_CLIMB_SPEED = 1;
    public static final int CYCLES_PER_SECOND = 50;
    public static final double AUTO_NODES_MAX_TIME = 5;

    // ok technically this is not the right names for these constants,
    // the first is the limelight value and the second is the odometry value.
    // but how it works is the lower the number is the more it trusts the input so
    // for it to make more sense, (i.e. 0.7 is more trust than 0.3), we flipped the
    // names
    public static final double ODOMETRY_TRUST_VALUE = 0.7;
    public static final double LIMELIGHT_TRUST_VALUE = 0.3;

}
