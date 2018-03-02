package org.pattonvillerobotics.robotclasses.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.pattonvillerobotics.robotclasses.enums.ClawPosition;

/**
 * Created by skaggsw on 3/1/18.
 */

public class XWing extends AbstractMechanism {

    public Servo topClawLeftServo, bottomClawLeftServo, topClawRightServo, bottomClawRightServo, spinner;

    private ClawPosition topClawPosition;
    private ClawPosition bottomClawPosition;

    public XWing(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        super(hardwareMap,linearOpMode);

        topClawLeftServo       = hardwareMap.servo.get("claw1");
        topClawRightServo      = hardwareMap.servo.get("claw3");

        bottomClawLeftServo    = hardwareMap.servo.get("claw2");
        bottomClawRightServo   = hardwareMap.servo.get("claw4");

        spinner                = hardwareMap.servo.get("spinner");

        topClawPosition        = ClawPosition.OPEN;
        bottomClawPosition     = ClawPosition.OPEN;

        topClawOpen();
        bottomClawOpen();
    }

    //claw1 aka topClawLeftServo       closed at 0.5   open at 0
    //claw2 aka bottomClawLeftServo    closed at 0     open at 0.7
    //claw3 aka topClawRightServo      closed at 0     open at 0.6
    //claw4 aka bottomClawRightServo   closed at 0.65  open at 0

    public void topClawClose() {
        topClawPosition = ClawPosition.CLOSE;
        topClawLeftServo. setPosition(0.5);
        topClawRightServo.setPosition(0);
    }

    public void topClawOpen() {
        topClawPosition = ClawPosition.OPEN;
        topClawLeftServo. setPosition(0);
        topClawRightServo.setPosition(0.6);
    }

    public void topClawHalf() {
        topClawPosition = ClawPosition.HALF;
        topClawLeftServo. setPosition(0.25);
        topClawRightServo.setPosition(0.3);
    }

    public void bottomClawClose() {
        bottomClawPosition = ClawPosition.CLOSE;
        bottomClawLeftServo. setPosition(0);
        bottomClawRightServo.setPosition(0.65);
    }

    public void bottomClawOpen() {
        bottomClawPosition = ClawPosition.OPEN;
        bottomClawLeftServo. setPosition(0.7);
        bottomClawRightServo.setPosition(0);
    }

    public void bottomClawHalf() {
        bottomClawPosition = ClawPosition.HALF;
        bottomClawLeftServo. setPosition(0.35);
        bottomClawRightServo.setPosition(0.3);
    }

    public void setTopClawPosition(double position) {
        topClawLeftServo. setPosition(position);
        topClawRightServo.setPosition(position);
    }

    public void setBottomClawPosition(double position) {
        bottomClawLeftServo. setPosition(position);
        bottomClawRightServo.setPosition(position);
    }

    public ClawPosition getTopClawPosition() {
        return topClawPosition;
    }

    public ClawPosition getBottomClawPosition() {
        return bottomClawPosition;
    }

    public void toggleTopClawPosition() {
        switch (topClawPosition) {
            case HALF:
                topClawClose();
                topClawPosition = ClawPosition.CLOSE;
                break;
            case OPEN:
                topClawHalf();
                topClawPosition = ClawPosition.HALF;
                break;
            case CLOSE:
                topClawOpen();
                topClawPosition = ClawPosition.OPEN;
                break;
            default:
                topClawOpen();
                topClawPosition = ClawPosition.OPEN;
                break;
        }
    }

    public void toggleBottomClawPosition() {
        switch (bottomClawPosition) {
            case HALF:
                bottomClawClose();
                bottomClawPosition = ClawPosition.CLOSE;
                break;
            case OPEN:
                bottomClawHalf();
                bottomClawPosition = ClawPosition.HALF;
                break;
            case CLOSE:
                bottomClawOpen();
                bottomClawPosition = ClawPosition.OPEN;
                break;
            default:
                bottomClawOpen();
                bottomClawPosition = ClawPosition.OPEN;
                break;
        }
    }
}
