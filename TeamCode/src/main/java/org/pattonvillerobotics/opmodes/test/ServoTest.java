package org.pattonvillerobotics.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;

/**
 * Created by gregbahr on 10/5/17.
 */
@TeleOp(name = "ServoTest", group = OpModeGroups.TESTING)
public class ServoTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo servo = hardwareMap.servo.get("servo");

        waitForStart();

        while (opModeIsActive()) {
            servo.setPosition(gamepad1.left_stick_y);
        }
    }
}
