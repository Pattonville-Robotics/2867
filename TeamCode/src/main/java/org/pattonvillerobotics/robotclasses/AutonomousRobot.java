package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.pattonvillerobotics.commoncode.robotclasses.drive.MecanumEncoderDrive;
import org.pattonvillerobotics.opmodes.CustomizedRobotParameters;

/**
 * Created by skaggsw on 10/19/17.
 */

public class AutonomousRobot extends Robot {

    public AutonomousRobot(HardwareMap hardwareMap, LinearOpMode opMode) {
        super(hardwareMap,opMode);
        drive = new MecanumEncoderDrive(hardwareMap,opMode, CustomizedRobotParameters.ROBOT_PARAMETERS);
    }
}
