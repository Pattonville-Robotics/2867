package org.pattonvillerobotics.team2867.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.pattonvillerobotics.commoncode.OpMode;
import org.pattonvillerobotics.team2867.robotclasses.Autonomous;
import org.pattonvillerobotics.team2867.robotclasses.Globals;

/**
 * Created by zahnerj01 on 3/3/16.
 */
@OpMode("Red Dump")
public class R_Dump extends LinearOpMode {

    Autonomous autonomous;

    @Override
    public void runOpMode() throws InterruptedException {

        autonomous = new Autonomous(Globals.Colors.RED, this);
        waitForStart();

        autonomous.red1ToBeacon();

    }
}
