package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.robotclasses.drive.MecanumEncoderDrive;
import org.pattonvillerobotics.commoncode.robotclasses.drive.RobotParameters;

@Autonomous(name="MainAutonomous", group = "Competition")
public class MainAutonomous extends LinearOpMode {

    private RobotParameters parameters = new RobotParameters.Builder()
            .encodersEnabled(true)
            .gyroEnabled(true)
            .wheelBaseRadius(8.5)
            .wheelRadius(2)
            .driveGearRatio(1.5)
            .leftDriveMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightDriveMotorDirection(DcMotorSimple.Direction.FORWARD)
            .build();

    private MecanumEncoderDrive drive;

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        // lower the robot

        drive.moveInches(Direction.FORWARD, 7, 0.5);
        // take picture, analyze
        boolean goldResult = true;
        int mineralCompensationDistance;
        if(goldResult) {
            //for determining what distance will get the robot to a specific point, depending on which mineral is "chosen"
            mineralCompensationDistance = 43;
        } else {
            drive.moveInches(Direction.RIGHT, 18, 0.5);
            //take picture, analyze
            goldResult = true;
            if(goldResult) {
                mineralCompensationDistance = 61;

            } else {
                //program failed. Zero stars.
                drive.moveInches(Direction.LEFT, 36, 0.5);
                mineralCompensationDistance = 25;
            }
        }

        drive.moveInches(Direction.FORWARD, 5, 0.5);
        drive.moveInches(Direction.BACKWARD, 5, 0.5);
        drive.moveInches(Direction.LEFT, mineralCompensationDistance, 0.6);
        drive.rotateDegrees(Direction.RIGHT, 45, 0.5);
        drive.moveInches(Direction.BACKWARD, 58, 0.5);
        drive.moveInches(Direction.FORWARD, 98, 1);


    }

    public void initialize() {
        drive = new MecanumEncoderDrive(hardwareMap, this, parameters);
    }
}
