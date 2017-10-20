package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BenClaw {

    public int increment;
    //Fields
    public Servo servo;
    private final int TURN_POSITION = 3600;
    private boolean isOpen;

    //Constructor
    public BenClaw(HardwareMap hardwareMap, LinearOpMode opMode) {
        servo = hardwareMap.servo.get("grabber_servo");
    }

    //Methods

    public void close() {
        //servo.setPosition();
    }

    public void open() {
        //servo.setPosition();
    }

    public double getServoPosition() {
        return servo.getPosition();
    }

    public boolean isOpen() {
        return isOpen;
    }
}
