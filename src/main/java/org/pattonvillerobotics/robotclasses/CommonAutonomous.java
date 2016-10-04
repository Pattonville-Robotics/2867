package org.pattonvillerobotics.robotclasses;

/**
 * Created by developer on 10/4/16.
 */

public class CommonAutonomous {

    private static int WALL_TO_BALL_DISTANCE = 60;
    private static int WALL_TO_TILE3_CENTER = 12;
    private static int BALL_TO_TAPE2 = 60;
    private static int TAPE_TO_BEACON = 20;
    private static int TAPE1_TO_TAPE2 = 50;
    private static int TILE3_CENTER_TO_TAPE = 60;
    private static int BEACONCOLOR_TO_BEACONBUTTON = 5;

    //Angle Constants
    private static int FIRST_TURN = 53;
    private static int SECOND_TURN = 37;
    private static int THIRD_TURN = 90;
    private static int FOURTH_TURN = 67;
    private static int FIFTH_ANYMORE = 23;

    public static void hitBall() {
        //Forward 60 inches
        //Hit Ball
    }

    public static void hitBeacon() {
        //read beacon color
        //rotate pressed mech
    }

    public static void tile3ToBeacon1() {
        //12 inches forward
        //53 degrees right
        //60 forward
        //37 right
        //20 forward
    }

    public static void beacon1ToBeacon2() {
        //24 inches forward
        //90 left
        //43 forward
        //90 right
        //20 inches forward
    }

    public static void backHitBall() {
        //60 inches back
    }

}
