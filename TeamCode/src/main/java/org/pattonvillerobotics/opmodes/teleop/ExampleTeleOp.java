package org.pattonvillerobotics.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.drive.SimpleDrive;
import org.pattonvillerobotics.commoncode.robotclasses.drive.SimpleMecanumDrive;

@Disabled
@TeleOp(name = "ExampleTeleOp")
public class ExampleTeleOp extends LinearOpMode {

    public SimpleMecanumDrive drive;

    @Override
    public void runOpMode() {

        initialize();

        Vector2D polarCoords;

        waitForStart();

        while(opModeIsActive()) {
            polarCoords = SimpleMecanumDrive.toPolar(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            drive.moveFreely(polarCoords.getY(), polarCoords.getX(), -gamepad1.right_stick_x);
        }
    }

    public void initialize() {
        drive = new SimpleMecanumDrive(this, hardwareMap);
    }
}
