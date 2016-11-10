package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.pattonvillerobotics.commoncode.enums.AllianceColor;
import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.robotclasses.BeaconColorSensor;
import org.pattonvillerobotics.commoncode.robotclasses.drive.EncoderDrive;
import org.pattonvillerobotics.opmodes.CustomRobotParameters;

/**
 * Created by developer on 10/4/16.
 */

public class CommonAutonomous {

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
   /* private int WALL_1_CENTER_TO_TAPE_1_ANGLE    =   37; //Tile #3 -> Tape 1
    private int BALL_TO_TAPE_2_ANGLE             =   53; //Ball -> Tape 2
    private int TAPE_1__TO_STRAIGHT_ANGLE        =   53; //Tile #3 -> Tape 1 Straighten
    private int TAPE_2_TO_BALL_ANGLE             =   37; //Tape 2 -> Ball
    private int WALL_1_CENTER_TO_TAPE_2_ANGLE    =   67; //Tile #3 -> Tape 2*/
    private int TAPE_2_TO_STRAIGHT_ANGLE         =   23; //Tile #3 -> Tape 2 Straighten

    public Direction turnDirection;
    public LineFollowerDrive drive;
    public LineFollowerDrive lineDrive;
    public HardwareMap hardware;
    public BeaconColorSensor beaconColorSensor;
    public ButtonPresser buttonPresser;
    public AllianceColor allianceColor;
    
    public CommonAutonomous(AllianceColor allianceColor, HardwareMap hardware, LinearOpMode linearOpMode, LineFollowerDrive drive){
        this.drive = drive;
        this.hardware = hardware;
        this.allianceColor = allianceColor;
        
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

    /*public void setUp(AllianceColor allianceColor, HardwareMap hardware, EncoderDrive drive){
        setAllianceColor(allianceColor);
        setHardwareMap(hardware);
        setDrive(drive, new LineFollowerDrive(drive.hardwareMap, drive.linearOpMode, CustomRobotParameters.ROBOT_PARAMETERS));
    }

    private void setAllianceColor(AllianceColor allianceColor){
        if(allianceColor == AllianceColor.BLUE){
            turnDirection = Direction.LEFT;
        }else{
            turnDirection = Direction.RIGHT;
        }
    }

    private void setDrive(EncoderDrive encoderDrive, LineFollowerDrive lineFollowerDrive){
        drive = encoderDrive;
        lineDrive = lineFollowerDrive;
    }

    private void setHardwareMap(HardwareMap hardwareMap){
        hardware = hardwareMap;
    }*/

    //Autonomous Modules
    public void wallPos1ToBall() {
        //Forward 60 inches
        //Hit Ball
        drive.moveInches(Direction.FORWARD, 60, 0.3);
       // hitBall();
    }

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
                drive.moveInches(Direction.FORWARD, 1, 0.2);
                pressBeacon();
            }
        });

        drive.moveInches(Direction.FORWARD, 15, 0.3);
    }

    /*public void wallPos1ToBeacon1() {
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

    public void wallPos1ToBeacon1_Blue() {
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

    public void wallPos1ToBeacon1(){
        drive.moveInches(Direction.FORWARD, 6, 0.3);
        drive.rotateDegrees(turnDirection, 45, 0.25);
        drive.moveInches(Direction.FORWARD, 57, 0.3);
        drive.rotateDegrees(turnDirection, 45, 0.25);
        drive.moveInches(Direction.FORWARD, 10, 0.3);
    }

    public void wallToBeacon1WithLine(){
        drive.moveInches(Direction.FORWARD, 6, 0.3);
        drive.rotateDegrees(turnDirection, 55, 0.25);

        drive.driveUntilLine();
        drive.align(turnDirection, 55);

    }
    
    public void wallPos1ToBeacon2() {
        
    }

    public void beacon1ToBeacon2() {
        //24 inches forward
        //RIGHT_ANGLE turn
        //43 inches forward
        //RIGHT_ANGLE turn
        //20 inches forward
        drive.moveInches(Direction.BACKWARD, 20, 0.3);
        drive.rotateDegrees(turnDirection, -RIGHT_ANGLE, 0.25);
        drive.moveInches(Direction.FORWARD, 43, 0.3);
        drive.rotateDegrees(turnDirection, RIGHT_ANGLE, 0.25);
        drive.moveInches(Direction.FORWARD, 20, 0.3);
    }

    public void tape1ToBall() {
        //60 inches back
        drive.moveInches(Direction.BACKWARD, 60, 0.3);
    }
    
    public void tape2ToBall() {
        
    }

}