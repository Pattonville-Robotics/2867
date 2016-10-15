package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by developer on 10/15/16.
 */

public class GuideRail {

    public Servo guideRail;

    public GuideRail(HardwareMap hardwareMap){
        guideRail = hardwareMap.servo.get("rail");
        guideRail.setPosition(0.25);
    }

    public void setToAngle(double angle){
        guideRail.setPosition(degreesToPosition(angle));
    }

    private double degreesToPosition(double degrees){
        return (degrees * 3)/360;
    }

}
