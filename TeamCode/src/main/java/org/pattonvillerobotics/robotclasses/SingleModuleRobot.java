package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by skaggsw on 10/19/17.
 * <p>
 * A variation of the Robot class, used for the robot when only one REV module is available
 */

public class SingleModuleRobot extends Robot {

    /**
     * @param hardwareMap a hardwaremap to setup the mechanisms and drive
     * @param opMode the LinearOpMode to pass to the drive class
     */
    public SingleModuleRobot(HardwareMap hardwareMap, LinearOpMode opMode) {
        super(hardwareMap,opMode);
        slideMotor = null;
    }
}