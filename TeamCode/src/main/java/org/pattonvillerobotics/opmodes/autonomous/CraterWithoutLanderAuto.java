package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.pattonvillerobotics.commoncode.enums.ColorSensorColor;
import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.drive.MecanumEncoderDrive;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.ImageProcessor;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.roverruckus.minerals.MineralDetector;
import org.pattonvillerobotics.commoncode.robotclasses.vuforia.VuforiaNavigation;
import org.pattonvillerobotics.robotclasses.CustomizedRobotParameters;
import org.pattonvillerobotics.robotclasses.IntakeMechanism;
import org.pattonvillerobotics.robotclasses.TapeMeasureLifter;

@Autonomous(name="Crater Without Lander Autonomous", group = OpModeGroups.MAIN)
public class CraterWithoutLanderAuto extends LinearOpMode {

    private MecanumEncoderDrive drive;

    private MineralDetector mineralDetector;
    private VuforiaNavigation vuforia;

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        drive.moveInches(Direction.RIGHT, 4, 0.8);

        drive.moveInches(Direction.BACKWARD, 2, 0.8);

        // take picture, analyze

        for (int i = 0; i < 7; i++) {
            mineralDetector.process(vuforia.getImage());
        }

        drive.rotateDegrees(Direction.CLOCKWISE, 6, 0.5);

        drive.moveInches(Direction.FORWARD, 12, 0.7);

        switch (mineralDetector.getHorizontalAnalysis()) {
            case RIGHT:
                drive.moveInches(Direction.RIGHT, 8, 0.7);
                break;
            case MIDDLE:
                drive.moveInches(Direction.LEFT, 7, 0.7);
                break;
            case LEFT:
            default:
                drive.moveInches(Direction.LEFT, 28, 0.7);
        }

        drive.moveInches(Direction.FORWARD, 25, 1);
    }

    public void initialize() {
        ImageProcessor.initOpenCV(hardwareMap, this);
        mineralDetector = new MineralDetector(CustomizedRobotParameters.PHONE_ORIENTATION, true);
        vuforia = new VuforiaNavigation(CustomizedRobotParameters.VUFORIA_PARAMETERS);

        drive = new MecanumEncoderDrive(hardwareMap, this, CustomizedRobotParameters.ROBOT_PARAMETERS);
    }
}
