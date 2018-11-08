package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.pattonvillerobotics.commoncode.enums.ColorSensorColor;
import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.drive.MecanumEncoderDrive;
import org.pattonvillerobotics.commoncode.robotclasses.drive.RobotParameters;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.ImageProcessor;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.roverruckus.minerals.MineralDetector;
import org.pattonvillerobotics.commoncode.robotclasses.vuforia.VuforiaNavigation;
import org.pattonvillerobotics.robotclasses.CustomizedRobotParameters;

@Autonomous(name="Blue Crater Autonomous", group = OpModeGroups.MAIN)
public class BlueCraterAutonomous extends LinearOpMode {

    private MecanumEncoderDrive drive;
    private MineralDetector mineralDetector;
    private VuforiaNavigation vuforia;

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        boolean goldResult = true;
        int mineralCompensationDistance;

        // lower the robot

        drive.moveInches(Direction.FORWARD, 7, 0.5);
        // take picture, analyze

        Mat rgbaMat = ImageProcessor.processBitmap(vuforia.getImage(), CustomizedRobotParameters.PHONE_ORIENTATION);

        Rect rectCrop = new Rect(0, (rgbaMat.height()*4)/5, rgbaMat.width(), (rgbaMat.height()/6));

        rgbaMat = new Mat(rgbaMat, rectCrop);

        mineralDetector.process(rgbaMat);

        if(mineralDetector.getAnalysis() == ColorSensorColor.YELLOW) {
            //for determining what distance will get the robot to a specific point, depending on which mineral is "chosen"
            mineralCompensationDistance = 43;
        } else {
            drive.moveInches(Direction.RIGHT, 14, 0.5);
            //take picture, analyze

            rgbaMat = ImageProcessor.processBitmap(vuforia.getImage(), CustomizedRobotParameters.PHONE_ORIENTATION);

            rgbaMat = new Mat(rgbaMat, rectCrop);

            mineralDetector.process(rgbaMat);
            if(mineralDetector.getAnalysis() == ColorSensorColor.YELLOW) {
                mineralCompensationDistance = 62;

            } else {
                //program failed. Zero stars.
                drive.moveInches(Direction.LEFT, 29, 0.5);
                mineralCompensationDistance = 27;
            }
        }

        drive.moveInches(Direction.FORWARD, 12, 0.5);
        drive.moveInches(Direction.BACKWARD, 12, 0.5);

        drive.moveInches(Direction.LEFT, mineralCompensationDistance, 0.7);

        drive.rotateDegrees(Direction.CLOCKWISE, 50, 0.5);

        drive.moveInches(Direction.LEFT, 6, 0.1);

        drive.moveInches(Direction.BACKWARD, 35, 0.7);


    }

    public void initialize() {
        ImageProcessor.initOpenCV(hardwareMap, this);
        mineralDetector = new MineralDetector(CustomizedRobotParameters.PHONE_ORIENTATION, true);
        vuforia = new VuforiaNavigation(CustomizedRobotParameters.VUFORIA_PARAMETERS);

        drive = new MecanumEncoderDrive(hardwareMap, this, CustomizedRobotParameters.ROBOT_PARAMETERS);
    }
}
