package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.pattonvillerobotics.commoncode.enums.ColorSensorColor;

/**
 * Created by skaggsw on 10/5/17.
 */

public class ServoArm {

    final double SCALE_FACTOR = 255;
    //private DistanceSensor sensorDistance;
    public ColorSensorColor ballColor;
    private Servo servo;
    private ColorSensor sensorColor;

    /**
     * @param hardwareMap a hardwaremap to setup the claw and sensor
     */
    public ServoArm(HardwareMap hardwareMap) {
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
