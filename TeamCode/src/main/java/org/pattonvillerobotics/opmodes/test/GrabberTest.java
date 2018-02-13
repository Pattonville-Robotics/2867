package org.pattonvillerobotics.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.GamepadData;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableButton;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableGamepad;
import org.pattonvillerobotics.robotclasses.mechanisms.BenClaw;

@TeleOp(name = "GrabberTest", group = OpModeGroups.TESTING)
public class GrabberTest extends LinearOpMode {

    private BenClaw claw, claw2;
    private ListenableGamepad gamepad;

    @Override
    public void runOpMode() throws InterruptedException {
        claw = new BenClaw(hardwareMap, this, "top_claw");
        claw2 = new BenClaw(hardwareMap, this, "bottom_claw");

        gamepad = new ListenableGamepad();

        gamepad.getButton(GamepadData.Button.X).addListener(ListenableButton.ButtonState.JUST_PRESSED, () -> claw.togglePosition());
        gamepad.getButton(GamepadData.Button.Y).addListener(ListenableButton.ButtonState.JUST_PRESSED, () -> claw2.togglePosition());

        waitForStart();

        while (opModeIsActive()) {
            gamepad.update(gamepad1);
            telemetry.addData("Claw 1", claw.getServoPosition());
            telemetry.addData("Claw 2", claw2.getServoPosition());
            telemetry.update();
        }
    }
}
