package org.pattonvillerobotics.robotclasses.mechanisms;

import android.os.AsyncTask;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by gregbahr on 2/13/18.
 */

public class Spinner extends AbstractMechanism {

    private static int ENCODER_TICKS = 144;
    public DcMotor spinnerMotor;


    public Spinner(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        super(hardwareMap, linearOpMode);

        spinnerMotor = hardwareMap.dcMotor.get("spinner");
        spinnerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void rotate180() {
        spinnerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int targetPosition = spinnerMotor.getCurrentPosition() + ENCODER_TICKS;

        spinnerMotor.setTargetPosition(targetPosition);

        AsyncTask.execute(() -> {
            spinnerMotor.setPower(1);
            while (spinnerMotor.isBusy()) {
            }
            spinnerMotor.setPower(0);
        });
    }
}
