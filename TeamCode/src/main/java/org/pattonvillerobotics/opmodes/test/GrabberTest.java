package org.pattonvillerobotics.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.GamepadData;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableButton;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableGamepad;
import org.pattonvillerobotics.robotclasses.BenClaw;

@TeleOp(name = "GrabberTest", group = OpModeGroups.TESTING)
public class GrabberTest extends LinearOpMode {

    private BenClaw grabber;
    private DcMotor slideMotor;
    private ListenableGamepad gamepad;

    @Override
    public void runOpMode() throws InterruptedException {
        grabber = new BenClaw(hardwareMap, this);
        slideMotor = hardwareMap.dcMotor.get("slide_motor");
        gamepad = new ListenableGamepad();

        gamepad.getButton(GamepadData.Button.X).addListener(ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                grabber.open();
            }
        });

        gamepad.getButton(GamepadData.Button.Y).addListener(ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                grabber.close();
            }
        });

        gamepad.getButton(GamepadData.Button.DPAD_UP).addListener(ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                grabber.increment += 100;
            }
        });

        gamepad.getButton(GamepadData.Button.DPAD_DOWN).addListener(ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                grabber.increment -= 100;
            }
        });



        waitForStart();

        while(opModeIsActive()) {
            gamepad.update(new GamepadData(gamepad1));
            telemetry.addData("Motor Position: ", grabber.getMotorPosition());
            telemetry.addData("Increment: ", grabber.increment);
            slideMotor.setPower(gamepad1.right_trigger-gamepad1.left_trigger);
            telemetry.update();
        }
    }
}
