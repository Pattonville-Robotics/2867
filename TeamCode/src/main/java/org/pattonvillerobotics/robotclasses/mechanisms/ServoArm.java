package org.pattonvillerobotics.robotclasses.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.pattonvillerobotics.commoncode.enums.ColorSensorColor;

/**
 * Created by skaggsw on 10/5/17.
 * <p>
 * Contains the servo and sensor for the arm mechanism, as well as all the methods involved with
 * the operation of the mechanism
 */
public class ServoArm extends AbstractMechanism {

    final double SCALE_FACTOR = 255;
    //private DistanceSensor sensorDistance;
    public ColorSensorColor ballColor;
    private Servo servo;
    private ColorSensor sensorColor;

    /**
     * Initializes the hardwaremap and linearopmode, as well as the servo and color sensor
     *
     * @param hardwareMap a hardwaremap to initialize the arm's servo and sensor
     * @param linearOpMode a linearopmode that allows for sleeping and telemetry
     */
    public ServoArm(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        super(hardwareMap, linearOpMode);
        servo = hardwareMap.servo.get("servo_arm");
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        //sensorDistance = hardwareMap.get(DistanceSensor.class,"sensor_color_distance");
    }

    /**
     * Extends the claw arm
     */
    public void extendArm() {
        servo.setPosition(1);
    }

    /**
     * Retracts the claw arm
     */
    public void retractArm() {
        servo.setPosition(0.15);
    }

    /**
     * Returns the claw's current position
     * @return position of the claw
     */
    public double getServoPosition() {
        return servo.getPosition();
    }

    /**
     * Uses the red and blue values from the color sensor to change, then return an enum of the color,
     * either red, blue, or green if it is not clearly one or the other
     * @return an enum of the color sensed by the color sensor
     */
    public ColorSensorColor senseBallColor() {
        if(sensorColor.red()*SCALE_FACTOR > 20 || sensorColor.blue()*SCALE_FACTOR > 20) {
            ballColor = sensorColor.red() > sensorColor.blue() ? ColorSensorColor.RED : ColorSensorColor.BLUE;
        } else {
            ballColor = ColorSensorColor.GREEN;
        }
        return ballColor;
    }

    /**
     * Returns the last sensed ball color
     * @return an enum of the last sensed ball color
     */
    public ColorSensorColor getBallColor() {
        return ballColor;
    }
}
