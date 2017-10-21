package org.pattonvillerobotics.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.GamepadData;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableButton;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableGamepad;
import org.pattonvillerobotics.robotclasses.Robot;

@TeleOp(name = "GrabberTest", group = OpModeGroups.TESTING)
public class GrabberTest extends LinearOpMode {

    private Robot robot;
    private ListenableGamepad gamepad;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap,this);
        gamepad = new ListenableGamepad();

        gamepad.getButton(GamepadData.Button.X).addListener(ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                robot.claw.opencloseToggle();
            }
        });

        waitForStart();

        while(opModeIsActive()) {
            gamepad.update(new GamepadData(gamepad1));
            telemetry.addData("Servo Position: ", robot.claw.getServoPosition());
            //slideMotor.setPower(gamepad1.right_trigger-gamepad1.left_trigger);
            telemetry.update();
        }
    }
}
