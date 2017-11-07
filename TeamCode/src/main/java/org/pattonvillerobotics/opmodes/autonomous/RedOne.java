package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.pattonvillerobotics.commoncode.enums.ColorSensorColor;
import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.drive.MecanumEncoderDrive;
import org.pattonvillerobotics.commoncode.robotclasses.vuforia.VuforiaNavigation;
import org.pattonvillerobotics.opmodes.CustomizedRobotParameters;
import org.pattonvillerobotics.robotclasses.mechanisms.BenClaw;

/**
 * Created by wingertj01 on 11/2/17.
 */
@Autonomous (name = "Red 1", group = OpModeGroups.MAIN)
public class RedOne extends LinearOpMode{

    private MecanumEncoderDrive drive;
    //private ServoArm arm;
    private BenClaw claw;
    private VuforiaNavigation vuforia;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        vuforia.activateTracking();

        RelicRecoveryVuMark columnKey;
        ColorSensorColor jewelColor;

        waitForStart();

        claw.close();
        columnKey = vuforia.getCurrentVisibleRelic();

        while(columnKey == RelicRecoveryVuMark.UNKNOWN) {
            columnKey = vuforia.getCurrentVisibleRelic();
        }

        sleep(1000);

        telemetry.addData("Column Key: ", columnKey).setRetained(true);
        telemetry.update();

        //arm.extendArm();

    /*    jewelColor = arm.senseBallColor();

        if (jewelColor == ColorSensorColor.BLUE) {
            drive.moveInches(Direction.FORWARD, 2, .2);
        } else if (jewelColor == ColorSensorColor.RED) {
            drive.moveInches(Direction.BACKWARD, 2, .2);
        }
*/
        //arm.retractArm();

        drive.moveInches(Direction.FORWARD, 30, 0.5);

        sleep(3000);

        drive.rotateDegrees(Direction.RIGHT, 90, 0.5);

        sleep(3000);

        switch (columnKey) {
            case CENTER:
                drive.moveInches(Direction.LEFT, 12, 1);
                break;
            case LEFT:
                drive.moveInches(Direction.LEFT, 23, 1);
                break;
            default:
                break;
        }

        sleep(3000);

        drive.moveInches(Direction.FORWARD, 15, .5);
        sleep(3000);
        claw.open();
        sleep(3000);
        drive.moveInches(Direction.BACKWARD, 17, .5);
        sleep(3000);
        drive.rotateDegrees(Direction.LEFT, 180, .5);

        while(opModeIsActive()) {
            telemetry.update();
        }
    }

    public void initialize() {
        drive = new MecanumEncoderDrive(hardwareMap, this, CustomizedRobotParameters.ROBOT_PARAMETERS);
        //arm = new ServoArm(hardwareMap, this);
        claw = new BenClaw(hardwareMap, this);
        vuforia = new VuforiaNavigation(CustomizedRobotParameters.VUFORIA_PARAMETERS);

        drive.leftRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        drive.rightRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        drive.leftDriveMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        drive.rightDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
