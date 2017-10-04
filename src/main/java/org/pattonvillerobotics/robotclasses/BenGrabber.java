package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.apache.commons.math3.util.FastMath;

import static org.pattonvillerobotics.commoncode.robotclasses.drive.EncoderDrive.TARGET_REACHED_THRESHOLD;

public class BenGrabber {

    //Fields
    private DcMotor motor;
    private LinearOpMode opMode;

    //Constructor
    public BenGrabber(HardwareMap hardwareMap, LinearOpMode opMode) {
        motor = hardwareMap.dcMotor.get("motor");
        this.opMode = opMode;
    }

    //Methods

    public void open() {

        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setTargetPosition(0);
        motor.setPower(0.2);

        while ((!reachedTarget(motor.getCurrentPosition(), motor.getTargetPosition()) || motor.isBusy()) && !opMode.isStopRequested() && opMode.opModeIsActive()) {
            opMode.idle();
        }

        motor.setPower(0);
    }

    public void close() {

        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setTargetPosition(120);
        motor.setPower(0.2);

        while (!reachedTarget(motor.getCurrentPosition(), motor.getTargetPosition()) && !opMode.isStopRequested() && opMode.opModeIsActive()) {
            opMode.idle();
        }

        motor.setPower(0);
    }

    protected boolean reachedTarget(int currentPositionLeft, int targetPositionLeft) {
        return FastMath.abs(currentPositionLeft - targetPositionLeft) < TARGET_REACHED_THRESHOLD;
    }
}
