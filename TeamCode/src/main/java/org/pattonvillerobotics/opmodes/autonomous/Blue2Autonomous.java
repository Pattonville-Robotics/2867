package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.robotclasses.drive.MecanumEncoderDrive;
import org.pattonvillerobotics.commoncode.robotclasses.drive.RobotParameters;

@Autonomous(name="Blue2Autonomous", group = "Competition")
public class Blue2Autonomous extends LinearOpMode {

    private RobotParameters parameters = new RobotParameters.Builder()
            .encodersEnabled(true)
            .gyroEnabled(true)
            .wheelBaseRadius(8.5)
            .wheelRadius(2)
            .driveGearRatio(1.5)
            .leftDriveMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightDriveMotorDirection(DcMotorSimple.Direction.REVERSE)
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
        int result;
        if(goldResult) {
            //for determining what distance will get the robot to a specific point, depending on which mineral is "chosen"
            result = 0;
        } else {
            drive.moveInches(Direction.RIGHT, 18, 0.5);
            //take picture, analyze
            goldResult = true;
            if(goldResult) {
                result = 1;

            } else {
                //program failed. Zero stars.
                drive.moveInches(Direction.LEFT, 36, 0.5);
                result = 2;
            }
        }

        drive.moveInches(Direction.FORWARD, 10, 0.5);
        if(result == 1) {
            drive.moveInches(Direction.LEFT, 18, 0.5);
        } else {
            if(result == 2) {
                drive.moveInches(Direction.RIGHT, 18, 0.5);
            }
        }
        //drop flag
        drive.moveInches(Direction.RIGHT, 18, 0.5);
        drive.rotateDegrees(Direction.LEFT, 45, 0.5);
        drive.moveInches(Direction.BACKWARD, 93, 1);


    }

    public void initialize() {
        drive = new MecanumEncoderDrive(hardwareMap, this, parameters);
    }
}
