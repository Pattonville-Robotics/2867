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
        setPosition(0.5);
    }

    /**
     * This method extends the button presser at position 0.
     */
    public void presserExtend(){
        setPosition(0);
    }

    /**
     * This method returns the button presser at position 1.
     */
    public void presserDefault(){
        setPosition(1);
    }

    /**
     * This method creates a new position for the button presser.
     */
    public void setPosition(double position){
        buttonPresser.setPosition(position);
    }

    @Override
    public String toString(){
        return "Servo";
    }


}

