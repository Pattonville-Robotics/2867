package org.pattonvillerobotics.robotclasses;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.pattonvillerobotics.commoncode.enums.AllianceColor;
import org.pattonvillerobotics.commoncode.enums.ColorSensorColor;
import org.pattonvillerobotics.commoncode.robotclasses.drive.MecanumEncoderDrive;
import org.pattonvillerobotics.commoncode.robotclasses.vuforia.VuforiaNavigation;
import org.pattonvillerobotics.opmodes.CustomizedRobotParameters;
import org.pattonvillerobotics.robotclasses.enums.StartingPosition;
import org.pattonvillerobotics.robotclasses.mechanisms.BenClaw;
import org.pattonvillerobotics.robotclasses.mechanisms.ServoArm;

/**
 * Created by bahrg on 10/24/17.
 */

public class CommonAutonomous {

    private AllianceColor allianceColor;
    private StartingPosition startingPosition;
    private HardwareMap hardwareMap;
    private LinearOpMode linearOpMode;
    private VuforiaNavigation vuforia;
    private RelicRecoveryVuMark vuMark;

    private BenClaw claw;
    private ServoArm arm;
    private MecanumEncoderDrive drive;
    private DcMotor slideMotor;

    public CommonAutonomous(StartingPosition startingPosition, AllianceColor allianceColor, HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        this.allianceColor = allianceColor;
        this.hardwareMap = hardwareMap;
        this.linearOpMode = linearOpMode;
        this.startingPosition = startingPosition;

        vuforia = new VuforiaNavigation(CustomizedRobotParameters.VUFORIA_PARAMETERS);
        drive = new MecanumEncoderDrive(hardwareMap, linearOpMode, CustomizedRobotParameters.ROBOT_PARAMETERS);

        claw = new BenClaw(hardwareMap, linearOpMode);
        arm = new ServoArm(hardwareMap, linearOpMode);
        slideMotor = hardwareMap.dcMotor.get("slide_motor");
    }

    public void detectAndStoreVuMark() {
        while((vuMark == null || vuMark == RelicRecoveryVuMark.UNKNOWN) && linearOpMode.opModeIsActive()) {
            vuMark = vuforia.getCurrentVisibleRelic();
        }
    }

    public ColorSensorColor extendArmAndDetectColor() {
        ColorSensorColor color = ColorSensorColor.GREEN;

        arm.extendArm();

        while(color == ColorSensorColor.GREEN && linearOpMode.opModeIsActive()) {
            color = arm.senseBallColor();
        }
        return color;
    }

    public void knockOffJewel(ColorSensorColor detectedColor) {
        //TODO: Do something once given color.

        switch (allianceColor) {
            case RED:
                break;
            case BLUE:
                break;
            default:
                Log.wtf("CommonAutonomous", "This should never happen.");
        }
    }
}
