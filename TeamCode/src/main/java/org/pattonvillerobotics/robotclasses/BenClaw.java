package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BenClaw {

    public Servo claw;
    private final int TURN_POSITION = 3600;
    private boolean isOpen;

    /**
     * @param hardwareMap
     */
    public BenClaw(HardwareMap hardwareMap) {
        claw = hardwareMap.servo.get("claw");
        claw.setPosition(1);
    }

    /**
     * Toggles the claw between open and close positions
     * Negates isOpen
     */
    public void opencloseToggle() {
        if(isOpen) {
            claw.setPosition(0.1);
        } else {
            claw.setPosition(1);
        }
        isOpen = !isOpen;
    }

    /**
     * Returns the claw's current position
     * @return position of the claw
     */
    public double getServoPosition() {
        return claw.getPosition();
    }

    /**
     * Checks if the claw is open
     * @return whether the claw is open or not
     */
    public boolean isOpen() {
        return isOpen;
    }
}
