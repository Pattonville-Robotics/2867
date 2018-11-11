package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.pattonvillerobotics.commoncode.enums.ColorSensorColor;
import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.robotclasses.drive.MecanumEncoderDrive;
import org.pattonvillerobotics.commoncode.robotclasses.drive.RobotParameters;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.ImageProcessor;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.roverruckus.minerals.MineralDetector;
import org.pattonvillerobotics.commoncode.robotclasses.vuforia.VuforiaNavigation;
import org.pattonvillerobotics.robotclasses.CustomizedRobotParameters;

@Autonomous(name="Depot Autonomous", group = "Competition")
public class DepotAutonomous extends LinearOpMode {

    private MecanumEncoderDrive drive;
    private MineralDetector mineralDetector;
    private VuforiaNavigation vuforia;

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        double mineralCompensationDistance;

        // lower the robot

        drive.moveInches(Direction.FORWARD, 8, 0.5);
        drive.moveInches(Direction.LEFT, 6, 0.5);

        // take picture, analyze

        mineralDetector.process(vuforia.getImage());

        if(mineralDetector.getAnalysis() == ColorSensorColor.YELLOW) {
            //for determining what distance will get the robot to a specific point, depending on which mineral is "chosen"
            mineralCompensationDistance = 15;
            //drive.moveInches(Direction.RIGHT, 4, 0.5);
        } else {
            drive.moveInches(Direction.RIGHT, 14, 0.5);
            //take picture, analyze

            mineralDetector.process(vuforia.getImage());

            if(mineralDetector.getAnalysis() == ColorSensorColor.YELLOW) {
                //drive.moveInches(Direction.RIGHT, 5, 0.5);
                mineralCompensationDistance = 0;

            } else {
                //program failed. Zero stars.
                drive.moveInches(Direction.LEFT, 27, 0.5);
                mineralCompensationDistance = 30;
            }
        }
        drive.moveInches(Direction.RIGHT, 7, 0.5);
        drive.moveInches(Direction.FORWARD, 29, 1);
        drive.moveInches(Direction.RIGHT, mineralCompensationDistance, 1);
        drive.rotateDegrees(Direction.COUNTERCLOCKWISE, 50, 1);
        drive.moveInches(Direction.RIGHT, 5, 0.3);
        drive.moveInches(Direction.BACKWARD,50, 1);
        drive.moveInches(Direction.FORWARD, 65, 1);

    }

    public void initialize() {
        ImageProcessor.initOpenCV(hardwareMap, this);
        mineralDetector = new MineralDetector(CustomizedRobotParameters.PHONE_ORIENTATION, true);
        vuforia = new VuforiaNavigation(CustomizedRobotParameters.VUFORIA_PARAMETERS);

        drive = new MecanumEncoderDrive(hardwareMap, this, CustomizedRobotParameters.ROBOT_PARAMETERS);
    }
}
