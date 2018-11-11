package org.pattonvillerobotics.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.GamepadData;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableButton;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableGamepad;

@TeleOp(name = "Intake Test TeleOp", group = OpModeGroups.TESTING)
public class IntakeTestTeleOp extends LinearOpMode {

    ListenableGamepad gamepad;
    DcMotor extendMotor, intakeMotor;
    //Servo   leftDropServo, rightDropServo;

    boolean intakeOn;

    @Override
    public void runOpMode() {
        initialize();

        waitForStart();

        while (opModeIsActive()) {
            gamepad.update(gamepad1);

            extendMotor.setPower(gamepad1.right_stick_y);

            //rightDropServo.setPosition(rightDropServo.getPosition()+gamepad1.left_stick_y);
            //leftDropServo.setPosition(leftDropServo.getPosition()+gamepad1.left_stick_y);
        }
    }

    public void initialize() {
        gamepad = new ListenableGamepad();

        extendMotor =       hardwareMap.dcMotor.get("extension_motor");
        //intakeMotor =       hardwareMap.dcMotor.get("intake_motor");
        //leftDropServo =     hardwareMap.servo.get("left_drop_servo");
        //rightDropServo =    hardwareMap.servo.get("right_drop_servo");

        gamepad.addButtonListener(GamepadData.Button.A, ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                if (intakeOn) {
                    //intakeMotor.setPower(0.6);
                } else {
                    //intakeMotor.setPower(0);
                }
                intakeOn = !intakeOn;
            }
        });


    }
}
