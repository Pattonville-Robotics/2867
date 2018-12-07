package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TapeMeasureLifter extends AbstractMechanism {

    public DcMotor winchMotor;

    public TapeMeasureLifter(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        super(hardwareMap,linearOpMode);
        winchMotor = hardwareMap.dcMotor.get("winch_motor");
        winchMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        winchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    /**
     * Moves the motor of the winch 6 rotations forward to extend the tape
     * measure tape to lower off the lander
     */
    public void lower() {
        winchMotor.setTargetPosition(winchMotor.getCurrentPosition()+(1440*6));
        while(Math.abs(winchMotor.getCurrentPosition()-winchMotor.getTargetPosition()) > 16) {
            linearOpMode.telemetry.update();
            winchMotor.setPower(0.4);
        }
        winchMotor.setPower(0);
    }

    /**
     * Moves the motor of the winch 6 rotations backwards to retract the tape
     * measure tape to raise the lander
     */
    public void raise() {
        winchMotor.setTargetPosition(winchMotor.getCurrentPosition()-(1440*6));
        while(Math.abs(winchMotor.getCurrentPosition()-winchMotor.getTargetPosition()) > 16) {
            winchMotor.setPower(-0.7);
        }
        winchMotor.setPower(0);
    }

}
