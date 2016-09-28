package org.pattonvillerobotics;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.pattonvillerobotics.commoncode.enums.AllianceColor;
import org.pattonvillerobotics.commoncode.robotclasses.BeaconColorSensor;

/**
 * Created by developer on 9/27/16.
 */

@TeleOp(name = "Full Color Sensor Test", group = "Common")
public class FullColorSensorTest extends LinearOpMode {

    Servo buttonPresser;
    BeaconColorSensor colorSensor;
    AllianceColor allianceColor;

    boolean aCurrState, aPrevState;

    @Override
    public void runOpMode() throws InterruptedException {

        initalize();
        waitForStart();

        while(opModeIsActive()){

            aCurrState = gamepad1.a;

            if(aCurrState && (aCurrState != aPrevState))  {
                // button is transitioning to a pressed state. So Toggle LED
                if(allianceColor == AllianceColor.BLUE){
                    allianceColor = AllianceColor.RED;
                }else{
                    allianceColor = AllianceColor.BLUE;
                }
            }

            aPrevState = aCurrState;

            colorSensor.determineColor(allianceColor, new Runnable() {
                @Override
                public void run() {
                    telemetry.addData("Servo", "Moving to Position 1");
                }
            }, new Runnable() {
                @Override
                public void run() {
                    telemetry.addData("Servo", "Moving to Position 0");
                }
            }, new Runnable(){
                @Override
                public void run() {
                    telemetry.addData("NONE", " ");
                }
            });

            telemetry.addData("AllianceColor", allianceColor);
            telemetry.update();
            idle();

        }

    }


    public void initalize(){
        ColorSensor cs = hardwareMap.colorSensor.get("color sensor");

        buttonPresser = hardwareMap.servo.get("servo");
        buttonPresser.setPosition(0.5);

        colorSensor = new BeaconColorSensor(cs);
        allianceColor = AllianceColor.BLUE;
    }

}
