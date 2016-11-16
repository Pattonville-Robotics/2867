package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.apache.commons.math3.geometry.euclidean.threed.Line;
import org.pattonvillerobotics.commoncode.enums.AllianceColor;
import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.robotclasses.BeaconColorSensor;
import org.pattonvillerobotics.commoncode.robotclasses.drive.EncoderDrive;
import org.pattonvillerobotics.opmodes.CustomRobotParameters;

/**
 * Created by developer on 10/4/16.
 */

/**
 * CommonAutonomous contains a number of different modules for us to utilize
 * during the autonomous phase of competition. These modules are broken into
 * separate methods that can be called inside an opmode in any order so as to
 * achieve any number of desirable autonomous paths. This structure allows for
 * our team to quickly modify and create new autonomous's for different situations
 * quickly, and with the assurace that they will work our of the box.
 *
 * The methods in this class work for both the Red and Blue alliances by setting a
 * specific "turnDirection" variable to be either LEFT or RIGHT which correlate to
 * the RED or BLUE alliance, so these methods work for out-of-the-box for either
 * alliance color.
 */
public class CommonAutonomous {

    private static double SPEED = 0.4;

    //Distance Constants (inches)
    private int ONE_TILE                         =   24;
    private int ROBOT_LENGTH                     =   18;

    private int WALL_1_TO_BALL_DISTANCE          =   60;
    private int WALL_1_TO_TILE_3_CENTER          =   12;
    private int BALL_TO_TAPE_2                   =   60;
    private int TILE_3_CENTER_TO_TAPE_1          =   60;
    private int TILE_3_CENTER_TO_TAPE_2          =   92;
    private int TAPE_1_TO_TAPE_2                 =   48;
    private int TAPE_TO_BEACON                   =   24;

    private int BEACON_DISTANCE_BUFFER           =    5;
    private int BALL_DISPLACEMENT_BUFFER         =    2;

    //Angle Constants (degrees)
    private int RIGHT_ANGLE                      =   90; //Right Angle
    private int WALL_POS_1_TO_BEACON_ANGLE       =   45;
    /*private int WALL_1_CENTER_TO_TAPE_1_ANGLE    =   37; //Tile #3 -> Tape 1
    private int BALL_TO_TAPE_2_ANGLE             =   53; //Ball -> Tape 2
    private int TAPE_1__TO_STRAIGHT_ANGLE        =   53; //Tile #3 -> Tape 1 Straighten
    private int TAPE_2_TO_BALL_ANGLE             =   37; //Tape 2 -> Ball
    private int WALL_1_CENTER_TO_TAPE_2_ANGLE    =   67; //Tile #3 -> Tape 2*/
    private int TAPE_2_TO_STRAIGHT_ANGLE         =   23; //Tile #3 -> Tape 2 Straighten

    public Direction turnDirection;
    public LineFollowerDrive drive;
    public HardwareMap hardware;
    public BeaconColorSensor beaconColorSensor;
    public ButtonPresser buttonPresser;
    public AllianceColor allianceColor;
    public LinearOpMode linearOpMode;

    /**
     * sets up an instance of CommonAutonomous, which establishes connectes to a drive class, and sets the
     * turn direction for the instance. This also sets up a button presser object and a BeaconColorSensor
     * object for use in pressing the beacon buttons.
     *
     * @param allianceColor the alliance color associated with the particular instance
     * @param hardware a hardwaremap to setup the button presser and drive train
     * @param linearOpMode a linearOpMode to allow for testing
     * @param drive a drive object to move with
     *
     * @see AllianceColor
     * @see LineFollowerDrive
     * @see BeaconColorSensor
     * @see ButtonPresser
     */
    public CommonAutonomous(AllianceColor allianceColor, HardwareMap hardware, LinearOpMode linearOpMode, LineFollowerDrive drive){
        this.drive = drive;
        this.hardware = hardware;
        this.allianceColor = allianceColor;
        this.linearOpMode = linearOpMode;
        
        if(allianceColor == AllianceColor.BLUE){
            turnDirection = Direction.LEFT;
        }else{
            turnDirection = Direction.RIGHT;
        }

        ColorSensor cs = hardware.colorSensor.get("color_sensor");
        beaconColorSensor = new BeaconColorSensor(cs);
        beaconColorSensor.colorSensor.enableLed(false);

        buttonPresser = new ButtonPresser(hardware);

        buttonPresser.setPosition(0.5);
        
    }


