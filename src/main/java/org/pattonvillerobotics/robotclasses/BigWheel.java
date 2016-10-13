package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by developer on 10/13/16.
 */

public class BigWheel {
    public DcMotor bigWheel;

    public BigWheel(HardwareMap hardwaremap){
        bigWheel = hardwaremap.dcMotor.get("big_wheel");
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

}
