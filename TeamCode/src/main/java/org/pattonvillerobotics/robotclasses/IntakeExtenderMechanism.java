package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeExtenderMechanism extends AbstractMechanism {

    public DcMotor spinMotor;

    public IntakeExtenderMechanism(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        super(hardwareMap,linearOpMode);
        spinMotor = hardwareMap.dcMotor.get("spin_motor");
        extendMotor1 = hardwareMap.dcMotor.get("extend_motor_one");
        extendMotor2 = hardwareMap.dcMotor.get("extend_motor_two");
    }

    public void extendIntake() {
        spinMotor.setTargetPosition(spinMotor.getCurrentPosition()-(1440*6));
        while(Math.abs(spinMotor.getCurrentPosition()-spinMotor.getTargetPosition()) > 16) {
            spinMotor.setPower(-0.7);
        }
        spinMotor.setPower(0);
        spinMotor.setPower(0.4);

    }


    public void recallIntake() {
        spinMotor.setTargetPosition(spinMotor.getCurrentPosition()-(1440*6));
        while(Math.abs(spinMotor.getCurrentPosition()-spinMotor.getTargetPosition()) > 16) {
            spinMotor.setPower(-0.7);
        }
        spinMotor.setPower(0);
    }

}
