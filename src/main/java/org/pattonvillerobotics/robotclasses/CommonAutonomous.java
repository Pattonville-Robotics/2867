package org.pattonvillerobotics.robotclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.pattonvillerobotics.commoncode.enums.AllianceColor;
import org.pattonvillerobotics.commoncode.enums.Direction;
import org.pattonvillerobotics.commoncode.robotclasses.BeaconColorSensor;
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
 * quickly, and with the assurace that they will work out of the box.
 *
 * The methods in this class work for both the Red and Blue alliances by setting a
 * specific "turnDirection" variable to be either LEFT or RIGHT which correlate to
 * the RED or BLUE alliance, so these methods work for out-of-the-box for either
 * alliance color.
 */
public class CommonAutonomous {

    private static double DRIVE_SPEED = 0.7;            // Motor Power
    private static double TURN_SPEED  = 0.7;           // Motor Power
    private static long WAIT_TIME = 20;                 // Milliseconds

    //Distance Constants (inches)

    private static int START_DISTANCE                   =   6;

    private static int BALL_TO_TAPE_2                   =   60;
    private static double START_POS_1_TO_TAPE_1         =   60;
    private static int TAPE_1_TO_TAPE_2                 =   46;

    private static int BEACON_DISTANCE_BUFFER           =    5;

    //Angle Constants (degrees)
    private static int RIGHT_ANGLE                      =   90; //Right Angle
    private static int WALL_POS_1_TO_BEACON_ANGLE       =   43;
    private static int TAPE_2_TO_BALL_ANGLE             =   53; //Tape 2 -> Ball

    public Direction turnDirection;
    public LineFollowerDrive drive;
    public HardwareMap hardware;
    public BeaconColorSensor beaconColorSensor;
    public ButtonPresser buttonPresser;
    public AllianceColor allianceColor;
    public LinearOpMode linearOpMode;
    public BigWheel bigWheel;
    public GuideRail guideRail;
    public ForkLift lift;

    /**
     * sets up an instance of CommonAutonomous, which establishes connectes to a drive class, and sets the
     * turn direction for the instance. This also sets up a button presser object and a BeaconColorSensor
     * object for use in pressing the beacon buttons.
     *
     * @param allianceColor the alliance color associated with the particular instance
     * @param hardware a hardwaremap to setup the button presser and drive train
     * @param linearOpMode a linearOpMode to allow for testing
     *
     * @see AllianceColor
     * @see LineFollowerDrive
     * @see BeaconColorSensor
     * @see ButtonPresser
     */
    public CommonAutonomous(AllianceColor allianceColor, HardwareMap hardware, LinearOpMode linearOpMode){

        //Set instance variables
        this.allianceColor = allianceColor;
        this.hardware = hardware;
        this.linearOpMode = linearOpMode;
        this.drive = new LineFollowerDrive(hardware, linearOpMode, CustomRobotParameters.ROBOT_PARAMETERS);

        //Set turn direction based on alliance color
        if(allianceColor == AllianceColor.BLUE){
            turnDirection = Direction.LEFT;
        }else{
            turnDirection = Direction.RIGHT;
        }

        //Setup color sensor
        ColorSensor cs = hardware.colorSensor.get("color_sensor");
        beaconColorSensor = new BeaconColorSensor(cs);
        beaconColorSensor.colorSensor.enableLed(false);

        //Setup remaining mechanisms
        buttonPresser = new ButtonPresser(hardware);
        bigWheel = new BigWheel(hardware, linearOpMode);
        guideRail = new GuideRail(hardware, linearOpMode);
        lift = new ForkLift(hardware, linearOpMode);

        /*lift.raiseSlides();
        linearOpMode.sleep(500);*/

    }


    //******************* AUTONOMOUS MODULES *******************\\

    /**
     * Drives robot from wall position 1 to the cap ball and knocks
     * the cap ball to the floor.
     */
    public void wallToBall(){
        drive.moveInches(Direction.BACKWARD, 60, 0.8);
        drive.stop();
    }

