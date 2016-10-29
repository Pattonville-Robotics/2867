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

@Autonomous(name = "toBeaconOne_B", group = "Autonomous")
public class SecondBeacon_1_B extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        //60 inches forward
        //Push Cap ball
        //53 right
        //60 inches forward
        //63 degrees right
        //20 inches forward
        //read beacon color
        //4 inches forward
        //press beacon
        CommonAutonomous.setUp(AllianceColor.BLUE, hardwareMap, new EncoderDrive(hardwareMap, this, CustomRobotParameters.ROBOT_PARAMETERS));

        CommonAutonomous.wallPos1ToBeacon1();
    }

}
