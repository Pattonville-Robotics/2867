package org.pattonvillerobotics.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.pattonvillerobotics.commoncode.enums.ColorSensorColor;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.GamepadData;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableButton;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableGamepad;
import org.pattonvillerobotics.robotclasses.ServoArm;

/**
 * Created by skaggsw on 10/5/17.
 */
@TeleOp(name = "ServoArmTest", group = OpModeGroups.TESTING)
public class ServoArmTest extends LinearOpMode {

    private ServoArm servoArm;
    private ListenableGamepad gamepad;

    @Override
    public void runOpMode() throws InterruptedException {

        servoArm = new ServoArm(hardwareMap);
        gamepad = new ListenableGamepad();

        gamepad.getButton(GamepadData.Button.X).addListener(ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                if(servoArm.ballColor == ColorSensorColor.BLUE) {
                    servoArm.extendArm();
                } else if(servoArm.ballColor == ColorSensorColor.RED) {
                    servoArm.retractArm();
                }
            }
        });

        waitForStart();

        while(opModeIsActive()) {
            servoArm.senseBallColor();
            telemetry.addData("Ball Color:", servoArm.ballColor);
            telemetry.addData("Servo Position:", servoArm.getServoPosition());
            telemetry.update();
            gamepad.update(new GamepadData(gamepad1));
        }
    }
}
