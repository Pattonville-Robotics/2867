package org.pattonvillerobotics.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.apache.commons.math3.util.FastMath;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;

/**
 * Created by gregbahr on 9/30/17.
 */

@TeleOp(name="MecanumTest", group = OpModeGroups.TESTING)
public class MecTest extends LinearOpMode {

    private DcMotor LR, LF, RR, RF;

    @Override
    public void runOpMode() throws InterruptedException {
        LR = hardwareMap.dcMotor.get("LR");
        LF = hardwareMap.dcMotor.get("LF");
        RR = hardwareMap.dcMotor.get("RR");
        RF = hardwareMap.dcMotor.get("RF");

        double cos135 = FastMath.cos(45);
        double sin135 = -cos135;
        double controllertheta;
        double controllerangle;
        double controllerradius;

        waitForStart();

        while(opModeIsActive()) {
            controllertheta = FastMath.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x);
            controllerradius = FastMath.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            controllerangle = controllertheta > 0 ? controllertheta : (2*FastMath.PI)-(-1*controllertheta);

            LR.setPower((controllerradius*(cos135*FastMath.cos(controllerangle + (FastMath.PI / 4))))+gamepad1.right_stick_x);
            LF.setPower((controllerradius*(sin135*FastMath.sin(controllerangle + (FastMath.PI / 4))))+gamepad1.right_stick_x);
            RR.setPower((controllerradius*(sin135*FastMath.sin(controllerangle + (FastMath.PI / 4))))-gamepad1.left_stick_x);
            RF.setPower((controllerradius*(cos135*FastMath.cos(controllerangle + (FastMath.PI / 4))))-gamepad1.left_stick_x);

            telemetry.addData("Left Stick Radius", controllerradius);
            telemetry.addData("Left Stick Angle", FastMath.toDegrees(controllerangle));
            telemetry.addData("Right Stick X", gamepad1.right_stick_x);

            telemetry.update();
            idle();
        }
    }
}
