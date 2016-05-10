package org.pattonvillerobotics.team2867.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Nathan Skelton on 10/15/15.
 * Last edited by Mitchell Skaggs on 11/14/15
 * <p/>
 */
public class Drive {

    // ---------- ENCODER AND GYRO CONSTANTS ---------- \\
    public static final double WHEEL_RADIUS = 1.2906932;
    public static final double WHEEL_CIRCUMFERENCE = 2 * Math.PI * WHEEL_RADIUS;
    public static final double TICKS_PER_REVOLUTION = 1440;
    public static final double INCHES_PER_TICK = WHEEL_CIRCUMFERENCE / TICKS_PER_REVOLUTION;
    public static final double WHEEL_BASE_RADIUS = 8.5;
    public static final double WHEEL_BASE_CIRCUMFERENCE = 2 * Math.PI * WHEEL_BASE_RADIUS;
    public static final int DEGREES_PER_REVOLUTION = 360; // Why lol
    public static final double INCHES_PER_DEGREE = WHEEL_BASE_CIRCUMFERENCE / DEGREES_PER_REVOLUTION;
    private static final String TAG = Drive.class.getSimpleName();

    // ----------- MOTOR SETUP -------- \\
    public DcMotor motorLeft;
    public DcMotor motorRight;
    public MRGyroHelper gyro;
    private HardwareMap hardwareMap;
    private LinearOpMode linearOpMode;


