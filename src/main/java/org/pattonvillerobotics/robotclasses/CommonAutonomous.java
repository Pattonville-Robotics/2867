package org.pattonvillerobotics.robotclasses;

import org.pattonvillerobotics.commoncode.enums.AllianceColor;
import org.pattonvillerobotics.commoncode.enums.Direction;

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
    private static int Wall_1_CENTER_TO_TAPE_1_ANGLE    =   53; //Tile #3 -> Tape 1
    private static int BALL_TO_TAPE_2_ANGLE             =   53; //Ball -> Tape 2
    private static int TAPE_1__TO_STRAIGHT_ANGLE        =   37; //Tile #3 -> Tape 1 Straighten
    private static int TAPE_2_TO_BALL_ANGLE             =   37; //Tape 2 -> Ball
    private static int WALL_1_CENTER_TO_TAPE_2_ANGLE    =   67; //Tile #3 -> Tape 2
    private static int TAPE_2_TO_STRAIGHT_ANGLE         =   23; //Tile #3 -> Tape 2 Straighten

    public static Direction turnDirection;

    public static void setAllianceColor(AllianceColor allianceColor){
        if(allianceColor == AllianceColor.BLUE){
            turnDirection = Direction.RIGHT;
        }else{
            turnDirection = Direction.LEFT;
        }
    }

    //Autonomous Modules
    public static void wallPos1ToBall() {
        //Forward 60 inches
        //Hit Ball
    }

    public static void pressBeacon() {
        //20 inches forward
        //read beacon color
        //rotate pressed mech
        //4 inches forward
    }

    public static void wallPos1ToBeacon1() {
        //12 inches forward
        //53 degrees turn
        //60 inches forward
        //37 turn
        //20 inches forward
    }
    
    public static void wallPos1ToBeacon2() {
        
    }

    public static void beacon1ToBeacon2() {
        //24 inches forward
        //90 turn
        //43 inches forward
        //90 turn
        //20 inches forward
    }

    public static void tape1ToBall() {
        //60 inches back
    }
    
    public static void tape2ToBall() {
        
    }

}
