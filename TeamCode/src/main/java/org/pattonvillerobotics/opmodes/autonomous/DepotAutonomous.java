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
import org.pattonvillerobotics.robotclasses.TapeMeasureLifter;

@Autonomous(name="Depot Autonomous", group = "Competition")
public class DepotAutonomous extends LinearOpMode {

    private MecanumEncoderDrive drive;
    private MineralDetector mineralDetector;
    private VuforiaNavigation vuforia;

    private TapeMeasureLifter lifter;

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        int mineralCompensationDistance;

        lifter.winchMotor.setPower(0.5);

        sleep(2300);

        lifter.winchMotor.setPower(0.2);

        drive.moveInches(Direction.FORWARD,1, 0.5);

        sleep(100);

        drive.moveInches(Direction.BACKWARD,0.7, 0.4);

        drive.moveInches(Direction.LEFT, 4, 0.3);

        lifter.winchMotor.setPower(-0.3);

        drive.moveInches(Direction.FORWARD, 3, 0.8);

        drive.moveInches(Direction.RIGHT, 8, 0.8);

        drive.moveInches(Direction.BACKWARD, 4, 0.8);

        // take picture, analyze

        lifter.winchMotor.setPower(0);
        // take picture, analyze

        for (int i = 0; i < 7; i++) {
            mineralDetector.process(vuforia.getImage());
        }

        switch (mineralDetector.getHorizontalAnalysis()) {
            case RIGHT:
                drive.moveInches(Direction.RIGHT, 8, 0.7);
                mineralCompensationDistance = 15;
                break;
            case MIDDLE:
                drive.moveInches(Direction.LEFT, 7, 0.7);
                mineralCompensationDistance = 0;
                break;
            case LEFT:
            default:
                drive.moveInches(Direction.LEFT, 28, 0.7);
                mineralCompensationDistance = 30;
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

        lifter = new TapeMeasureLifter(hardwareMap, this);
    }
}
