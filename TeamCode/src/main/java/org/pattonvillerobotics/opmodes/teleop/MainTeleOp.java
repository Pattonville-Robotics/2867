package org.pattonvillerobotics.opmodes.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.drive.SimpleMecanumDrive;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.GamepadData;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableButton;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableGamepad;
import org.pattonvillerobotics.robotclasses.CustomizedRobotParameters;
import org.pattonvillerobotics.robotclasses.IntakeMechanism;
import org.pattonvillerobotics.robotclasses.TapeMeasureLifter;

@TeleOp(name = "MainTeleOp", group = OpModeGroups.MAIN)
public class MainTeleOp extends LinearOpMode {

    public SimpleMecanumDrive drive;
    public ListenableGamepad listenableGamepad1;
    private boolean fieldOrientedDriveMode = false;
    private BNO055IMU imu;

    @Override
    public void runOpMode() {

        initialize();
        Vector2D polarCoords;
        Orientation angles;

        waitForStart();

        while (opModeIsActive()) {
            polarCoords = SimpleMecanumDrive.toPolar(-gamepad1.left_stick_x, gamepad1.left_stick_y);
            angles = imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);

            listenableGamepad1.update(gamepad1);
            drive.moveFreely(polarCoords.getY() + (fieldOrientedDriveMode ? angles.secondAngle + (Math.PI / 2.) : 0), polarCoords.getX(), -gamepad1.right_stick_x);

            telemetry.clearAll();

            telemetry.update();
        }
    }

    public void initialize() {
        listenableGamepad1 = new ListenableGamepad();

        drive = new SimpleMecanumDrive(this, hardwareMap);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        drive.leftRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        drive.rightRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        drive.leftDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        drive.rightDriveMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }
}
