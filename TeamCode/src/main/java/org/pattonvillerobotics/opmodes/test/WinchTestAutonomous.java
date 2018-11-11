package org.pattonvillerobotics.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.drive.RobotParameters;
import org.pattonvillerobotics.robotclasses.TapeMeasureLifter;

@Disabled
@Autonomous(name = "WinchTestAutonomous", group = OpModeGroups.TESTING)
public class WinchTestAutonomous extends LinearOpMode {

    public TapeMeasureLifter lifter;

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
