package org.pattonvillerobotics.robotclasses.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Contains the servo for the claw mechanism, as well as all the methods involved with
 * the operation of the mechanism
 */
public class BenClaw extends AbstractMechanism {

    private static final double OPEN_POSITION = .95;
    private static final double CLOSED_POSITION = .1;
    private static final double HALF_POSITION = .8;
    public Servo claw;
    private boolean isOpen;

    /**
     * Initializes the hardwaremap and linearopmode, as well as the claw's servo
     * Sets the position of the claw to open and initializes the isOpen boolean to true
     *
     * @param hardwareMap a hardwaremap to initialize the claw's servo
     * @param linearOpMode a linearopmode that allows for sleeping and telemetry
     */
    public BenClaw(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        super(hardwareMap, linearOpMode);
        claw = hardwareMap.servo.get("claw");
        claw.setPosition(1);
        isOpen = true;
    }

    /**
     * Opens the claw
     */
    public void open() {
        claw.setPosition(OPEN_POSITION);
    }

    /**
     * Closes the claw
     */
    public void close() {
        claw.setPosition(CLOSED_POSITION);
    }

    public void half() {
        claw.setPosition(HALF_POSITION);
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
