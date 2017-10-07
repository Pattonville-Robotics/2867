package org.pattonvillerobotics.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;

@TeleOp(name="GrabberTest", group = OpModeGroups.TESTING)
public class GrabberTest extends LinearOpMode {

    private DcMotor grabber;

    @Override
    public void runOpMode() throws InterruptedException {
        grabber = hardwareMap.dcMotor.get("grabber");

        waitForStart();

        while(opModeIsActive()) {
            grabber.setPower(-gamepad1.left_stick_y);
        }
    }
}
