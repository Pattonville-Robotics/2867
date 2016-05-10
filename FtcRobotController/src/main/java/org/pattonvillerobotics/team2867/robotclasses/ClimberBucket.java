package org.pattonvillerobotics.team2867.robotclasses;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by zahnerj01 on 12/10/15.
 */
public class ClimberBucket {

    public Servo climberBucketServo;
    public HardwareMap hardwareMap;

    public ClimberBucket(HardwareMap hardwareMap) {

        this.hardwareMap = hardwareMap;
        climberBucketServo = hardwareMap.servo.get(Globals.CLIMBER_BUCKET);
        returnToStart();
    }

    public void returnToStart() {
        setPosition(Globals.CLIMBER_BUCKET_DEFAULT);
    }

    public void setPosition(double position) {
        climberBucketServo.setPosition(position);
    }

    public void dumpClimbers() {
        setPosition(Globals.CLIMBER_BUCKET_DOWN_POSITION);
    }

    @Override
    public String toString() {
        return "";
    }

}
