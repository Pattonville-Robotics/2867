package org.pattonvillerobotics.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.GamepadData;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableButton;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableGamepad;
import org.pattonvillerobotics.robotclasses.IntakeMechanism;

@TeleOp(name = "Intake Test TeleOp", group = OpModeGroups.TESTING)
public class IntakeTestTeleOp extends LinearOpMode {

    IntakeMechanism intakeMechanism;

    @Override
    public void runOpMode() {
        initialize();

        waitForStart();

        while (opModeIsActive()) {
            intakeMechanism.pivotMotor.setPower(gamepad1.right_trigger-gamepad1.left_trigger);
        }
    }

    public void initialize() {
        intakeMechanism = new IntakeMechanism(hardwareMap, this);
    }
}
