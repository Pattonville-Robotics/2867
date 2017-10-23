package org.pattonvillerobotics.robotclasses.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BenClaw extends AbstractMechanism {

    private static final double OPEN_POSITION = 1;
    private static final double CLOSED_POSITION = .1;
    public Servo claw;
    private boolean isOpen;

    public BenClaw(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        super(hardwareMap, linearOpMode);
        claw = hardwareMap.servo.get("claw");
        claw.setPosition(1);
        isOpen = true;
    }

    public void open() {
        claw.setPosition(OPEN_POSITION);
    }

    public void close() {
        claw.setPosition(CLOSED_POSITION);
    }

    /**
     * Toggles the claw between open and closed positions
     * Negates isOpen
     */
    public void togglePosition() {
        if(isOpen) {
            close();
        } else {
            open();
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
