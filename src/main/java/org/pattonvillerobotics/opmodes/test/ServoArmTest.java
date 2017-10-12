package org.pattonvillerobotics.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.pattonvillerobotics.commoncode.robotclasses.gamepad.GamepadData;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableButton;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableGamepad;
import org.pattonvillerobotics.robotclasses.ServoArm;

/**
 * Created by skaggsw on 10/5/17.
 */
@TeleOp(name="Servo Arm Test")
public class ServoArmTest extends LinearOpMode {

    private ServoArm servoArm;
    private ListenableGamepad gamepad;

    @Override
    public void runOpMode() throws InterruptedException {

        servoArm = new ServoArm(hardwareMap, this);
        gamepad = new ListenableGamepad();

        gamepad.getButton(GamepadData.Button.X).addListener(ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                servoArm.extendArm();
            }
        });

        gamepad.getButton(GamepadData.Button.Y).addListener(ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                servoArm.retractArm();
            }
        });

        waitForStart();

        while(opModeIsActive()) {
            servoArm.senseBallColor();
            telemetry.addData("Ball Color:", servoArm.ballColor);
            telemetry.addData("Servo Position:", servoArm.getServoPosition());
            telemetry.update();
        }
    }
}
