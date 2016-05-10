package org.pattonvillerobotics.team2867.robotclasses;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by zahnerj01 on 12/10/15.
 */
public class DebrisBucket {

    public Servo debrisServo;

    public DebrisBucket(HardwareMap hardwareMap) {
        debrisServo = hardwareMap.servo.get(Globals.DEBRIS_BUCKET);
        debrisServo.setPosition(Globals.DEBRIS_BUCKET_DEFAULT);
    }

    public void dumpDebris() {
        setPosition(Globals.DEBRIS_BUCKET_DUMP_POSITION);
    }

    public void setPosition(double position) {
        debrisServo.setPosition(position);
    }

    public void returnToStart() {
        setPosition(Globals.DEBRIS_BUCKET_DEFAULT);
    }


}