    //Autonomous Modules

    /**
     * Drives robot from wall position 1 to the cap ball and knocks
     * the cap ball to the floor.
     */
    public void wallPos1ToBall() {
        //Forward 60 inches
        //Hit Ball
        drive.moveInches(Direction.FORWARD, 35, 0.8);
        drive.rotateDegrees(turnDirection, 45, 0.5);
        drive.moveInches(Direction.FORWARD, 15, 0.8);
       // hitBall();
    }

    /**
     * Determines the color of the right side of the beacon
     * and sets drives forward to press the correct button.
     *
     * @see BeaconColorSensor#determineColor(AllianceColor, Runnable, Runnable, Runnable)
     */
    public void pressBeacon() {
        //20 inches forward
        //read beacon color
        //rotate pressed mech
        //4 inches forward

        beaconColorSensor.determineColor(allianceColor, new Runnable() {
            @Override
            public void run() {
                buttonPresser.presserRight();
            }
        }, new Runnable() {
            @Override
            public void run() {
                buttonPresser.presserLeft();
            }
        }, new Runnable() {
            @Override
            public void run() {
                drive.moveInches(Direction.FORWARD, 3, 0.2);
                pressBeacon();
            }
        });

        drive.moveInches(Direction.FORWARD, 10, 0.3);
    }

    /**
     * Drives robot from wall position 1 to in front of the
     * first beacon.
     */
    public void wallPos1ToBeacon1(){
        drive.moveInches(Direction.FORWARD, 6, SPEED);
        buttonPresser.setPosition(0.5);
        drive.rotateDegrees(turnDirection, WALL_POS_1_TO_BEACON_ANGLE , 0.25);
        drive.moveInches(Direction.FORWARD, 56, SPEED);
        drive.rotateDegrees(turnDirection, WALL_POS_1_TO_BEACON_ANGLE , 0.25);
        drive.moveInches(Direction.FORWARD, 10, SPEED);
    }

    /**
     * Drives robot from wall position1 to in front of the
     * first beacon using the ODS sensor to track the
     * white line.
     *
     * @see LineFollowerDrive#driveUntilLine()
     * @see LineFollowerDrive#align(Direction, double)
     */
    public void wallToBeacon1WithLine(){
        drive.moveInches(Direction.FORWARD, 6, 0.3);
        drive.rotateDegrees(turnDirection, 55, 0.25);

        drive.driveUntilLine();
        drive.align(turnDirection, 55);

    }

    /**
     * Drives robot from wall position 1 to in front of the
     * second beacon
     */
    public void wallPos1ToBeacon2() {
        
    }

    /**
     * Drives robot from in front of the first beacon to in
     * front of the second beacon
     */
    public void beacon1ToBeacon2() {
        //24 inches forward
        //RIGHT_ANGLE turn
        //43 inches forward
        //RIGHT_ANGLE turn
        //20 inches forward
        drive.moveInches(Direction.BACKWARD, 20, SPEED);
        drive.rotateDegrees(turnDirection, -RIGHT_ANGLE - 3, 0.25);
        drive.moveInches(Direction.FORWARD, 48, SPEED);
        drive.rotateDegrees(turnDirection, RIGHT_ANGLE, 0.25);
        drive.moveInches(Direction.FORWARD, 15, SPEED);
    }

    /**
     * Drives robot from the first beacon to the cap ball and
     * pushes the cap ball to the floor
     */
    public void tape1ToBall() {
        //60 inches back
        drive.moveInches(Direction.BACKWARD, 55, 0.3);
    }

    /**
     * Drives robot from the second beacon to the cap ball and
     * pushed the cap ball to the floor.
     */
    public void tape2ToBall() {
        drive.moveInches(Direction.BACKWARD, 10, 0.3);
        if(turnDirection == Direction.RIGHT){
            turnDirection = Direction.LEFT;
        }else{
            turnDirection = Direction.RIGHT;
        }
        drive.rotateDegrees(turnDirection, 45, 0.25);
        drive.moveInches(Direction.BACKWARD, 65, 0.8);
    }

}