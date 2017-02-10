package org.pattonvillerobotics.opmodes;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.pattonvillerobotics.commoncode.robotclasses.drive.RobotParameters;

/**
 * Created by developer on 10/4/16.
 */

public class CustomRobotParameters {

    public static final RobotParameters ROBOT_PARAMETERS;

    static {
        ROBOT_PARAMETERS = new RobotParameters.Builder()
                .leftDriveMotorDirection(DcMotorSimple.Direction.FORWARD)
                .rightDriveMotorDirection(DcMotorSimple.Direction.REVERSE)
                .encodersEnabled(true)
                .gyroEnabled(true)
                .driveGearRatio(3) //Needs Edit
                .wheelBaseRadius(8.25 * 180/170) //Needs Edit
                .wheelRadius(2 * (45.0/50.0) * (98.0/100.0)*(16.0/50.0))
                .dcMotorMaxSpeed(1440)
                .build();
    }

}
