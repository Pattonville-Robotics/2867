package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Optional;

public class IntakeMechanism extends AbstractMechanism {

    public DcMotor intakeMotor, leftExtendMotor, rightExtendMotor;
    public Servo leftDropServo, rightDropServo;
    public boolean isDropped, isIntaking;

    public IntakeMechanism(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        super(hardwareMap,linearOpMode);

        intakeMotor      = hardwareMap.dcMotor.get("intake_motor");
        intakeMotor       .setDirection(DcMotorSimple.Direction.REVERSE);

        leftExtendMotor  = hardwareMap.dcMotor.get("left_extend_motor");
        rightExtendMotor = hardwareMap.dcMotor.get("right_extend_motor");

        leftExtendMotor   .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightExtendMotor  .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftExtendMotor   .setDirection(DcMotorSimple.Direction.FORWARD);
        rightExtendMotor  .setDirection(DcMotorSimple.Direction.REVERSE);

        leftDropServo    = hardwareMap.servo.get("left_drop_servo");
        rightDropServo   = hardwareMap.servo.get("right_drop_servo");

        leftDropServo     .setDirection(Servo.Direction.FORWARD);
        rightDropServo    .setDirection(Servo.Direction.REVERSE);

        leftDropServo.scaleRange(0,0.6);
        rightDropServo.scaleRange(0,0.6);
    }

    public void setServoPositions(double position) {
        leftDropServo.setPosition(position);
        rightDropServo.setPosition(position);
    }

    public void setExtentionPower(double power) {
        leftExtendMotor.setPower(power);
        rightExtendMotor.setPower(power);
    }

    /**
     * Drops the intake box
     */
    public void drop() {
        leftDropServo.setPosition(0.6);
        rightDropServo.setPosition(0.6);
        isDropped = true;
    }

    /**
     * Raises the intake box
     */
    public void raise() {
        leftDropServo.setPosition(0);
        rightDropServo.setPosition(0);
        isDropped = false;
    }

    /**
     * Turns the motor on to take in minerals
     */
    public void intake() {
        intakeMotor.setPower(1);
        isIntaking = true;
    }

    /**
     * Turns the motor on to spit out the contents of the box
     */
    public void expulsion() {
        intakeMotor.setPower(-1);
        isIntaking = true;
    }

    /**
     * Turns off the motor
     */
    public void turnOffIntake() {
        intakeMotor.setPower(0);
        isIntaking = false;
    }
}
