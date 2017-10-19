package org.pattonvillerobotics.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;

/**
 * Created by skaggsw on 10/19/17.
 */
@TeleOp(name = "SliderTest", group = OpModeGroups.TESTING)
public class SliderTest extends LinearOpMode {

    private DcMotor slideMotor;

    @Override
    public void runOpMode() throws InterruptedException {

        slideMotor = hardwareMap.dcMotor.get("slide_motor");
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();

        while(opModeIsActive()) {
            slideMotor.setPower(gamepad1.right_trigger-gamepad1.left_trigger);
        }
    }
}
