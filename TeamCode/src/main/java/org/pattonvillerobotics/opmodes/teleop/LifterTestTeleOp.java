package org.pattonvillerobotics.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.pattonvillerobotics.commoncode.robotclasses.gamepad.GamepadData;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableButton;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableGamepad;
import org.pattonvillerobotics.robotclasses.TapeMeasureLifter;

@TeleOp(name = "LifterTestTeleOp", group = "Test")
public class LifterTestTeleOp extends LinearOpMode {

    public TapeMeasureLifter lifter;
    public ListenableGamepad gamepad;

    @Override
    public void runOpMode() {

        initialize();

        waitForStart();

        while (opModeIsActive()) {
            gamepad.update(gamepad1);
            telemetry.addData("Lifter Position : ", lifter.winchMotor.getCurrentPosition());
            telemetry.update();
        }
    }

    public void initialize() {

        lifter = new TapeMeasureLifter(hardwareMap, this);

        gamepad = new ListenableGamepad();
        gamepad.addButtonListener(GamepadData.Button.A, ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                lifter.raise();
            }
        });
        gamepad.addButtonListener(GamepadData.Button.B, ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                lifter.lower();
            }
        });
    }
}
