package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.pattonvillerobotics.commoncode.robotclasses.drive.AbstractDrive;
import org.pattonvillerobotics.commoncode.robotclasses.drive.SimpleMecanumDrive;
import org.pattonvillerobotics.robotclasses.mechanisms.BenClaw;
import org.pattonvillerobotics.robotclasses.mechanisms.ServoArm;

/**
 * Created by skaggsw on 10/19/17.
 * <p>
 * Collection of each of our mechanism classes, our drive class, and the linear-slide motor, used during TeleOp
 */

public class Robot {

    public BenClaw claw;
    public DcMotor slideMotor;
    public ServoArm arm;
    public AbstractDrive drive;

    /**
     * @param hardwareMap a hardwaremap to setup the mechanisms and drive
     * @param opMode the LinearOpMode to pass to the drive class
     */
    public Robot(HardwareMap hardwareMap, LinearOpMode opMode) {
        claw = new BenClaw(hardwareMap, opMode);
        arm = new ServoArm(hardwareMap, opMode);
        slideMotor = hardwareMap.dcMotor.get("slide_motor");
        drive = new SimpleMecanumDrive(opMode,hardwareMap);
    }
}