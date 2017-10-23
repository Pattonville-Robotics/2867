package org.pattonvillerobotics.robotclasses.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by gregbahr on 10/23/17.
 */

public abstract class AbstractMechanism {
    protected final HardwareMap hardwareMap;
    protected final LinearOpMode linearOpMode;

    public AbstractMechanism(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        this.hardwareMap = hardwareMap;
        this.linearOpMode = linearOpMode;
    }
}
