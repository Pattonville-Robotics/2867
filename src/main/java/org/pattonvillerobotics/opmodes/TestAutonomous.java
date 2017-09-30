package org.pattonvillerobotics.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.drive.EncoderDrive;

/**
 * Created by gregbahr on 9/30/17.
 */

@Autonomous(name="TestAuto", group = OpModeGroups.TESTING)
public class TestAutonomous extends LinearOpMode {

    private EncoderDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {

        drive = new EncoderDrive(hardwareMap, this, CustomizedRobotParameters.ROBOT_PARAMETERS);

        waitForStart();

        drive.moveInches(Direction.FORWARD, 20, .2);
        drive.rotateDegrees(Direction.LEFT, 90, .2);
    }
}
