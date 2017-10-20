package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.pattonvillerobotics.commoncode.robotclasses.drive.AbstractDrive;
import org.pattonvillerobotics.commoncode.robotclasses.drive.SimpleMecanumDrive;

/**
 * Created by skaggsw on 10/19/17.
 */

public class Robot {

    public BenClaw claw;
    public DcMotor slideMotor;
    public ServoArm arm;
    public AbstractDrive drive;

    public Robot(HardwareMap hardwareMap, LinearOpMode opMode) {
        claw = new BenClaw(hardwareMap,opMode);
        arm = new ServoArm(hardwareMap,opMode);
        slideMotor = hardwareMap.dcMotor.get("slide_motor");
        drive = new SimpleMecanumDrive(opMode,hardwareMap);
    }
}