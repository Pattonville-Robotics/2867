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

    private TapeMeasureLifter lifter;
    private IntakeMechanism intakeMechanism;

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        int mineralCompensationDistance;

        drive.moveInches(Direction.FORWARD, 7, 0.7);

        drive.moveInches(Direction.LEFT, 6, 0.7);

        // take picture, analyze

        mineralDetector.process(vuforia.getImage());

        if(mineralDetector.getAnalysis() == ColorSensorColor.YELLOW) {
            //for determining what distance will get the robot to a specific point, depending on which mineral is "chosen"
            mineralCompensationDistance = 43;
            drive.moveInches(Direction.RIGHT, 4, 0.7);
        } else {
            drive.moveInches(Direction.RIGHT, 16, 0.7);
            //take picture, analyze

            mineralDetector.process(vuforia.getImage());

            if(mineralDetector.getAnalysis() == ColorSensorColor.YELLOW) {
                drive.moveInches(Direction.RIGHT, 5, 0.7);
                mineralCompensationDistance = 62;

            } else {
                //program failed. Zero stars.
                drive.moveInches(Direction.LEFT, 27, 0.7);
                mineralCompensationDistance = 25;
            }
        }

        drive.moveInches(Direction.FORWARD, 12, 0.7);
        drive.moveInches(Direction.BACKWARD, 10, 0.7);

        drive.moveInches(Direction.LEFT, mineralCompensationDistance, 0.8);

        drive.rotateDegrees(Direction.CLOCKWISE, 50, 0.7);

        drive.moveInches(Direction.LEFT, 10, 0.6);

        drive.moveInches(Direction.RIGHT, 2, 0.5);

        drive.moveInches(Direction.BACKWARD, 35, 1);

        sleep(500);

        intakeMechanism.raise();

        sleep(600);

        intakeMechanism.expulsion();

        sleep(1000);

        intakeMechanism.turnOffIntake();

        intakeMechanism.raise();

        drive.rotateDegrees(Direction.COUNTERCLOCKWISE, 180, 0.7);

        drive.moveInches(Direction.BACKWARD, 40, 1);

        intakeMechanism.drop();
    }

    public void initialize() {
        ImageProcessor.initOpenCV(hardwareMap, this);
        mineralDetector = new MineralDetector(CustomizedRobotParameters.PHONE_ORIENTATION, true);
        vuforia = new VuforiaNavigation(CustomizedRobotParameters.VUFORIA_PARAMETERS);

        drive = new MecanumEncoderDrive(hardwareMap, this, CustomizedRobotParameters.ROBOT_PARAMETERS);

        lifter = new TapeMeasureLifter(hardwareMap, this);

        intakeMechanism = new IntakeMechanism(hardwareMap, this);

        intakeMechanism.drop();
    }
}
