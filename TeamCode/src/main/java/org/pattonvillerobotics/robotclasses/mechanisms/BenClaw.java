package org.pattonvillerobotics.robotclasses.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Contains the servo for the claw mechanism, as well as all the methods involved with
 * the operation of the mechanism
 */
public class BenClaw extends AbstractMechanism {

    private final double OPEN_POSITION;
    private final double CLOSED_POSITION;
    private final double HALF_POSITION;
    public Servo claw;
    private boolean isOpen, isHalfOpen, isClosed;

    /**
     * Initializes the hardwaremap and linearopmode, as well as the claw's servo
     * Sets the position of the claw to open and initializes the isOpen boolean to true
     *
     * @param hardwareMap a hardwaremap to initialize the claw's servo
     * @param linearOpMode a linearopmode that allows for sleeping and telemetry
     */
    public BenClaw(HardwareMap hardwareMap, LinearOpMode linearOpMode, String name) {
        super(hardwareMap, linearOpMode);
        claw = hardwareMap.servo.get(name);
        isOpen = true;

        OPEN_POSITION = .8;
        CLOSED_POSITION = .15;
        HALF_POSITION = .5;
        claw.setPosition(OPEN_POSITION);
    }

    public BenClaw(HardwareMap hardwareMap, LinearOpMode linearOpMode, String name, double[] positions) {
        super(hardwareMap, linearOpMode);
        claw = hardwareMap.servo.get(name);
        claw.setPosition(1);
        isOpen = true;

        OPEN_POSITION = positions[0];
        CLOSED_POSITION = positions[2];
        HALF_POSITION = positions[1];
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
        if (isHalfOpen) {
            close();
            isOpen = false;
            isClosed = true;
            isHalfOpen = false;
        } else if (isOpen) {
            half();
            isHalfOpen = true;
            isClosed = false;
            isOpen = false;
        } else if (isClosed) {
            half();
            isHalfOpen = false;
            isOpen = false;
            isClosed = false;
        } else {
            open();
            isHalfOpen = false;
            isOpen = true;
            isClosed = false;
        }
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
