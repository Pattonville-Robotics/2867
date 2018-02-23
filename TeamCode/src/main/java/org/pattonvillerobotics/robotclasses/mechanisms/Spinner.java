package org.pattonvillerobotics.robotclasses.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by gregbahr on 2/13/18.
 */

public class Spinner extends AbstractMechanism {

    public DcMotor spinnerMotor;
    private SpinnerPosition currentPosition;

    public Spinner(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        super(hardwareMap, linearOpMode);
        spinnerMotor = hardwareMap.dcMotor.get("spinner");
        spinnerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        currentPosition = SpinnerPosition.UP;
    }

    public void toggleSpinnerPosition() {
        spinnerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int targetPosition = 0;

        switch (currentPosition) {
            case UP:
                targetPosition = 120;
                currentPosition = SpinnerPosition.DOWN;
                break;
            case DOWN:
                targetPosition = 0;
                currentPosition = SpinnerPosition.UP;
                break;
        }

        spinnerMotor.setTargetPosition(targetPosition);
        spinnerMotor.setPower(.8);
        while (spinnerMotor.isBusy()) {
        }
        spinnerMotor.setPower(0);
        spinnerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public SpinnerPosition getCurrentPosition() {
        return currentPosition;
    }

    public enum SpinnerPosition {
        UP, DOWN
    }
}
