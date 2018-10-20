package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.pattonvillerobotics.commoncode.robotclasses.drive.RobotParameters;
import org.pattonvillerobotics.robotclasses.TapeMeasureLifter;

@Autonomous(name = "WinchAutonomous")
public class WinchAutonomous extends LinearOpMode {

    public TapeMeasureLifter lifter;

    private RobotParameters parameters = new RobotParameters.Builder()
            .encodersEnabled(true)
            .gyroEnabled(true)
            .wheelBaseRadius(7.25)
            .wheelRadius(2)
            .driveGearRatio(3)
            .leftDriveMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightDriveMotorDirection(DcMotorSimple.Direction.FORWARD)
            .build();

    @Override
    public void runOpMode() {

        initialize();

        waitForStart();

        telemetry.addData("Lifter Position", lifter.winchMotor.getCurrentPosition());

        lifter.lower();

    }

    public void initialize() {
        lifter = new TapeMeasureLifter(hardwareMap, this);
    }
}
