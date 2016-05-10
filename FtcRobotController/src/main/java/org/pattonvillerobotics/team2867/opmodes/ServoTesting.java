package org.pattonvillerobotics.team2867.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.pattonvillerobotics.commoncode.OpMode;
import org.pattonvillerobotics.team2867.robotclasses.DebrisBucket;
import org.pattonvillerobotics.team2867.robotclasses.Globals;

/**
 * Created by zahnerj01 on 1/26/16.
 */
@OpMode("Servo Testing")
public class ServoTesting extends LinearOpMode {

    DebrisBucket debrisBucket;

    public void runOpMode() throws InterruptedException {

        initialize();
        waitForStart();

        while (opModeIsActive()) {
            doLoop();
        }


    }

    public void initialize() {
        debrisBucket = new DebrisBucket(hardwareMap);
        debrisBucket.setPosition(Globals.DEBRIS_BUCKET_DEFAULT);
    }

    public void doLoop() {

        if (gamepad1.a) {
            debrisBucket.dumpDebris();
        } else if (gamepad1.b) {
            debrisBucket.returnToStart();
        }

    }
}
