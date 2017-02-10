package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by joshua on 1/31/17.
 */

public class ForkLift {

    private DcMotor leftWinch;
    private DcMotor rightWinch;

    private HardwareMap hardwareMap;
    private LinearOpMode linearOpMode;

    public ForkLift(HardwareMap hardwareMap, LinearOpMode linearOpMode){

        this.hardwareMap = hardwareMap;
        this.linearOpMode = linearOpMode;

        leftWinch = hardwareMap.dcMotor.get("left_winch");
        rightWinch = hardwareMap.dcMotor.get("right_winch");

    }

    public void raiseSlides(){
        runMotors(0.75);
    }

    public void lowerSlides(){
        runMotors(-0.75);
    }

    public void stopSlides(){
        runMotors(0);
        linearOpMode.sleep(100);
    }

    public void extendForks(){
        raiseSlides();
        linearOpMode.sleep(2000);

        stopSlides();

        lowerSlides();
        linearOpMode.sleep(2000);
    }


    private void runMotors(double power){
        leftWinch.setPower(power);
        rightWinch.setPower(power);
    }

}
