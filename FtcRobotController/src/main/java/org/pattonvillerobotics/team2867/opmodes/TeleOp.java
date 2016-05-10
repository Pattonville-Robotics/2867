package org.pattonvillerobotics.team2867.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.pattonvillerobotics.commoncode.OpMode;
import org.pattonvillerobotics.team2867.robotclasses.Clamp;
import org.pattonvillerobotics.team2867.robotclasses.ClimberBucket;
import org.pattonvillerobotics.team2867.robotclasses.DebrisBucket;
import org.pattonvillerobotics.team2867.robotclasses.Drive;
import org.pattonvillerobotics.team2867.robotclasses.Hopper;
import org.pattonvillerobotics.team2867.robotclasses.SlideSystem;
import org.pattonvillerobotics.team2867.robotclasses.ZipLineTrigger;

/**
 * Created by Joshua Zahner on 9/6/15.
 */
@OpMode("TeleOp")
public class TeleOp extends LinearOpMode {

    //private boolean dPadLeftPressed, dPadRightPressed, dPadTopPressed, dPadBottomPressed;
    private boolean aButtonPressed, bButtonPressed, xButtonPressed, yButtonPressed;
    private boolean climberBucketEngaged, slackCollected, debrisBucketEngaged, zltEngaged;

    //private HardwareMap hardwareMap;

    private Drive drive;
    private ClimberBucket climberBucket;
    private Clamp clamp;
    private Hopper hopper;
    private SlideSystem slideSystem;
    private DebrisBucket debrisBucket;
    private ZipLineTrigger zlt;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();

        while (opModeIsActive()) {
            doLoop();
        }

    }

    public void initialize() {

        drive = new Drive(hardwareMap, this);
        climberBucket = new ClimberBucket(hardwareMap);
        clamp = new Clamp(hardwareMap);
        hopper = new Hopper(hardwareMap);
        slideSystem = new SlideSystem(hardwareMap, this);
        debrisBucket = new DebrisBucket(hardwareMap);
        zlt = new ZipLineTrigger(hardwareMap);

        //speaker = new Speaker(, R.raw.TheStarSpangledBanner_BandOnly);

        telemetry.addData("Init", "Just ran init");

        /*

            // ----------- INITIALIZE ALL VALUES AND DIRECTIONS IN HERE  ------------ \\
            |                                                                         |
            |                                                                         |
            |                                                                         |
            |                                                                         |
            |                                                                         |
            |                                                                         |
            |                                                                         |
            |                                                                         |
            // ---------------------------------------------------------------------- \\

        */

        gamepad1.left_stick_y = 0;
        gamepad1.right_stick_y = 0;

        telemetry.addData("Init", "Initialization Complete");

    }

    public void doLoop() {

        drive.moveFreely(-gamepad1.left_stick_y, -gamepad1.right_stick_y);

        if (gamepad1.a) {
            if (!aButtonPressed) {
                if (!slackCollected) {
                    slideSystem.pickUpSlack();
                    slackCollected = true;
                } else {
                    slideSystem.stop();
                    slackCollected = false;
                }
            }
            aButtonPressed = true;
        } else if (gamepad1.y) {
            if (!yButtonPressed) {
                if (!climberBucketEngaged) {
                    climberBucket.dumpClimbers();
                    climberBucketEngaged = true;
                } else {
                    climberBucket.returnToStart();
                    climberBucketEngaged = false;
                }
            }
            yButtonPressed = true;
        } else if (gamepad1.b) {
            if (!bButtonPressed) {
                if (!debrisBucketEngaged) {
                    debrisBucket.dumpDebris();
                    debrisBucketEngaged = true;
                } else {
                    debrisBucket.returnToStart();
                    debrisBucketEngaged = false;
                }
            }
            bButtonPressed = true;
        } else if (gamepad1.x) {
            if (!xButtonPressed) {
                if (!zltEngaged) {
                    zlt.trigger();
                    zltEngaged = true;
                } else {
                    zlt.returnToStart();
                    zltEngaged = false;
                }
            }
            xButtonPressed = true;
        } else {
            aButtonPressed = false;
            bButtonPressed = false;
            xButtonPressed = false;
            yButtonPressed = false;
        }


        // Below are the controls for all d-pad values: up, right, left, and down
        /*if(gamepad1.dpad_up){       // The up d-pad value sets linear slides to the high position
            slideSystem.goToPosition(Globals.ScoringBucket.HIGH);
        }else if(gamepad1.dpad_right){   // The right d-pad value sets linear slides to the start/default position
            slideSystem.goToPosition(Globals.ScoringBucket.START);
        }else if(gamepad1.dpad_down){    // The down d-pad value sets linear slides to the low position
             slideSystem.goToPosition(Globals.ScoringBucket.LOW);
        }else if(gamepad1.dpad_left){    // The left d-pad value sets linear slides to the middle position
             slideSystem.goToPosition(Globals.ScoringBucket.MIDDLE);
        }*/

        if (gamepad1.left_bumper) {        // While the left bumper (L1) is held down, expel debris from the hopper
            hopper.expel();
        } else if (gamepad1.right_bumper) {   // While the right bumper (R1) is held down, collect debris into the hopper
            hopper.collect();
        } else {
            hopper.stop();
        }

        if (gamepad1.right_trigger > 0.5) {
            slideSystem.incrementPosition();
        } else if (gamepad1.left_trigger > 0.5) {
            slideSystem.decrementPosition();
        } else {
            slideSystem.stop();
        }


        if (gamepad1.start) {
            kill();
        }

    }

    public void kill() {

        initialize();

        aButtonPressed = false;
        bButtonPressed = false;
        xButtonPressed = false;
        yButtonPressed = false;

    }


}
