package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.robotclasses.drive.SimpleDrive;

/**
 * Created by mostafay on 10/4/16.
 */

@Autonomous(name = "FiveSeconds", group = "None")
public class CapBallPush_1 extends LinearOpMode {

    private SimpleDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        //drive forward 60in
        //Push Cap Ball
        //5 inches forward
        //Park on center vortex

        initialize();
        waitForStart();

        while(opModeIsActive()){
            drive.move(Direction.FORWARD, 0.5);
            telemetry.addData("OpModeIsActive", "Should be moving");
            telemetry.addData("MotorPower", drive.leftDriveMotor.getPower());
            telemetry.update();
        }

        /*while(opModeIsActive()){
            drive.moveFreely(gamepad1.left_stick_y, gamepad1.right_stick_y);
        }*/

    }

    private void initialize(){

        drive = new SimpleDrive(this, hardwareMap);

    }

}