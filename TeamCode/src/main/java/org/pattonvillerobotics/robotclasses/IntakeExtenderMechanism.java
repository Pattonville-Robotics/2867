package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeExtenderMechanism extends AbstractMechanism {

    public DcMotor spinMotor;
    public DcMotor leftExtendMotor;
    public DcMotor rightExtendMotor;

    public IntakeExtenderMechanism(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        super(hardwareMap,linearOpMode);
        spinMotor = hardwareMap.dcMotor.get("spin_motor");
        leftExtendMotor = hardwareMap.dcMotor.get("left_extend_motor");
        rightExtendMotor = hardwareMap.dcMotor.get("right_extend_motor");
    }

    public void extendIntake() {
        leftExtendMotor.setTargetPosition(leftExtendMotor.getCurrentPosition()-(360));
        while(Math.abs(leftExtendMotor.getCurrentPosition()-leftExtendMotor.getTargetPosition()) > 16) {
            leftExtendMotor.setPower(-0.7);
        }
        leftExtendMotor.setPower(0);
        rightExtendMotor.setTargetPosition(rightExtendMotor.getCurrentPosition()+(360));
        while(Math.abs(rightExtendMotor.getCurrentPosition()-rightExtendMotor.getTargetPosition()) > 16) {
            rightExtendMotor.setPower(0.7);
        }
        rightExtendMotor.setPower(0);
        spinMotor.setPower(0.4);

    }


    public void recallIntake() {
        rightExtendMotor.setTargetPosition(rightExtendMotor.getCurrentPosition()-(360));
        while(Math.abs(rightExtendMotor.getCurrentPosition()-rightExtendMotor.getTargetPosition()) > 16) {
            rightExtendMotor.setPower(-0.7);
        }
        rightExtendMotor.setPower(0);
        leftExtendMotor.setTargetPosition(leftExtendMotor.getCurrentPosition()+(360));
        while(Math.abs(leftExtendMotor.getCurrentPosition()-leftExtendMotor.getTargetPosition()) > 16) {
            leftExtendMotor.setPower(0.7);
        }
        leftExtendMotor.setPower(0);
        spinMotor.setPower(0);
    }

}
