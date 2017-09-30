package org.pattonvillerobotics.opmodes;

import org.pattonvillerobotics.commoncode.robotclasses.drive.RobotParameters;

/**
 * Created by gregbahr on 9/30/17.
 */

public class CustomizedRobotParameters {
    public static final RobotParameters ROBOT_PARAMETERS;

    static {
        ROBOT_PARAMETERS = new RobotParameters.Builder()
                                .wheelBaseRadius(10)
                                .encodersEnabled(true)
                                .wheelRadius(2)
                                .gyroEnabled(true)
                                .driveGearRatio(3)
                                .build();
    }
}
