package org.pattonvillerobotics.robotclasses.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by gregbahr on 2/13/18.
 */

public class Spinner extends AbstractMechanism {

    private static final double UP_POSITION = .125;
    private static final double DOWN_POSITION = .85;
    private Servo spinnerServo;
    private SpinnerPosition currentPosition;

    public Spinner(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        super(hardwareMap, linearOpMode);
        spinnerServo = hardwareMap.servo.get("spinner");
        currentPosition = SpinnerPosition.UP;
    }

    public void toggleSpinnerPosition() {
        switch (this.currentPosition) {
            case UP:
                spinnerServo.setPosition(DOWN_POSITION);
                this.currentPosition = SpinnerPosition.DOWN;
                break;
            case DOWN:
                spinnerServo.setPosition(UP_POSITION);
                this.currentPosition = SpinnerPosition.UP;
                break;
        }
    }

    public SpinnerPosition getCurrentPosition() {
        return currentPosition;
    }

    public enum SpinnerPosition {
        UP, DOWN
    }
}
