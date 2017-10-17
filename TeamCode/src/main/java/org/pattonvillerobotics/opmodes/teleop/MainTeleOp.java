package org.pattonvillerobotics.opmodes.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.apache.commons.math3.util.FastMath;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.drive.SimpleMecanumDrive;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.GamepadData;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableButton;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableGamepad;

@TeleOp(name = "MainTeleOp", group = OpModeGroups.MAIN)
public class MainTeleOp extends LinearOpMode {

    private SimpleMecanumDrive drive;
    private ListenableGamepad gamepad;
    private boolean fieldOrientedDriveMode;

    private BNO055IMU imu;
    //private BenClaw claw;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        double[] polarCoords;
        Orientation angles;

        waitForStart();

        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);

        while (opModeIsActive()) {
            polarCoords = SimpleMecanumDrive.toPolar(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            angles = imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
            gamepad.update(new GamepadData(gamepad1));

            drive.moveFreely(polarCoords[1] - (fieldOrientedDriveMode ? angles.firstAngle : 0), polarCoords[0], gamepad1.right_stick_x);

            telemetry.addData("Field Oriented Drive", fieldOrientedDriveMode);

            telemetry.addData("Left Stick Radius", polarCoords[0]);
            telemetry.addData("Left Stick Angle", FastMath.toDegrees(polarCoords[1] + (fieldOrientedDriveMode ? angles.firstAngle : 0)));
            telemetry.addData("Right Stick X", gamepad1.right_stick_x);
            telemetry.addData("Angles", angles.toString());


            telemetry.update();
            idle();
        }
    }

    public void initialize() {

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        drive = new SimpleMecanumDrive(this, hardwareMap);
        gamepad = new ListenableGamepad();

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

        gamepad.getButton(GamepadData.Button.Y).addListener(ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                if (gamepad1.back) {
                    imu.stopAccelerationIntegration();
                    imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);
                }
                fieldOrientedDriveMode = !fieldOrientedDriveMode;
            }
        });
    }
}
