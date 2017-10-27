package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.pattonvillerobotics.commoncode.enums.ColorSensorColor;
import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.drive.MecanumEncoderDrive;
import org.pattonvillerobotics.commoncode.robotclasses.vuforia.VuforiaNavigation;
import org.pattonvillerobotics.opmodes.CustomizedRobotParameters;
import org.pattonvillerobotics.robotclasses.BenClaw;
import org.pattonvillerobotics.robotclasses.ServoArm;


@Autonomous(name = "Blue 1", group = OpModeGroups.MAIN)
public class BlueOne extends LinearOpMode {

    private MecanumEncoderDrive drive;
    private ServoArm arm;
    private BenClaw claw;
    private VuforiaNavigation vuforia;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        vuforia.activateTracking();

        RelicRecoveryVuMark columnKey;
        ColorSensorColor jewelColor;

        waitForStart();

        columnKey = vuforia.getCurrentVisibleRelic();

        arm.extendArm();

        jewelColor = arm.senseBallColor();

        if (jewelColor == ColorSensorColor.BLUE) {
            drive.moveInches(Direction.FORWARD, 2, .2);
        } else if (jewelColor == ColorSensorColor.RED) {
            drive.moveInches(Direction.BACKWARD, 2, .2);
        }

        arm.retractArm();

        drive.moveInches(Direction.FORWARD, 20.5, 0.2);

        drive.rotateDegrees(Direction.COUNTERCLOCKWISE, 90, 0.2);

        switch (columnKey) {
            case CENTER:
                drive.moveInches(Direction.RIGHT, 6.5, 1);
                break;
            case RIGHT:
                drive.moveInches(Direction.RIGHT, 16.5, 1);
                break;
            default:
                break;
        }

        drive.moveInches(Direction.FORWARD, 3, .2);
        claw.open();
        drive.moveInches(Direction.BACKWARD, 5, .2);
        drive.rotateDegrees(Direction.RIGHT, 180, .2);
    }

    public void initialize() {
        drive = new MecanumEncoderDrive(hardwareMap, this, CustomizedRobotParameters.ROBOT_PARAMETERS);
        arm = new ServoArm(hardwareMap);
        claw = new BenClaw(hardwareMap);
        vuforia = new VuforiaNavigation(CustomizedRobotParameters.VUFORIA_PARAMETERS);
    }
}
