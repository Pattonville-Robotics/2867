package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.drive.MecanumEncoderDrive;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.JewelColorDetector;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.util.PhoneOrientation;
import org.pattonvillerobotics.commoncode.robotclasses.vuforia.VuforiaNavigation;
import org.pattonvillerobotics.opmodes.CustomizedRobotParameters;
import org.pattonvillerobotics.robotclasses.mechanisms.BenClaw;
import org.pattonvillerobotics.robotclasses.mechanisms.ServoArm;


@Autonomous(name = "Blue 1", group = OpModeGroups.MAIN)
public class BlueOne extends LinearOpMode {

    private MecanumEncoderDrive drive;
    private ServoArm arm;
    private BenClaw claw;
    private JewelColorDetector jewelColorDetector;
    private VuforiaNavigation vuforia;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        JewelColorDetector.Analysis analysis;
        vuforia.activateTracking();

        RelicRecoveryVuMark columnKey;

        waitForStart();

        claw.close();
        columnKey = vuforia.getCurrentVisibleRelic();

        while(columnKey == RelicRecoveryVuMark.UNKNOWN) {
            columnKey = vuforia.getCurrentVisibleRelic();
        }

        sleep(1000);

        telemetry.addData("Column Key: ", columnKey).setRetained(true);
        telemetry.update();

        jewelColorDetector.process(vuforia.getImage());
        analysis = jewelColorDetector.getAnalysis();

        while(analysis.leftJewelColor == null && analysis.rightJewelColor == null) {
            jewelColorDetector.process(vuforia.getImage());
            analysis = jewelColorDetector.getAnalysis();
        }

        arm.extendArm();

        switch (analysis.leftJewelColor) {
            case RED:
                drive.moveInches(Direction.BACKWARD,2,0.5);
                drive.moveInches(Direction.FORWARD,2,0.5);
                break;
            case BLUE:
                drive.moveInches(Direction.FORWARD,2,0.5);
                drive.moveInches(Direction.BACKWARD,2,0.5);
                break;
            default:
                switch (analysis.rightJewelColor) {
                    case RED:
                        drive.moveInches(Direction.FORWARD,2,0.5);
                        drive.moveInches(Direction.BACKWARD,2,0.5);
                        break;
                    case BLUE:
                        drive.moveInches(Direction.BACKWARD,2,0.5);
                        drive.moveInches(Direction.FORWARD,2,0.5);
                        break;
                    default:
                        break;
                }
        }

        arm.retractArm();

        drive.moveInches(Direction.FORWARD, 30, 0.5);

        sleep(3000);

        drive.rotateDegrees(Direction.LEFT, 90, 0.5);

        sleep(3000);

        switch (columnKey) {
            case CENTER:
                drive.moveInches(Direction.RIGHT, 12, 1);
                break;
            case RIGHT:
                drive.moveInches(Direction.RIGHT, 23, 1);
                break;
            default:
                break;
        }

        sleep(3000);

        drive.moveInches(Direction.BACKWARD, 15, .5);
        sleep(3000);
        claw.open();
        sleep(3000);
        drive.moveInches(Direction.FORWARD, 17, .5);
        sleep(3000);
        drive.rotateDegrees(Direction.RIGHT, 180, .5);

        while(opModeIsActive()) {
            telemetry.update();
        }
    }

    public void initialize() {
        drive = new MecanumEncoderDrive(hardwareMap, this, CustomizedRobotParameters.ROBOT_PARAMETERS);
        arm = new ServoArm(hardwareMap, this);
        claw = new BenClaw(hardwareMap, this);
        jewelColorDetector = new JewelColorDetector(PhoneOrientation.PORTRAIT_INVERSE);
        vuforia = new VuforiaNavigation(CustomizedRobotParameters.VUFORIA_PARAMETERS);
    }
}
