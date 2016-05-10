package org.pattonvillerobotics.team2867.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Arrays;

/**
 * Created by zahnerj01 on 11/19/15.
 */
public class Autonomous {

    HardwareMap hardwareMap;
    LinearOpMode linearOpMode;
    private Drive drive;
    private ClimberBucket climberBucket;
    private Clamp clamp;
    private ColorSensor colorSensor;
    private Globals.Colors color;

    public Autonomous(Globals.Colors color, LinearOpMode linearOpMode) {

        try {

            this.color = color;
            this.linearOpMode = linearOpMode;
            this.hardwareMap = linearOpMode.hardwareMap;

            drive = new Drive(this.hardwareMap, linearOpMode);

            /*

            climberBucket = new ClimberBucket();
            clamp = new Clamp();
            buttonPresser = new ButtonPresser();

            */

        } catch (Exception e) {

            telemetry("Error", Arrays.toString(e.getStackTrace()));

        }

    }

    private void telemetry(String key, String msg) {
        this.linearOpMode.telemetry.addData(key, msg);
    }

    public void red1ToBeacon() {
        drive.move(0.75);
        sleep(5000);
        climberBucket.dumpClimbers();
    }

    private void sleep(int time) {
        try {
            this.linearOpMode.sleep(time);
            drive.stopDriveMotors();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public void driveToBeacon() {

        Globals.DriveDirections turnDirection;

        switch (color) {
            case BLUE:
                turnDirection = Globals.DriveDirections.RIGHT;
                break;
            case RED:
                turnDirection = Globals.DriveDirections.LEFT;
                break;
            default:
                throw new IllegalArgumentException("Error");
        }

        drive.moveInches(Globals.DriveDirections.FORWARD, Globals.X_DISTANCE, 0.5);
        drive.rotateDegrees(turnDirection, 45, 0.3);
        drive.moveInches(Globals.DriveDirections.FORWARD, Globals.Y_DISTANCE, 0.5);
        drive.moveInches(turnDirection, 45, 0.3);
        drive.moveInches(Globals.DriveDirections.FORWARD, Globals.Z_DISTANCE, 0.5);

    }

    @Deprecated
    public void decideColor() {
        Globals.ButtonPresserDirection buttonPresserDirection;
        switch (color) {
            case BLUE:
                if (colorSensor.blue() != 0) {
                    buttonPresserDirection = Globals.ButtonPresserDirection.RIGHT;
                } else {
                    buttonPresserDirection = Globals.ButtonPresserDirection.LEFT;
                }

                telemetry("DesiredColor", "Blue");
                telemetry("OtherColor", "Red");
                break;

            case RED:
                if (colorSensor.blue() == 0) {
                    buttonPresserDirection = Globals.ButtonPresserDirection.RIGHT;
                } else {
                    buttonPresserDirection = Globals.ButtonPresserDirection.LEFT;
                }
                telemetry("DesiredColor", "Red");
                telemetry("OtherColor", "Blue");
                break;
            default:
                throw new IllegalArgumentException("Error");
        }

        //buttonPresser.rotate();
        drive.moveInches(Globals.DriveDirections.FORWARD, 3, 0.3);

        telemetry("Colors", colorSensor.blue() + ", " + colorSensor.red());

    }

}
