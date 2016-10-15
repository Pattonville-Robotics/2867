package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by developer on 10/13/16.
 */

public class BigWheel {

    public DcMotor bigWheel;
    public LinearOpMode linearOpMode;

    public BigWheel(HardwareMap hardwaremap, LinearOpMode linearOpMode){
        bigWheel = hardwaremap.dcMotor.get("big_wheel");
        this.linearOpMode = linearOpMode;
    }

    public void wheelForward(){
        bigWheel.setPower(.5);
    }

    public void wheelBackward(){
        bigWheel.setPower(-.5);
    }

    public void wheelStop(){
        bigWheel.setPower(0);
    }

    public void primeToShoot(){
        wheelForward();
        linearOpMode.sleep(250);
    }

}
