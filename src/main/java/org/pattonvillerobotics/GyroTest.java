package org.pattonvillerobotics;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.robotclasses.GyroEncoderDrive;

/**
 * Created by developer on 7/31/16.
 */
public class GyroTest extends LinearOpMode {

    public GyroEncoderDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {

        drive = new GyroEncoderDrive(hardwareMap, this);

        drive.turnDegrees(Direction.LEFT, 45, 0.5);
        drive.stop();
        sleep(1000);

        drive.turnDegrees(Direction.LEFT, 90, 0.5);
        drive.stop();
        sleep(1000);

        drive.turnDegrees(Direction.RIGHT, 135, 0.5);
        drive.stop();
        sleep(1000);

        drive.turnDegrees(Direction.RIGHT, 180, 0.5);
        drive.stop();
        sleep(1000);

        drive.turnDegrees(Direction.LEFT, 360, 0.5);
        drive.stop();
        sleep(1000);

        drive.turnDegrees(Direction.RIGHT, 360, 0.5);
        drive.stop();
        sleep(1000);

    }

}
