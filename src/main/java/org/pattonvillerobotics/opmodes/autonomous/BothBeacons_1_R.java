package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.pattonvillerobotics.commoncode.enums.AllianceColor;
import org.pattonvillerobotics.opmodes.CustomRobotParameters;
import org.pattonvillerobotics.robotclasses.CommonAutonomous;
import org.pattonvillerobotics.robotclasses.LineFollowerDrive;

/**
 * Created by mostafay on 10/4/16.
 */

@Autonomous(name = "BothBeacon_R", group = "Autonomous")
public class BothBeacons_1_R extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        //60 inches forward
        //push cap ball
        //90 degrees left
        //60 inches forward
        //read beacon color
        //rotate presser mech
        //press beacon
        //25 inches backward
        //90 left
        //50 inches forward
        //90 left
        //20 forward
        //read beacon color
        //rotate presser mech
        //4 inches forward
        //press beacon

        CommonAutonomous commonAutonomous = new CommonAutonomous(AllianceColor.RED, hardwareMap, this, new LineFollowerDrive(hardwareMap, this, CustomRobotParameters.ROBOT_PARAMETERS));

        waitForStart();

        commonAutonomous.wallPos1ToBeacon1();
        commonAutonomous.pressBeacon();
        commonAutonomous.beacon1ToBeacon2();
        commonAutonomous.pressBeacon();
        commonAutonomous.tape2ToBall();

    }
}
