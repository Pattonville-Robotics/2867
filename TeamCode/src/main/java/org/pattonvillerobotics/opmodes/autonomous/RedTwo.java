package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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

/**
 * Created by wingertj01 on 11/21/17.
 */

@Autonomous (name = "Red 2", group = OpModeGroups.MAIN)
public class RedTwo extends LinearOpMode {

    private MecanumEncoderDrive drive;
    private ServoArm arm;
    private BenClaw claw;
    private JewelColorDetector jewelColorDetector;
    private VuforiaNavigation vuforia;
    private DcMotor slides;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        JewelColorDetector.AnalysisResult analysis;
        vuforia.activateTracking();

        RelicRecoveryVuMark columnKey;

        telemetry.addData("Red Two", "Initialization Complete");
        telemetry.update();

        waitForStart();

        claw.close();
        slides.setPower(-.3);
        sleep(1000);
        slides.setPower(0);

        drive.rotateDegrees(Direction.LEFT, 20, .4);

        columnKey = vuforia.getCurrentVisibleRelic();

        while (columnKey == RelicRecoveryVuMark.UNKNOWN) {
            columnKey = vuforia.getCurrentVisibleRelic();
        }

        telemetry.addData("Column Key: ", columnKey).setRetained(true);
        telemetry.update();

        drive.rotateDegrees(Direction.RIGHT, 20, .4);

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

        drive.moveInches(Direction.FORWARD, 30, .7);

        switch (columnKey) {
            case RIGHT:
                drive.moveInches(Direction.RIGHT, 11.5, 1);
                break;
            case CENTER:
                drive.moveInches(Direction.RIGHT, 19, 1);
                break;
            case LEFT:
                drive.moveInches(Direction.RIGHT, 30, 1);
                break;
            default:
                break;
        }

        drive.moveInches(Direction.FORWARD, 15, .7);
        claw.open();
        drive.moveInches(Direction.BACKWARD, 12, .7);
        claw.close();
        drive.moveInches(Direction.FORWARD, 15, .7);
        claw.open();
        drive.moveInches(Direction.BACKWARD, 11, .7);
        switch (columnKey) {
            case RIGHT:
                drive.moveInches(Direction.RIGHT, 18, 1);
                break;
            case CENTER:
                drive.moveInches(Direction.RIGHT, 10, 1);
                break;
            default:
                break;
        }
        drive.rotateDegrees(Direction.LEFT, 100, .7);
    }

    public void initialize() {
        ImageProcessor.initOpenCV(hardwareMap, this);
        slides = hardwareMap.dcMotor.get("slides");
        drive = new MecanumEncoderDrive(hardwareMap, this, CustomizedRobotParameters.ROBOT_PARAMETERS);
        arm = new ServoArm(hardwareMap, this);
        claw = new BenClaw(hardwareMap, this);
        jewelColorDetector = new JewelColorDetector(CustomizedRobotParameters.PHONE_ORIENTATION, true);
        vuforia = new VuforiaNavigation(CustomizedRobotParameters.VUFORIA_PARAMETERS);

        drive.leftRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        drive.rightRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        drive.leftDriveMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        drive.rightDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
