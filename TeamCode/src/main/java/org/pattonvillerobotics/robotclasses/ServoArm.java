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

    final double SCALE_FACTOR = 255;
    //private DistanceSensor sensorDistance;
    public ColorSensorColor ballColor;
    private Servo servo;
    private ColorSensor sensorColor;

    public ServoArm(HardwareMap hardwareMap, LinearOpMode opMode) {
        servo = hardwareMap.servo.get("servo_arm");
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        //sensorDistance = hardwareMap.get(DistanceSensor.class,"sensor_color_distance");
    }

    public void extendArm() {
        servo.setPosition(1);
    }

    public void retractArm() {
        servo.setPosition(0.15);
    }

    public double getServoPosition() {
        return servo.getPosition();
    }

    public void senseBallColor() {
        if(sensorColor.red()*SCALE_FACTOR > 20 || sensorColor.blue()*SCALE_FACTOR > 20) {
            ballColor = sensorColor.red() > sensorColor.blue() ? ColorSensorColor.RED : ColorSensorColor.BLUE;
        } else {
            ballColor = ColorSensorColor.GREEN;
        }
    }
}
