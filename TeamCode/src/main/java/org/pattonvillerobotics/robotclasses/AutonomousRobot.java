package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.pattonvillerobotics.commoncode.robotclasses.drive.MecanumEncoderDrive;
import org.pattonvillerobotics.opmodes.CustomizedRobotParameters;

/**
 * Created by skaggsw on 10/19/17.
 * <p>
 * A variation of the Robot class, changing the drive object to a MecanumEncoderDrive class, the
 * class used during Autonomous
 */

public class AutonomousRobot extends Robot {

    /**
     * @param hardwareMap a hardwaremap to setup the mechanisms and drive
     * @param opMode the LinearOpMode to pass to the drive class
     */
    public AutonomousRobot(HardwareMap hardwareMap, LinearOpMode opMode) {
        super(hardwareMap,opMode);
        drive = new MecanumEncoderDrive(hardwareMap,opMode, CustomizedRobotParameters.ROBOT_PARAMETERS);
    }
}
