package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.robotclasses.drive.SimpleDrive;

/**
 * Created by mostafay on 10/4/16.
 */

public class CapBallPush_1 extends LinearOpMode {

    SimpleDrive drive;
    HardwareMap hardwareMap;

    @Override
    public void runOpMode() throws InterruptedException {
        //drive forward 60in
        //Push Cap Ball
        //5 inches forward
        //Park on center vortex

        drive.move(Direction.FORWARD, 0.5);
        sleep(5000);

    }

    public void initalize(){

        drive = new SimpleDrive(this, hardwareMap);

    }

}