package org.pattonvillerobotics.robotclasses;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRRangeSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.robotclasses.drive.AbstractDrive;
import org.pattonvillerobotics.commoncode.robotclasses.drive.EncoderDrive;
import org.pattonvillerobotics.commoncode.robotclasses.drive.RobotParameters;

/**
 * Created by developer on 11/1/16.
 */

public class LineFollowerDrive extends EncoderDrive {

    /**
     * the reflectivity value of the white tape as determined
     * in testing.
     */
    private static final double WHITE_TAPE_REFLECTIVITY_MIN = 0.2;
    private static final double WHITE_TAPE_REFLECTIVITY_MAX = 0.3;
    private static final double DISTANCE_OFFSET = 2;

    private OpticalDistanceSensor ods;
    private ModernRoboticsI2cRangeSensor range;

    /**
     * expands upon the EncoderDrive functionality by adding
     * support for ths Optical Distance Sensor. This allows
     * for driving the robot until it senses a certain color
     * beneath it and allows for certain actions to be taken
     * under such conditions.
     *
     * @param hardwareMap     robot's current hardwareMap
     * @param linearOpMode    a linearOpMode object
     * @param robotParameters our custom RobotParameters Class
     *
     * @see EncoderDrive
     * @see org.pattonvillerobotics.opmodes.CustomRobotParameters
     * @see HardwareMap
     * @see LinearOpMode
     * @see OpticalDistanceSensor
     * @see ModernRoboticsI2cRangeSensor
     */
    public LineFollowerDrive(HardwareMap hardwareMap, LinearOpMode linearOpMode, RobotParameters robotParameters) {
        super(hardwareMap, linearOpMode, robotParameters);

        ods = hardwareMap.opticalDistanceSensor.get("ods");
        //range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");

    }

    /**
     * drives forward until the the ODS sensor finds
     * the white tape line that signifies the center
     * of the beacon
     *
     * @see LineFollowerDrive#foundLine()
     */
    public void driveUntilLine() {
        move(Direction.FORWARD, 0.3);
        Telemetry.Item item = telemetry("");
        linearOpMode.sleep(1000);
        while (!foundLine() && linearOpMode.opModeIsActive()) {
            item.setValue("PowerL: " + leftDriveMotor.getPower() + " PowerR: " + rightDriveMotor.getPower());
            linearOpMode.telemetry.update();
        }
        stop();

    }

    /**
     * drive robot forwards until it is a defined distance
     * from an object (usually the wall). Used in autonomous
     * to stop robot before it crashes into wall when hitting
     * the beacon.
     *
     * @param distance the distance we want to be from a certain object
     *
     * @see LineFollowerDrive#DISTANCE_OFFSET
     */
    /*public void driveUntilDistanceFrom(double distance){
        while(range.getDistance(DistanceUnit.INCH) > DISTANCE_OFFSET && linearOpMode.opModeIsActive()){
            move(Direction.FORWARD, 0.3);
        }
        stop();
    }*/

    /**
     * aligns the robot with the white line so that the robot
     * is directly perpendicular to the field wall and facing
     * the beacon. Utilizes the EncoderDrive.rotateDegrees()
     * method to turn 90 - current heading towards the beacon.
     *
     * @param direction      direction in which to turn
     * @param currentHeading the current heading of
     *                       the robot relative to
     *                       perpendicular to the field
     *                       wall
     * @see Direction
     */
    public void align(Direction direction, double currentHeading) {
        rotateDegrees(direction, 90 - currentHeading, 0.2);
        stop();
    }

    /**
     * Determine whether or not the ods sensor is locating a
     * white colored tape (the white tape line).
     *
     * @return boolean dictating whether or not the sensor is
     * detecting the white line
     * @see LineFollowerDrive#WHITE_TAPE_REFLECTIVITY_MIN
     * @see LineFollowerDrive#WHITE_TAPE_REFLECTIVITY_MAX
     */
    private boolean foundLine() {
        telemetry("ODS: ", String.valueOf(ods.getRawLightDetected()));
        return ods.getRawLightDetected() >= WHITE_TAPE_REFLECTIVITY_MIN && ods.getRawLightDetected() <= WHITE_TAPE_REFLECTIVITY_MAX;
    }

}