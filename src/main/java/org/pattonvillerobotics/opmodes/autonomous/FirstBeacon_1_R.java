package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.pattonvillerobotics.commoncode.enums.AllianceColor;
import org.pattonvillerobotics.commoncode.robotclasses.drive.EncoderDrive;
import org.pattonvillerobotics.opmodes.CustomRobotParameters;
import org.pattonvillerobotics.robotclasses.CommonAutonomous;

/**
 * Created by mostafay on 10/4/16.
 */

@Autonomous(name = "ToBeaconOne_R", group = "Autonomous")
public class FirstBeacon_1_R extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        //drive 60 in forward
        //push cap ball
        //turn 90 left
        //55 inches forward
        //Read beacon color
        //5 Back
        //rotate presser mech.
        //10 inches forward
        //Press beacon

        CommonAutonomous.setUp(AllianceColor.RED, hardwareMap, new EncoderDrive(hardwareMap, this, CustomRobotParameters.ROBOT_PARAMETERS));

        CommonAutonomous.wallPos1ToBeacon1();
        CommonAutonomous.beacon1ToBeacon2();
        //CommonAutonomous.pressBeacon();

    }
}