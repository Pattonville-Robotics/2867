package org.pattonvillerobotics.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.drive.MecanumEncoderDrive;
import org.pattonvillerobotics.commoncode.robotclasses.drive.RobotParameters;
import org.pattonvillerobotics.robotclasses.CustomizedRobotParameters;

@Disabled
@Autonomous(name = "MecanumDriveTest", group = OpModeGroups.TESTING)
public class MecanumDriveTest extends LinearOpMode {

    public MecanumEncoderDrive drive;

    @Override
    public void runOpMode() {

        initialize();

        waitForStart();

        drive.moveInches(Direction.FORWARD, 12, 0.5);
        drive.moveInches(Direction.LEFT, 12, 0.5);
        drive.moveInches(Direction.RIGHT, 12, 0.5);
        drive.moveInches(Direction.BACKWARD, 12, 0.5);

        drive.rotateDegrees(Direction.LEFT, 90, 0.5);

        //Here is where you run methods for the robot to do things

    }

    public void initialize() {
        drive = new MecanumEncoderDrive(hardwareMap, this, CustomizedRobotParameters.ROBOT_PARAMETERS);
    }
}
