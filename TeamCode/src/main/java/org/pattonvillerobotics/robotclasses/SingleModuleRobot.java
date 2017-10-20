package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by skaggsw on 10/19/17.
 */

public class SingleModuleRobot extends Robot {

    public SingleModuleRobot(HardwareMap hardwareMap, LinearOpMode opMode) {
        super(hardwareMap,opMode);
        slideMotor = null;
    }
}