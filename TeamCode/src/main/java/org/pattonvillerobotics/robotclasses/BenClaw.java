package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BenClaw {

    public Servo servo;
    private final int TURN_POSITION = 3600;
    private boolean isOpen;

    /**
     * @param hardwareMap
     */
    public BenClaw(HardwareMap hardwareMap) {
        servo = hardwareMap.servo.get("grabber_servo");
    }

    /**
     * Closes the claw
     */
    public void close() {
        //servo.setPosition();
    }

    /**
     * Opens the claw
     */
    public void open() {
        //servo.setPosition();
    }

    /**
     * Returns the servo's current position
     * @return position of the servo
     */
    public double getServoPosition() {
        return servo.getPosition();
    }

    /**
     * Checks if the claw is open
     * @return whether the claw is open or not
     */
    public boolean isOpen() {
        return isOpen;
    }
}
