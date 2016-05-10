package org.pattonvillerobotics.team2867.robotclasses;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by zahnerj01 on 2/19/16.
 */
public class ZipLineTrigger {

    HardwareMap hardwareMap;
    Servo zltServo;

    public ZipLineTrigger(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        zltServo = hardwareMap.servo.get(Globals.ZLT);
    }

    public void trigger() {
        setPosition(Globals.ZLT_TRIGGERED_POSITION);
    }

    private void setPosition(double position) {
        zltServo.setPosition(position);
    }

    public void returnToStart() {
        setPosition(Globals.ZLT_DEFAULT);
    }

}
