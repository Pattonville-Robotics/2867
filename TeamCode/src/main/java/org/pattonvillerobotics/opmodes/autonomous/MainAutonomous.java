package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.drive.MecanumEncoderDrive;
import org.pattonvillerobotics.commoncode.robotclasses.drive.RobotParameters;
import org.pattonvillerobotics.robotclasses.CustomizedRobotParameters;

@Autonomous(name="MainAutonomous", group = OpModeGroups.MAIN)
public class MainAutonomous extends LinearOpMode {

    private MecanumEncoderDrive drive;

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        // lower the robot

        drive.moveInches(Direction.FORWARD, 10, 0.5);
        // take picture, analyze
        boolean goldResult = true;
        int mineralCompensationDistance;
        if(false) {
            //for determining what distance will get the robot to a specific point, depending on which mineral is "chosen"
            mineralCompensationDistance = 43;
        } else {
            drive.moveInches(Direction.RIGHT, 14, 0.5);
            //take picture, analyze
            goldResult = true;
            if(false) {
                mineralCompensationDistance = 62;

            } else {
                //program failed. Zero stars.
                drive.moveInches(Direction.LEFT, 29, 0.5);
                mineralCompensationDistance = 27;
            }
        }

        drive.moveInches(Direction.FORWARD, 8, 0.5);
        drive.moveInches(Direction.BACKWARD, 8, 0.5);

        drive.moveInches(Direction.LEFT, mineralCompensationDistance, 0.7);

        drive.rotateDegrees(Direction.RIGHT, 50, 0.5);

        drive.moveInches(Direction.LEFT, 6, 0.1);

        drive.moveInches(Direction.BACKWARD, 35, 0.7);
        //drive.moveInches(Direction.FORWARD, 98, 1);


    }

    public void initialize() {
        drive = new MecanumEncoderDrive(hardwareMap, this, CustomizedRobotParameters.ROBOT_PARAMETERS);
    }
}
