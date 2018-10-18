package org.pattonvillerobotics.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.pattonvillerobotics.robotclasses.TapeMeasureLifter;

@Autonomous(name = "MechanismExampleTest", group = "test")
public class MechanismExampleTest extends LinearOpMode {

    public TapeMeasureLifter lifter;

    @Override
    public void runOpMode() {
        initialize();

        waitForStart();

        telemetry.addData("lifter position", lifter.winchMotor.getCurrentPosition());

        lifter.lower();
    }

    public void initialize() {
        lifter = new TapeMeasureLifter(hardwareMap, this);
    }
}
