package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TapeMeasureLifter extends AbstractMechanism {

    public DcMotor winchMotor;

    public TapeMeasureLifter(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        super(hardwareMap,linearOpMode);
        winchMotor = hardwareMap.dcMotor.get("winch_motor");
    }

    public void lower() {
        winchMotor.setTargetPosition(winchMotor.getCurrentPosition()+(1440*6));
        while(Math.abs(winchMotor.getCurrentPosition()-winchMotor.getTargetPosition()) > 16) {
            linearOpMode.telemetry.update();
            winchMotor.setPower(0.4);
        }
        winchMotor.setPower(0);
    }

    public void raise() {
        winchMotor.setTargetPosition(winchMotor.getCurrentPosition()-(1440*6));
        while(Math.abs(winchMotor.getCurrentPosition()-winchMotor.getTargetPosition()) > 16) {
            winchMotor.setPower(-0.7);
        }
        winchMotor.setPower(0);
    }

}
