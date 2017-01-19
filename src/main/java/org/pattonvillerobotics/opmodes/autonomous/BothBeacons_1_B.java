package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.pattonvillerobotics.commoncode.enums.AllianceColor;
import org.pattonvillerobotics.opmodes.CustomRobotParameters;
import org.pattonvillerobotics.robotclasses.CommonAutonomous;
import org.pattonvillerobotics.robotclasses.LineFollowerDrive;

/**
 * Created by developer on 11/18/16.
 */
@Autonomous(name = "BothBeacons_B", group = "Autonomous")
public class BothBeacons_1_B extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        CommonAutonomous commonAutonomous = new CommonAutonomous(AllianceColor.BLUE, hardwareMap, this, new LineFollowerDrive(hardwareMap, this, CustomRobotParameters.ROBOT_PARAMETERS));

        waitForStart();

        commonAutonomous.wallPos1ToBeacon1();
        commonAutonomous.pressBeacon();
        commonAutonomous.beacon1ToBeacon2();
        commonAutonomous.pressBeacon();
        //commonAutonomous.tape2ToBall();
    }

}
