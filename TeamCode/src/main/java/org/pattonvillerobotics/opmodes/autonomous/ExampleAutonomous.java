package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.robotclasses.drive.MecanumEncoderDrive;
import org.pattonvillerobotics.commoncode.robotclasses.drive.RobotParameters;
import org.pattonvillerobotics.robotclasses.CustomizedRobotParameters;

@Autonomous(name = "ExampleAutonomous")
public class ExampleAutonomous extends LinearOpMode {

    public MecanumEncoderDrive drive;

    @Override
    public void runOpMode() {

        initialize();

        waitForStart();

        drive.moveInches(Direction.FORWARD, 12, 0.5);
        drive.moveInches(Direction.BACKWARD, 12, 0.5);
        drive.moveInches(Direction.LEFT, 12, 0.5);
        drive.moveInches(Direction.RIGHT, 12, 0.5);

        drive.rotateDegrees(Direction.LEFT, 90, 0.5);
        drive.rotateDegrees(Direction.RIGHT, 90, 0.5);

        //Here is where you run methods for the robot to do things

    }

    public void initialize() {
        drive = new MecanumEncoderDrive(hardwareMap, this, CustomizedRobotParameters.ROBOT_PARAMETERS);
        drive.leftDriveMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        drive.leftRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        drive.rightDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        drive.rightRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
