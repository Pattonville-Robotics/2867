package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.pattonvillerobotics.commoncode.enums.ColorSensorColor;

/**
 * Created by skaggsw on 10/5/17.
 */

public class ServoArm {

    private Servo servo;
    private ColorSensor sensorColor;
    //private DistanceSensor sensorDistance;
    public ColorSensorColor ballColor;
    final double SCALE_FACTOR = 255;

    public ServoArm(HardwareMap hardwareMap, LinearOpMode opMode) {
        servo = hardwareMap.servo.get("servo_arm");
        sensorColor = hardwareMap.get(ColorSensor.class,"sensor_color_distance");
        //sensorDistance = hardwareMap.get(DistanceSensor.class,"sensor_color_distance");
    }

    public void extendArm() {
        servo.setPosition(1);
    }

    public void retractArm() {
        servo.setPosition(0.4);
    }

    public double getServoPosition() {
        return servo.getPosition();
    }

    public void senseBallColor() {
        ballColor = sensorColor.red() > sensorColor.blue() ? ColorSensorColor.RED : ColorSensorColor.BLUE;
    }
}
