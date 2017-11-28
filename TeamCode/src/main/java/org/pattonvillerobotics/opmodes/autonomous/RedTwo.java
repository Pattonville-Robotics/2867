package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.drive.MecanumEncoderDrive;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.JewelColorDetector;
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
        JewelColorDetector.Analysis analysis;
        vuforia.activateTracking();

        RelicRecoveryVuMark columnKey;

        telemetry.addData("Red Two", "Initialization Complete");
        telemetry.update();

        waitForStart();

        claw.close();
        slides.setPower(-.3);
        sleep(1000);
        slides.setPower(0);

        columnKey = vuforia.getCurrentVisibleRelic();

        arm.extendArm();

        arm.retractArm();

        drive.moveInches(Direction.FORWARD, 9, .2);

        drive.rotateDegrees(Direction.COUNTERCLOCKWISE, 90, 0.2);

        switch (columnKey) {
            case CENTER:
                drive.moveInches(Direction.RIGHT, 4, 1);
                break;
            case RIGHT:
                drive.moveInches(Direction.RIGHT, 10.5, 1);
                break;
            default:
                break;
        }

        drive.moveInches(Direction.FORWARD, 3, .2);
        claw.open();
        drive.moveInches(Direction.BACKWARD, 5, .2);
        drive.rotateDegrees(Direction.RIGHT, 135, .25);
    }

    public void initialize() {
        drive = new MecanumEncoderDrive(hardwareMap, this, CustomizedRobotParameters.ROBOT_PARAMETERS);
        arm = new ServoArm(hardwareMap, this);
        claw = new BenClaw(hardwareMap, this);
        vuforia = new VuforiaNavigation(CustomizedRobotParameters.VUFORIA_PARAMETERS);
    }
}
