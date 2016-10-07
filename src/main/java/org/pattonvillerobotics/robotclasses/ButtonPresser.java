package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by wrightk03 on 10/6/16.
 */

public class ButtonPresser {

    public Servo buttonPresser;

    public ButtonPresser(HardwareMap hardwaremap){
        buttonPresser = hardwaremap.servo.get("button_presser");

    }

    public void presserExtend(){
        buttonPresser.setPosition(0);
    }
    public void presserDefault(){
        buttonPresser.setPosition(1);
    }
    public void setPostion(double postiton){
        buttonPresser.setPosition(postiton);
    }

    @Override
    public String toString(){
        return "Servo";
    }


}

