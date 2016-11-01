package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.pattonvillerobotics.commoncode.enums.AllianceColor;
import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.robotclasses.BeaconColorSensor;
import org.pattonvillerobotics.commoncode.robotclasses.drive.EncoderDrive;

/**
 * Created by developer on 10/4/16.
 */

public class CommonAutonomous {

    //Distance Constants (inches)
    private static int ONE_TILE                         =   24;
    private static int ROBOT_LENGTH                     =   18;

    private static int WALL_1_TO_BALL_DISTANCE          =   60;
    private static int WALL_1_TO_TILE_3_CENTER          =   12;
    private static int BALL_TO_TAPE_2                   =   60;
    private static int TILE_3_CENTER_TO_TAPE_1          =   60;
    private static int TILE_3_CENTER_TO_TAPE_2          =   92;
    private static int TAPE_1_TO_TAPE_2                 =   48;
    private static int TAPE_TO_BEACON                   =   24;

    private static int BEACON_DISTANCE_BUFFER           =    5;
    private static int BALL_DISPLACEMENT_BUFFER         =    2;

    //Angle Constants (degrees)
    private static int RIGHT_ANGLE                      =   90; //Right Angle
    private static int WALL_POS_1_TO_BEACON_ANGLE       =   45;
   /* private static int WALL_1_CENTER_TO_TAPE_1_ANGLE    =   37; //Tile #3 -> Tape 1
    private static int BALL_TO_TAPE_2_ANGLE             =   53; //Ball -> Tape 2
    private static int TAPE_1__TO_STRAIGHT_ANGLE        =   53; //Tile #3 -> Tape 1 Straighten
    private static int TAPE_2_TO_BALL_ANGLE             =   37; //Tape 2 -> Ball
    private static int WALL_1_CENTER_TO_TAPE_2_ANGLE    =   67; //Tile #3 -> Tape 2*/
    private static int TAPE_2_TO_STRAIGHT_ANGLE         =   23; //Tile #3 -> Tape 2 Straighten

    public static Direction turnDirection;
    public static EncoderDrive drive;
    public static HardwareMap hardware;

    public static void setUp(AllianceColor allianceColor, HardwareMap hardware, EncoderDrive drive){
        setAllianceColor(allianceColor);
        setHardwareMap(hardware);
        setDrive(drive);
    }

    private static void setAllianceColor(AllianceColor allianceColor){
        if(allianceColor == AllianceColor.BLUE){
            turnDirection = Direction.RIGHT;
        }else{
            turnDirection = Direction.LEFT;
        }
    }

    private static void setDrive(EncoderDrive encoderDrive){
        drive = encoderDrive;
    }

    private static void setHardwareMap(HardwareMap hardwareMap){
        hardware = hardwareMap;
    }

    //Autonomous Modules
    public static void wallPos1ToBall() {
        //Forward 60 inches
        //Hit Ball
        drive.moveInches(Direction.FORWARD, 60, 0.3);
       // hitBall();
    }

    public static void pressBeacon() {
        //20 inches forward
        //read beacon color
        //rotate pressed mech
        //4 inches forward

        BeaconColorSensor beaconColorSensor;
        final ButtonPresser buttonPresser;

        ColorSensor cs = hardware.colorSensor.get("color_sensor");
        beaconColorSensor = new BeaconColorSensor(cs);
        buttonPresser = new ButtonPresser(hardware);

        drive.moveInches(Direction.FORWARD, 20, 0.3);

        beaconColorSensor.determineColor(AllianceColor.BLUE, new Runnable() {
            @Override
            public void run() {
                buttonPresser.presserLeft();
            }
        }, new Runnable() {
            @Override
            public void run() {
                buttonPresser.presserRight();
            }
        }, new Runnable() {
            @Override
            public void run() {
                drive.moveInches(Direction.FORWARD, 1, 0.2);
                pressBeacon();
            }
        });
    }

    /*public static void wallPos1ToBeacon1() {
        //12 inches forward
        //53 degrees turn
        //60 inches forward
        //37 turn
        //20 inches forward
        drive.moveInches(Direction.FORWARD, 12, 0.3);
        drive.rotateDegrees(turnDirection,  WALL_1_CENTER_TO_TAPE_1_ANGLE, 0.25);
        drive.moveInches(Direction.FORWARD, 42, 0.3);
        drive.rotateDegrees(turnDirection,  TAPE_1__TO_STRAIGHT_ANGLE, 0.25);
        drive.moveInches(Direction.FORWARD, 28, 0.3);
    }

    public static void wallPos1ToBeacon1_Blue() {
        //12 inches forward
        //53 degrees turn
        //60 inches forward
        //37 turn
        //20 inches forward
        drive.moveInches(Direction.FORWARD, 6, 0.3);
        drive.rotateDegrees(turnDirection,  WALL_1_CENTER_TO_TAPE_1_ANGLE, 0.25);
        drive.moveInches(Direction.FORWARD, 49, 0.3);
        drive.rotateDegrees(turnDirection,  TAPE_1__TO_STRAIGHT_ANGLE + 3, 0.25);
        drive.moveInches(Direction.FORWARD, 23, 0.3);
    }*/

    public static void wallPos1ToBeacon1(){
        drive.moveInches(Direction.FORWARD, 6, 0.3);
        drive.rotateDegrees(turnDirection, 45, 0.25);
        drive.moveInches(Direction.FORWARD, 51, 0.3);
        drive.rotateDegrees(turnDirection, 45, 0.25);
        drive.moveInches(Direction.FORWARD, 20, 0.3);
    }
    
    public static void wallPos1ToBeacon2() {
        
    }

    public static void beacon1ToBeacon2() {
        //24 inches forward
        //RIGHT_ANGLE turn
        //43 inches forward
        //RIGHT_ANGLE turn
        //20 inches forward
        drive.moveInches(Direction.FORWARD, 24, 0.3);
        drive.rotateDegrees(turnDirection, RIGHT_ANGLE, 0.25);
        drive.moveInches(Direction.FORWARD, 43, 0.3);
        drive.rotateDegrees(turnDirection, RIGHT_ANGLE, 0.25);
        drive.moveInches(Direction.FORWARD, 20, 0.3);
    }

    public static void tape1ToBall() {
        //60 inches back
        drive.moveInches(Direction.BACKWARD, 60, 0.3);
    }
    
    public static void tape2ToBall() {
        
    }

}