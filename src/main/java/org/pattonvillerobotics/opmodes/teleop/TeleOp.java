package org.pattonvillerobotics.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.pattonvillerobotics.commoncode.robotclasses.drive.SimpleDrive;
import org.pattonvillerobotics.robotclasses.ButtonPresser;

/**
 * Created by developer on 10/11/16.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "Teleop")
public class TeleOp extends LinearOpMode {

    private SimpleDrive drive;
    private ButtonPresser buttonPresser;

    @Override
    public void runOpMode() throws InterruptedException{
        initialize();
        waitForStart();

        while (opModeIsActive()){
            doLoop();
        }
    }

    public void initialize(){
        drive = new SimpleDrive(this, hardwareMap);
        buttonPresser = new ButtonPresser(hardwareMap);
        //drive.leftDriveMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        //drive.rightDriveMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        gamepad1.left_stick_y = 0;
        gamepad1.right_stick_y = 0;

        buttonPresser.setPosition(0);
    }

    public void doLoop(){
        drive.moveFreely(-gamepad1.left_stick_y, -gamepad1.right_stick_y);

        if(gamepad1.x){
            buttonPresser.presserExtend();
        } else if(gamepad1.b){
            buttonPresser.presserDefault();
        }
    }
}
