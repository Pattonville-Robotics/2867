package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.apache.commons.math3.geometry.euclidean.threed.Line;

/**
 * Created by developer on 10/15/16.
 */

public class GuideRail {

    public Servo guideRail;
    public LinearOpMode linearOpMode;
/**
 * <p>
 *     Defines what the GuideRail is,
 *     defines the Servo that controls it,
 *     and sets the starting position
 * </p>
    */
    public GuideRail(HardwareMap hardwareMap, LinearOpMode linearOpMode){
        this.linearOpMode = linearOpMode;
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


    /**
     * <p>Explicitly sets the position of the guide rail servo and
     * calcaulates the angular degrees of the guide rail.<p/>\
     *
     * @param position servo position to set servo to
     */
    public void setPosition(double position){
        guideRail.setPosition(position);

        double degrees = (360 * position)/3;
        linearOpMode.telemetry.addData("Position", position);
        linearOpMode.telemetry.addData("Degrees", degrees);

    }

}