    // --------- CONSTRUCTOR -------- \\
    public Drive(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        this.hardwareMap = hardwareMap;
        this.linearOpMode = linearOpMode;

        this.motorLeft = hardwareMap.dcMotor.get(Globals.MOTOR_DRIVE_LEFT);
        this.motorRight = hardwareMap.dcMotor.get(Globals.MOTOR_DRIVE_RIGHT);

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.FORWARD);
    }

    // ---------- CONVERTS DEGREES MEASUREMENT INTO ENCODER TICKS -------- \\
    public static double degreesToTicks(double degrees) {
        return inchesToTicks(degrees * INCHES_PER_DEGREE);
    }

    // ---------- CONVERTS INCHES MEASUREMENT INTO ENCODER TICKS -------- \\
    public static double inchesToTicks(double inches) {
        return inches / INCHES_PER_TICK;
    }

    // --------- SLEEP -------- \\
    public void sleep(long milliseconds) {
        try {
            this.linearOpMode.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void move(double power) {
        moveFreely(power, power);
    }

    // --------- MOVE FREELY (USED IN TELE-OP) -------- \\
    public void moveFreely(double left, double right) {
        motorLeft.setPower(left);
        motorRight.setPower(right);
    }

    // --------- MOVE FOR INCHES -------- \\
    public void moveInches(Globals.DriveDirections direction, double inches, double power) {

        int targetPositionLeft;
        int targetPositionRight;

        this.waitForNextHardwareCycle();

        int startPositionLeft = motorLeft.getCurrentPosition();
        int startPositionRight = motorRight.getCurrentPosition();
        int deltaPosition = (int) Math.round(inchesToTicks(inches));

        switch (direction) {
            case FORWARD: {
                targetPositionLeft = startPositionLeft + deltaPosition;
                targetPositionRight = startPositionRight + deltaPosition;
                break;
            }
            case BACKWARD: {
                targetPositionLeft = startPositionLeft - deltaPosition;
                targetPositionRight = startPositionRight - deltaPosition;
                break;
            }
            default:
                throw new IllegalArgumentException("Direction must be FORWARDS or BACKWARDS!");
        }

        this.waitForNextHardwareCycle();

        motorLeft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorRight.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        this.waitForNextHardwareCycle();

        motorLeft.setTargetPosition(targetPositionLeft);
        motorRight.setTargetPosition(targetPositionRight);

        this.waitForNextHardwareCycle();

        motorLeft.setPower(power);
        motorRight.setPower(power);

        this.waitForNextHardwareCycle();

        this.linearOpMode.telemetry.addData(TAG, "Started encoder move...");
        while (this.linearOpMode.opModeIsActive() && Math.abs(motorRight.getCurrentPosition() - targetPositionRight) + Math.abs(motorLeft.getCurrentPosition() - targetPositionLeft) > Globals.ENCODER_MOVEMENT_TOLERANCE) {
            this.waitForNextHardwareCycle();
        }
        linearOpMode.telemetry.addData(TAG, "Finished encoder move...");

        this.stopDriveMotors();
    }

    // ----------- WAIT FOR NEXT HARDWARE CYCLE ---------- \\
    public void waitForNextHardwareCycle() {
        try {
            this.linearOpMode.waitForNextHardwareCycle();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ---------- STOP DRIVE ---------- \\
    public void stopDriveMotors() {
        motorLeft.setPower(0);
        motorRight.setPower(0);
    }

    // -------- ROTATE FOR DESIRED DEGREES -------- \\
    public void rotateDegrees(Globals.DriveDirections direction, int degrees, double power) {
        this.rotateDegreesGyro(direction, degrees, power);
        //this.rotateDegreesPID(direction, degrees, power);
    }


    // --------- USE GYRO TO ROTATE DEGREES -------- \\
    private void rotateDegreesGyro(Globals.DriveDirections direction, int degrees, double power) {
        this.waitForNextHardwareCycle();

        motorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        this.waitForNextHardwareCycle();

        int target = gyro.getIntegratedZValue();

        switch (direction) {
            case LEFT: {
                motorLeft.setPower(-power);
                motorRight.setPower(power);

                target += degrees + Globals.GYRO_TRIM;
                break;
            }
            case RIGHT: {
                motorLeft.setPower(power);
                motorRight.setPower(-power);

                target -= degrees - Globals.GYRO_TRIM;
                break;
            }
            default: {
                throw new IllegalArgumentException("Direction must be LEFT or RIGHT!");
            }
        }

        while (this.linearOpMode.opModeIsActive() && Math.abs(gyro.getIntegratedZValue() - target) > Globals.GYRO_TURN_TOLERANCE) {
            //this.linearOpMode.telemetry.addData(TAG, "Current degree readout: " + gyro.getIntegratedZValue());
            this.waitForNextHardwareCycle();
        }

        this.stopDriveMotors();

        this.waitForNextHardwareCycle();
    }

    @Deprecated
    private void rotateDegreesPID(Globals.DriveDirections direction, int degrees, final double power) {
        if (direction != Globals.DriveDirections.LEFT && direction != Globals.DriveDirections.RIGHT)
            throw new IllegalArgumentException("Direction must be LEFT or RIGHT!");

        this.waitForNextHardwareCycle();

        motorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        this.waitForNextHardwareCycle();

        int target = gyro.getIntegratedZValue() + (direction == Globals.DriveDirections.LEFT ? degrees : -degrees);
        double proportionalPower = power;

        while (this.linearOpMode.opModeIsActive() && Math.abs(target - gyro.getIntegratedZValue()) > Globals.GYRO_TURN_TOLERANCE) {
            //this.linearOpMode.telemetry.addData(TAG, "Current degree readout: " + gyro.getIntegratedZValue());
            this.waitForNextHardwareCycle();

            int error = target - gyro.getIntegratedZValue();

            double multiplier = Math.min(Math.sqrt(Math.abs(error)) / 10, 1);
            proportionalPower = power * multiplier;

            switch (direction) {
                case LEFT: {
                    motorLeft.setPower(-proportionalPower);
                    motorRight.setPower(proportionalPower);

                    //target += degrees;// + Config.GYRO_TRIM;
                    break;
                }
                case RIGHT: {
                    motorLeft.setPower(proportionalPower);
                    motorRight.setPower(-proportionalPower);

                    //target -= degrees;// - Config.GYRO_TRIM;
                    break;
                }
            }
        }

        this.stopDriveMotors();

        this.waitForNextHardwareCycle();
    }

}
