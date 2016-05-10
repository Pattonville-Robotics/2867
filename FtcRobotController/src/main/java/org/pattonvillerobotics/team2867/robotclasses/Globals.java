package org.pattonvillerobotics.team2867.robotclasses;

/**
 * Created by zahnerj01 on 12/10/15.
 */
public final class Globals {

    // ---------- MOTOR NAMES ---------- \\
    public static final String MOTOR_DRIVE_LEFT = "drive_left";
    public static final String MOTOR_DRIVE_RIGHT = "drive_right";
    public static final String SLIDE_LEFT = "slide_left";
    public static final String SLIDE_RIGHT = "slide_right";
    public static final String HOPPER = "hopper";


    // ---------- SERVO NAMES ---------- \\
    public static final String CLIMBER_BUCKET = "climber_bucket";
    public static final String DEBRIS_BUCKET = "debris_bucket";
    public static final String CLAMP_LEFT = "clamp_left";
    public static final String CLAMP_RIGHT = "clamp_right";
    public static final String ZLT = "zlt";


    // ---------- SENSOR NAMES ---------- \\
    public static final String GYRO_SENSOR = "gyro_sensor";
    public static final String COLOR_SENSOR = "color_sensor";
    public static final String ODS_SENSOR = "ods_sensor";


    // ---------- DEFAULT SERVO VALUES ---------- \\
    public static final double CLIMBER_BUCKET_DEFAULT = 1.0;
    public static final double DEBRIS_BUCKET_DEFAULT = 0.0;
    public static final double CLAMP_DEFAULT = 0;
    public static final double ZLT_DEFAULT = 1.0;


    // ---------- SPEEDS/POSITIONS/TOLERANCES/ETC ---------- \\
    public static final double DEFAULT_DRIVE_MOTOR_POWER = 0.5;
    public static final double DEFAULT_LINEAR_SLIDE_POWER = 1.0;

    public static final double CLAMP_ENGAGED_POSITION = 0.8;

    public static final double BUTTON_PRESSER_ENGAGED_POSITION = 0.5;

    public static final double CLIMBER_BUCKET_DOWN_POSITION = 0.2;

    public static final double DEBRIS_BUCKET_DUMP_POSITION = 1.0;

    public static final double ZLT_TRIGGERED_POSITION = 0.1;

    public static final double ENCODER_MOVEMENT_TOLERANCE = 12; // 3 degrees
    public static final double GYRO_TURN_TOLERANCE = 5;
    public static final double GYRO_TRIM = 5;


    // ----------- AUTONOMOUS MATH VALUES ---------- \\
    public final static double WALL_POSITION_1 = 48;
    public final static double WALL_POSITION_2 = 72;
    public final static double WALL_LENGTH = 144;
    public final static double Z_MAX = 0;
    public final static double OFFSET = 8;
    public static double X_DISTANCE = 0;
    public static double Y_DISTANCE = 0;
    public static double Z_DISTANCE = 0;


    // ---------- ENUMS ---------- \\
    public static enum DriveDirections {
        LEFT, RIGHT, FORWARD, BACKWARD
    }

    public static enum ButtonPresserDirection {LEFT, RIGHT}

    public static enum ScoringBucket {START, LOW, MIDDLE, HIGH}

    public static enum Colors {RED, BLUE}


}

