package org.pattonvillerobotics.robotclasses.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by skaggsw on 10/24/17.
 */

public class GlyphLifter extends AbstractMechanism {

    private BenClaw claw;
    private SlideMotor slideMotor;

    public GlyphLifter(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        super(hardwareMap,linearOpMode);
        claw = new BenClaw(hardwareMap, linearOpMode, "bottom_claw");
        slideMotor = new SlideMotor(hardwareMap,linearOpMode);
    }

    public void setSlideMotorPower(double power) {
        slideMotor.setMotorPower(power);
    }
}
