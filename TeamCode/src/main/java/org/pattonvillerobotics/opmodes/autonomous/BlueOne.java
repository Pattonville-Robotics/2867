package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.drive.MecanumEncoderDrive;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.ImageProcessor;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.relicrecovery.jewels.JewelColorDetector;
import org.pattonvillerobotics.commoncode.robotclasses.vuforia.VuforiaNavigation;
import org.pattonvillerobotics.opmodes.CustomizedRobotParameters;
import org.pattonvillerobotics.robotclasses.mechanisms.BenClaw;
import org.pattonvillerobotics.robotclasses.mechanisms.ServoArm;


@Autonomous(name = "Blue 1", group = OpModeGroups.MAIN)
public class BlueOne extends LinearOpMode {

    private MecanumEncoderDrive drive;
    private ServoArm arm;
    private BenClaw bottomClaw, topClaw;
    private JewelColorDetector jewelColorDetector;
    private VuforiaNavigation vuforia;
    private DcMotor slides;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        JewelColorDetector.AnalysisResult analysis;
        vuforia.activateTracking();

        RelicRecoveryVuMark columnKey;

        telemetry.addData("Blue One", "Initialization Complete");
        telemetry.update();

        waitForStart();

        bottomClaw.close();
        slides.setPower(-.3);
        sleep(1000);
        slides.setPower(0);

        drive.rotateDegrees(Direction.RIGHT, 20, .4);

        columnKey = vuforia.getCurrentVisibleRelic();

        while(columnKey == RelicRecoveryVuMark.UNKNOWN) {
            columnKey = vuforia.getCurrentVisibleRelic();
        }

        telemetry.addData("Column Key: ", columnKey).setRetained(true);
        telemetry.update();

        drive.rotateDegrees(Direction.LEFT, 20, .4);

        arm.extendArm();

        jewelColorDetector.process(vuforia.getImage());
        analysis = jewelColorDetector.getAnalysis();

        while ((analysis.leftJewelColor == null || analysis.rightJewelColor == null) && opModeIsActive()) {
            jewelColorDetector.process(vuforia.getImage());
            analysis = jewelColorDetector.getAnalysis();
        }

        telemetry.addData("LEFT", analysis.leftJewelColor).setRetained(true);
        telemetry.addData("RIGHT", analysis.rightJewelColor).setRetained(true);
        telemetry.update();


        if (analysis.leftJewelColor != null) {
            switch (analysis.leftJewelColor) {
                case RED:
                    drive.moveInches(Direction.BACKWARD, 6, 0.5);
                    arm.retractArm();
                    sleep(1000);
                    drive.moveInches(Direction.FORWARD, 6, 0.5);
                    break;
                case BLUE:
                    drive.moveInches(Direction.FORWARD, 6, 0.5);
                    arm.retractArm();
                    sleep(1000);
                    drive.moveInches(Direction.BACKWARD, 6, 0.5);
                    break;
                default:
            }
        } else if (analysis.rightJewelColor != null) {
            switch (analysis.rightJewelColor) {
                case RED:
                    drive.moveInches(Direction.FORWARD, 6, 0.5);
                    arm.retractArm();
                    sleep(1000);
                    drive.moveInches(Direction.BACKWARD, 6, 0.5);
                    break;
                case BLUE:
                    drive.moveInches(Direction.BACKWARD, 6, 0.5);
                    arm.retractArm();
                    sleep(1000);
                    drive.moveInches(Direction.FORWARD, 6, 0.5);
                    break;
                default:
            }
        }

        drive.moveInches(Direction.FORWARD, 30, 0.5);

        drive.rotateDegrees(Direction.LEFT, 90, 0.5);

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

        drive.moveInches(Direction.BACKWARD, 15, .5);
        bottomClaw.open();
        drive.moveInches(Direction.FORWARD, 14, .5);
        bottomClaw.close();
        drive.moveInches(Direction.BACKWARD, 14, .5);
        bottomClaw.open();
        drive.moveInches(Direction.FORWARD, 10, .5);
        drive.rotateDegrees(Direction.RIGHT, 180, .8);

        // fancy !!

//        drive.moveInches(Direction.FORWARD, 40, .8);
//        bottomClaw.close();
//        slides.setPower(-.5);
//        sleep(500);
//        slides.setPower(0);
//        drive.moveInches(Direction.BACKWARD, 40, .8);
//        drive.rotateDegrees(Direction.RIGHT, 180, .8);
//        drive.moveInches(Direction.FORWARD, 20, .8);
//        bottomClaw.open();
//        drive.moveInches(Direction.BACKWARD, 20, .8);
    }

    public void initialize() {
        ImageProcessor.initOpenCV(hardwareMap, this);
        slides = hardwareMap.dcMotor.get("slides");
        drive = new MecanumEncoderDrive(hardwareMap, this, CustomizedRobotParameters.ROBOT_PARAMETERS);
        arm = new ServoArm(hardwareMap, this);
        bottomClaw = new BenClaw(hardwareMap, this, "bottom_claw");
        topClaw = new BenClaw(hardwareMap, this, "top_claw");

        jewelColorDetector = new JewelColorDetector(CustomizedRobotParameters.PHONE_ORIENTATION, true);
        vuforia = new VuforiaNavigation(CustomizedRobotParameters.VUFORIA_PARAMETERS);
    }
}
