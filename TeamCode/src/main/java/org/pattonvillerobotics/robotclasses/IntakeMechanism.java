package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.annotations.ServoType;

public class IntakeMechanism extends AbstractMechanism {

    public DcMotor pivotMotor;
    public CRServo tubeServo, extendServo, bucketServo;

    public IntakeMechanism(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        super(hardwareMap,linearOpMode);

        pivotMotor = hardwareMap.dcMotor.get("pivot_motor");
        pivotMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        tubeServo = hardwareMap.crservo.get("tube_servo");

        bucketServo = hardwareMap.crservo.get("bucket_servo");

        extendServo = hardwareMap.crservo.get("extend_servo");
    }

    public void setPivotMotorPower(double power) {
        pivotMotor.setPower(power);
    }

    public void setExtendServoPower(double power) {
        extendServo.setPower(power);
    }

    public void setBucketServoPower(double power) {
        bucketServo.setPower(power);
    }

    public void setTubeServoPower(double power) {
        tubeServo.setPower(power);
    }
}
