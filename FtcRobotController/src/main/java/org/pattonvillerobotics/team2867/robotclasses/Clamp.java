package org.pattonvillerobotics.team2867.robotclasses;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by zahnerj01 on 12/10/15.
 */
public class Clamp {


    public Servo clampServoLeft, clampServoRight;
    public HardwareMap hardwareMap;

    public Clamp(HardwareMap hardwareMap) {

        this.hardwareMap = hardwareMap;
        clampServoLeft = hardwareMap.servo.get(Globals.CLAMP_LEFT);
        clampServoRight = hardwareMap.servo.get(Globals.CLAMP_RIGHT);

    }

    public void engage() {
        setPosition(Globals.CLAMP_ENGAGED_POSITION);
    }

    public void setPosition(double position) {
        clampServoLeft.setPosition(position);
        clampServoRight.setPosition(position);
    }

    public void disengage() {
        setPosition(Globals.CLAMP_DEFAULT);
    }

    @Override
    public String toString() {
        return "";
    }


}
