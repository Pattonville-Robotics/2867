package org.pattonvillerobotics.opmodes;

import org.pattonvillerobotics.commoncode.robotclasses.drive.RobotParameters;

/**
 * Created by developer on 10/4/16.
 */

public class CustomRobotParameters {

    public static final RobotParameters ROBOT_PARAMETERS;

    static {
        ROBOT_PARAMETERS = new RobotParameters.Builder()
                .encodersEnabled(true)
                .gyroEnabled(false)
                .driveGearRatio(2) //Needs Edit
                .wheelBaseRadius(14) //Needs Edit
                .wheelRadius(2) //Needs Edit
                .build();
    }

}
