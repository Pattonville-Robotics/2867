package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.pattonvillerobotics.commoncode.robotclasses.drive.AbstractDrive;
import org.pattonvillerobotics.commoncode.robotclasses.drive.MecanumEncoderDrive;
import org.pattonvillerobotics.commoncode.robotclasses.drive.SimpleMecanumDrive;
import org.pattonvillerobotics.opmodes.CustomizedRobotParameters;
import org.pattonvillerobotics.robotclasses.enums.OpModeType;

/**
 * Created by skaggsw on 10/19/17.
 */

public class Robot {

    public BenClaw claw;
    public DcMotor slideMotor;
    public ServoArm arm;
    public AbstractDrive drive;

    public Robot(HardwareMap hardwareMap, LinearOpMode opMode, OpModeType opModeType) {
        claw = new BenClaw(hardwareMap,opMode);
        arm = new ServoArm(hardwareMap,opMode);
        slideMotor = hardwareMap.dcMotor.get("slide_motor");
        switch (opModeType) {
            case TELEOP:
                drive = new SimpleMecanumDrive(opMode,hardwareMap);
                break;
            case AUTONOMOUS:
                drive = new MecanumEncoderDrive(hardwareMap,opMode, CustomizedRobotParameters.ROBOT_PARAMETERS);
                break;
        }
    }
}