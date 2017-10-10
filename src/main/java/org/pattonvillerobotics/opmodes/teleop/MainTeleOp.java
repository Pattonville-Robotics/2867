package org.pattonvillerobotics.opmodes.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.apache.commons.math3.util.FastMath;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.drive.SimpleMecanumDrive;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.GamepadData;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableButton;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableGamepad;

@TeleOp(name = "MainTeleOp", group = OpModeGroups.MAIN)
public class MainTeleOp extends LinearOpMode {

    private SimpleMecanumDrive drive;
    private ListenableGamepad gamepad;

    private BNO055IMU imu;
    //private BenClaw claw;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        double[] polarCoords;

        waitForStart();

        while(opModeIsActive()) {
            polarCoords = SimpleMecanumDrive.toPolar(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            gamepad.update(new GamepadData(gamepad1));

            drive.moveFreely(polarCoords[1], polarCoords[0], gamepad1.right_stick_x);

            telemetry.addData("Left Stick Radius", polarCoords[0]);
            telemetry.addData("Left Stick Angle", FastMath.toDegrees(polarCoords[1]));
            telemetry.addData("Right Stick X", gamepad1.right_stick_x);
            telemetry.addData("LeftX", gamepad1.left_stick_x);
            telemetry.addData("LeftY", gamepad1.left_stick_y);
            telemetry.addData("LR", drive.leftRearMotor.getPower());
            telemetry.addData("LF", drive.leftDriveMotor.getPower());
            telemetry.addData("RR", drive.rightRearMotor.getPower());
            telemetry.addData("RF", drive.rightDriveMotor.getPower());

            telemetry.update();
            idle();
        }
    }

    public void initialize() {

        imu = hardwareMap.get(BNO055IMU.class, "imu");

        drive = new SimpleMecanumDrive(this, hardwareMap);
        gamepad = new ListenableGamepad();

        drive.leftRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        drive.rightRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        drive.leftDriveMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        drive.rightDriveMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        //claw = new BenClaw(hardwareMap ,this);

        gamepad.getButton(GamepadData.Button.X).addListener(ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                // open and close
//                if (claw.isOpen()) {
//                    claw.close();
//                } else {
//                    claw.open();
//                }
            }
        });
    }
}
