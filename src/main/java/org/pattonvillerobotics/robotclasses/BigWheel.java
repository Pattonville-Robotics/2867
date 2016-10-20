package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by developer on 10/13/16.
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
    public LinearOpMode linearOpMode;

    public BigWheel(HardwareMap hardwaremap, LinearOpMode linearOpMode){
        bigWheel = hardwaremap.dcMotor.get("big_wheel");
        this.linearOpMode = linearOpMode;
    }

    /**
     * This method moves the wheel forward at 0.5 motor speed.
     */
    public void wheelForward(){
        bigWheel.setPower(.5);
    }

    /**
     * This method moves the wheel backward at -0.5 motor speed.
     */
    public void wheelBackward(){
        bigWheel.setPower(-.5);
    }

    /**
     * This method stops the wheel.
     */
    public void wheelStop(){
        bigWheel.setPower(0);
    }

    /**
     * This method primes the wheel to shoot.
     */
    public void primeToShoot(){
        wheelForward();
        linearOpMode.sleep(250);
        wheelStop();
    }

}
