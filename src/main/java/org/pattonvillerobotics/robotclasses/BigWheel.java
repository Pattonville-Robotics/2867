package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Bridget Farrell on 10/13/16.
 * <p>
 * The BigWheel Class allows the user to move the wheel
 * forward or backward.
 * <p>
 * It also allows the user to prime the wheel so the
 * particle can move within the wheel the spot it needs
 * to be before the user launches it. The priming method
 * does not shoot the particle.
 */

public class BigWheel {

    public DcMotor bigWheel;
    public DcMotor secondWheelMotor;
    public LinearOpMode linearOpMode;

    /**
     *
     * @param hardwaremap the hardwareMap associated with the robot
     * @param linearOpMode the linearOpMode in which the object is
     *                     instantiated. This allows us to effectively
     *                     stop the wheel.
     */

    public BigWheel(HardwareMap hardwaremap, LinearOpMode linearOpMode){
        bigWheel = hardwaremap.dcMotor.get("big_wheel");
        secondWheelMotor = hardwaremap.dcMotor.get("second_wheel_motor");
        bigWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        secondWheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        bigWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        secondWheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        this.linearOpMode = linearOpMode;
    }

    /**
     * Moves the wheel forward at 0.5 motor speed.
     */
    public void moveForward(){
        bigWheel.setPower(0.33);
    }

    /**
     * Moves the wheel backward at -0.5 motor speed.
     */
    public void moveBackward(){
        bigWheel.setPower(1.0);
    }

    /**
     * Stops the wheel.
     */
    public void stop(){
        bigWheel.setPower(0);
        secondWheelMotor.setPower(0);
    }

    public void fire(){
        move(1.0);
    }

    /**
     * Moves wheel with supplied power. Used in teleOp to map
     * values of trigger buttons to motor power for wheel.
     *
     * @param power motor power to supply to the motor. Positive
     *              rotates forward, negative rotates backward.
     */
    public void move(double power){
        bigWheel.setPower(power);
        secondWheelMotor.setPower(power);
    }

}
