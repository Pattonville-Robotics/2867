package org.pattonvillerobotics.team2867.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.pattonvillerobotics.team2867.robotclasses.Autonomous;
import org.pattonvillerobotics.team2867.robotclasses.Globals;

/**
 * Created by zahnerj01 on 1/24/16.
 */
public class R_2_FG extends LinearOpMode {

    Autonomous autonomous;

    @Override
    public void runOpMode() throws InterruptedException {

        autonomous = new Autonomous(Globals.Colors.RED, this);

        autonomous.driveToBeacon();

        /* go back 12 in
        turn left
        go forward 24 in
         */
    }

}