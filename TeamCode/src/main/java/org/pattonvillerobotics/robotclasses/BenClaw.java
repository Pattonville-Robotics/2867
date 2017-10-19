package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.apache.commons.math3.util.FastMath;
import org.pattonvillerobotics.commoncode.robotclasses.drive.EncoderDrive;

public class BenClaw {

    public int increment;
    //Fields
    private DcMotor motor;
    private final int TURN_POSITION = 3600;
    private LinearOpMode opMode;
    private boolean isOpen;

    //Constructor
    public BenClaw(HardwareMap hardwareMap, LinearOpMode opMode) {
        motor = hardwareMap.dcMotor.get("grabber_motor");
        this.opMode = opMode;
    }

    //Methods

    public void close() {

        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setTargetPosition(0);
        motor.setPower(0.5);

        while ((!reachedTarget(motor.getCurrentPosition(), motor.getTargetPosition()) || motor.isBusy()) && !opMode.isStopRequested() && opMode.opModeIsActive()) {
            opMode.idle();
        }

        motor.setPower(0);
        isOpen = false;
    }

    public void open() {

        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setTargetPosition(-increment);
        motor.setPower(0.5);

        while (approachingTarget(motor.getCurrentPosition(), motor.getTargetPosition()) && !opMode.isStopRequested() && opMode.opModeIsActive()) {
            opMode.idle();
        }

        motor.setPower(0);
        isOpen = true;
    }

    public int getMotorPosition() {
        return motor.getCurrentPosition();
    }

    public boolean isOpen() {
        return isOpen;
    }

    protected boolean reachedTarget(int currentPosition, int targetPosition) {
        return FastMath.abs(currentPosition - targetPosition) < EncoderDrive.TARGET_REACHED_THRESHOLD;
    }

    private boolean approachingTarget(int currentPosition, int targetPosition) {
        return !reachedTarget(currentPosition, targetPosition) && motor.isBusy();
    }
}
