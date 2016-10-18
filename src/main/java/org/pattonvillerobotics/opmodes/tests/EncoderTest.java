package org.pattonvillerobotics.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.robotclasses.drive.EncoderDrive;
import org.pattonvillerobotics.opmodes.CustomRobotParameters;

/**
 * Created by developer on 10/15/16.
 */

@Autonomous(name = "EndoerTest", group = "Tests")
public class EncoderTest extends LinearOpMode {

    private EncoderDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();
        waitForStart();

        drive.moveInches(Direction.FORWARD, 50, 0.5);
        sleep(1000);
        drive.moveInches(Direction.BACKWARD, 50, 0.5);
        sleep(1000);
        drive.moveInches(Direction.FORWARD, 25, 0.5);
        sleep(1000);
        drive.moveInches(Direction.BACKWARD, 25, 0.5);
        sleep(1000);

        drive.rotateDegrees(Direction.LEFT, 90, 0.5);
        sleep(1000);
        drive.rotateDegrees(Direction.RIGHT, 90, 0.5);
        sleep(1000);
        drive.rotateDegrees(Direction.LEFT, 180, 0.5);
        sleep(1000);
        drive.rotateDegrees(Direction.RIGHT, 180, 0.5);
        sleep(1000);

    }

    private void initialize(){

        drive = new EncoderDrive(hardwareMap, this, CustomRobotParameters.ROBOT_PARAMETERS);

    }

}
