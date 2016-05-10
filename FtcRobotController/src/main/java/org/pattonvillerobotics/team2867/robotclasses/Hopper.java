package org.pattonvillerobotics.team2867.robotclasses;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by zahnerj01 on 12/10/15.
 */
public class Hopper {

    private final static double MOTOR_POWER = 1;
    public HardwareMap hardwareMap;
    private DcMotor hopperMotor;

    public Hopper(HardwareMap hardwareMap) {

        this.hardwareMap = hardwareMap;

        hopperMotor = hardwareMap.dcMotor.get(Globals.HOPPER);
    }

    public void collect() {
        hopperMotor.setPower(MOTOR_POWER);
    }

    public void expel() {
        hopperMotor.setPower(-MOTOR_POWER);
    }

    public void stop() {
        hopperMotor.setPower(0);
    }

    @Override
    public String toString() {
        return "";
    }

}
