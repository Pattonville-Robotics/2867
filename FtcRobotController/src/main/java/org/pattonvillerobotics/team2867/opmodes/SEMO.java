package org.pattonvillerobotics.team2867.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.pattonvillerobotics.commoncode.OpMode;
import org.pattonvillerobotics.team2867.robotclasses.Drive;
import org.pattonvillerobotics.team2867.robotclasses.Globals;

/**
 * Created by zahnerj01 on 2/6/16.
 */
@OpMode("SEMO")
public class SEMO extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        Drive drive = new Drive(hardwareMap, this);
        drive.moveFreely(Globals.DEFAULT_DRIVE_MOTOR_POWER, Globals.DEFAULT_DRIVE_MOTOR_POWER);
        sleep(10000);
        drive.stopDriveMotors();
    }

}
