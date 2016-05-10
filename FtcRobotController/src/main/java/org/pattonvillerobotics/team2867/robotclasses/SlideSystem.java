package org.pattonvillerobotics.team2867.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by zahnerj01 on 12/10/15.
 */
public class SlideSystem {

    private final String TAG = "slide_system";
    public int targetPosition;


    LinearOpMode linearOpMode;
    DcMotor slideLeft, slideRight;

    public SlideSystem(HardwareMap hardwareMap, LinearOpMode linearOpMode) {

        this.linearOpMode = linearOpMode;
        slideLeft = hardwareMap.dcMotor.get(Globals.SLIDE_LEFT);
        slideRight = hardwareMap.dcMotor.get(Globals.SLIDE_RIGHT);

        slideLeft.setDirection(DcMotor.Direction.REVERSE);
        slideRight.setDirection(DcMotor.Direction.FORWARD);

    }

    /*public void goToPosition(Globals.ScoringBucket bucket){

        int startPosition, positionDifference;

        waitForNextHardwareCycle();

        leftSlideMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        switch(bucket){
            case START:
                targetPosition = Globals.START_BUCKET_ENCODER_POSITION;
                break;
            case LOW:
                targetPosition = Globals.LOW_BUCKET_ENCODER_POSITION;//encoder targetPosition for low bucket
                break;
            case MIDDLE:
                targetPosition = Globals.MEDIUM_BUCKET_ENCODER_POSITION;//encoder targetPosition for middle bucket
                break;
            case HIGH:
                targetPosition = Globals.HIGH_BUCKET_ENCODER_POSITION;//encoder targetPosition for high bucket
                break;
            default:
                throw new IllegalArgumentException("ERROR. CHECK YOUR ARGUMENTS!");
        }

        waitForNextHardwareCycle();

        startPosition = leftSlideMotor.getCurrentPosition();

        positionDifference = targetPosition - startPosition;

        leftSlideMotor.setTargetPosition(targetPosition);

        waitForNextHardwareCycle();

        if(positionDifference > 0){
            leftSlideMotor.setPower(Globals.DEFAULT_LINEAR_SLIDE_POWER);
        }
        else if(positionDifference < 0){
            leftSlideMotor.setPower(-Globals.DEFAULT_LINEAR_SLIDE_POWER);
        }
        else if(positionDifference == 0){
            leftSlideMotor.setPower(0);
        }
        else{
            throw new Error("The Motors are not at the same position.");
        }

        waitForNextHardwareCycle();

        this.linearOpMode.telemetry.addData(TAG, "Started encoder move...");

        while (Math.abs(leftSlideMotor.getCurrentPosition() - targetPosition) < Globals.ENCODER_MOVEMENT_TOLERANCE) {
            waitForNextHardwareCycle();
        }

        this.linearOpMode.telemetry.addData(TAG, "Finished encoder move...");

        leftSlideMotor.setPower(0);

    }*/

    public void incrementPosition() {

        slideLeft.setPower(Globals.DEFAULT_LINEAR_SLIDE_POWER);
        slideRight.setPower(Globals.DEFAULT_LINEAR_SLIDE_POWER);

    }

    public void decrementPosition() {

        slideLeft.setPower(-Globals.DEFAULT_LINEAR_SLIDE_POWER);
        slideRight.setPower(-Globals.DEFAULT_LINEAR_SLIDE_POWER);

    }

    public void pickUpSlack() {
        slideRight.setPower(0);
        slideLeft.setPower(-Globals.DEFAULT_LINEAR_SLIDE_POWER);
        sleep(1000);
    }

    // --------- SLEEP -------- \\
    public void sleep(long milliseconds) {
        try {
            this.linearOpMode.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        slideLeft.setPower(0);
        slideRight.setPower(0);
    }

    // ----------- WAIT FOR NEXT HARDWARE CYCLE ---------- \\
    public void waitForNextHardwareCycle() {
        try {
            this.linearOpMode.waitForNextHardwareCycle();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "Slide System at" + slideLeft.getCurrentPosition();
    }

}
