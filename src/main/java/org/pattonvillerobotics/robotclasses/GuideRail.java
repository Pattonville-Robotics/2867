package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by developer on 10/15/16.
 */

public class GuideRail {

    public Servo guideRail;
/**
 * <p>
 *     Defines what the GuideRail is,
 *     defines the Servo that controls it,
 *     and sets the starting position
 * </p>
    */
    public GuideRail(HardwareMap hardwareMap){
        guideRail = hardwareMap.servo.get("rail");
        guideRail.setPosition(0.25);
    }

    /**
     * <p>
     *     Sets the position of the guide rail
     * </p>
     */
    public void setToAngle(double angle){
        guideRail.setPosition(degreesToPosition(angle));
    }

    /**
     * <p>
     *     Translates degree into servo position
     * </p>
     */
    private double degreesToPosition(double degrees){
        return (degrees * 3)/360;
    }

}
