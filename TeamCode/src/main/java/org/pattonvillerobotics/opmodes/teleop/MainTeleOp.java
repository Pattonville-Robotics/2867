package org.pattonvillerobotics.opmodes.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.pattonvillerobotics.commoncode.robotclasses.drive.SimpleMecanumDrive;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableGamepad;

public class MainTeleOp extends LinearOpMode {

    public SimpleMecanumDrive drive;
    public ListenableGamepad gamepad;
    private boolean fieldOrientedDriveMode;
    private BNO055IMU imu;

    @Override
    public void runOpMode() {

        initialize();
        Vector2D polarCoords;
        Orientation angles;

        waitForStart();

        while (opModeIsActive()) {
            polarCoords = SimpleMecanumDrive.toPolar(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            angles = imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
            gamepad.update(gamepad1);
            drive.moveFreely(polarCoords.getY() - (fieldOrientedDriveMode ? angles.secondAngle + (Math.PI / 2.) : 0), polarCoords.getX(), -gamepad1.right_stick_x);
        }
    }

    public void initialize() {
        gamepad = new ListenableGamepad();
        drive = new SimpleMecanumDrive(this, hardwareMap);
    }
}