    /**
     * Determines the color of the right side of the beacon
     * and sets the button presser to teh correct position
     * and drives forward to press the correct button.
     *
     * @see BeaconColorSensor#determineColor(AllianceColor, Runnable, Runnable, Runnable)
     */
    public void pressBeacon() {

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
                linearOpMode.telemetry.addData("Blue", beaconColorSensor.blue());
                linearOpMode.telemetry.addData("Red", beaconColorSensor.red());
                drive.moveInches(Direction.FORWARD, 3, DRIVE_SPEED);
                pressBeacon();
            }
        });

        drive.moveInches(Direction.FORWARD, 8, DRIVE_SPEED);
        wait_between_move();
    }

    /**
     * Drives robot from wall position 1 to in front of the
     * first beacon
     */
    public void wallPos1ToBeacon1(){
        drive.moveInches(Direction.FORWARD, START_DISTANCE, DRIVE_SPEED);
        drive.stop();
        wait_between_move();

        drive.rotateDegrees(turnDirection, WALL_POS_1_TO_BEACON_ANGLE , TURN_SPEED);
        drive.stop();
        wait_between_move();

        guideRail.setPosition(0.3);
        drive.moveInches(Direction.FORWARD, START_POS_1_TO_TAPE_1, DRIVE_SPEED);
        drive.stop();
        wait_between_move();

        drive.rotateDegrees(turnDirection, RIGHT_ANGLE-START_POS_1_TO_TAPE_1+10, TURN_SPEED);
        drive.stop();
        wait_between_move();

        //driveToBeacon();
        /*drive.moveInches(Direction.FORWARD, 10, SPEED);
        drive.stop();
        wait_between_move();*/

    }

    /**
     * Drives robot from wall position1 to in front of the
     * first beacon using the ODS sensor to track the
     * white line.
     *
     * @see LineFollowerDrive#driveUntilLine(double)
     * @see LineFollowerDrive#align(Direction, double)
     */
    public void wallToBeacon1WithLine(){
        drive.moveInches(Direction.FORWARD, START_DISTANCE, DRIVE_SPEED);
        wait_between_move();

        drive.rotateDegrees(turnDirection, 50, TURN_SPEED);
        wait_between_move();

        drive.driveUntilLine(DRIVE_SPEED);
        wait_between_move();

        drive.align(turnDirection, 50);
        wait_between_move();

        driveToBeacon();

    }


    private void backUpFromBeacon1(){
        //MOVE TO END OF WHITE TAPE LINE
        drive.moveInches(Direction.BACKWARD, 8, DRIVE_SPEED);
        drive.stop();
        wait_between_move();

        //FIRE LOADED PARTICLE
        bigWheel.fire();

        //TURN TOWARDS SECOND BEACON

        double angle = -RIGHT_ANGLE + 5;

        drive.rotateDegrees(turnDirection, angle, TURN_SPEED);
        wait_between_move();

        drive.stop();
        wait_between_move();

        //RESET BUTTON PRESSER OUT OF WAY OF COLOR SENSOR
        buttonPresser.setPosition(0.5);

    }

    /**
     * Drives robot from in front of the first beacon to in
     * front of the second beacon
     */
    public void beacon1ToBeacon2() {

        backUpFromBeacon1();

        //DRIVE TO WHITE TAPE LINE
        drive.moveInches(Direction.FORWARD, TAPE_1_TO_TAPE_2, DRIVE_SPEED);
        wait_between_move();

        drive.rotateDegrees(turnDirection, RIGHT_ANGLE - 2, TURN_SPEED);
        wait_between_move();

        driveToBeacon();
        /*drive.moveInches(Direction.FORWARD, 3, SPEED);
        wait_between_move();*/
    }

    public void beacon1ToBeacon2WithLine(){
        backUpFromBeacon1();

        drive.driveUntilLine(DRIVE_SPEED);
        wait_between_move();

        drive.align(turnDirection, 0);
        wait_between_move();

        driveToBeacon();
        /*drive.moveInches(Direction.FORWARD, 3, SPEED);
        wait_between_move();*/
    }

    /**
     * Drives robot from the second beacon to the cap ball and
     * pushes the cap ball to the floor.
     */
    public void tape2ToBall() {
        drive.moveInches(Direction.BACKWARD, 10, 1.0);
        wait_between_move();

        if(turnDirection == Direction.RIGHT){
            turnDirection = Direction.LEFT;
        }else{
            turnDirection = Direction.RIGHT;
        }
        drive.rotateDegrees(turnDirection, TAPE_2_TO_BALL_ANGLE, 0.25);
        wait_between_move();

        drive.moveInches(Direction.BACKWARD, BALL_TO_TAPE_2, 1.0);
        wait_between_move();
    }

    /**
     * An autonomous routine to run during judging to show off our
     * robot's abilities to the judges.
     */
    public void judgeCode () {

        drive.moveInches(Direction.FORWARD, 5, 1.0);
        drive.moveInches(Direction.BACKWARD, 5, 1.0);
        drive.rotateDegrees(Direction.LEFT, 45, 1.0);
        drive.rotateDegrees(Direction.RIGHT, 45, 1.0);
        drive.stop();
        linearOpMode.sleep(1000);

        buttonPresser.presserLeft();
        buttonPresser.presserRight();
        linearOpMode.sleep(1000);

        guideRail.setPosition(0.8);
        bigWheel.fire();
        linearOpMode.sleep(300);

    }

    public void fireParticle(){
        drive.moveInches(Direction.BACKWARD, 10, 1.0);
        guideRail.setPosition(0.2);

        linearOpMode.sleep(1000);

        bigWheel.fire();

        drive.moveInches(Direction.BACKWARD, 45, 1.0);

    }



    private void wait_between_move(){
        linearOpMode.sleep(WAIT_TIME);
    }

    private void driveToBeacon(){
        drive.moveInches(Direction.FORWARD, 5, DRIVE_SPEED);
        drive.stop();
        wait_between_move();
    }

}
