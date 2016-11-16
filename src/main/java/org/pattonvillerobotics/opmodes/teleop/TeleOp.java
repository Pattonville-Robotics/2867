package org.pattonvillerobotics.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.pattonvillerobotics.commoncode.robotclasses.drive.SimpleDrive;
import org.pattonvillerobotics.robotclasses.BigWheel;
import org.pattonvillerobotics.robotclasses.ButtonPresser;
import org.pattonvillerobotics.robotclasses.GuideRail;

/**
 * Created by Joshua Zahner on 10/11/16.
 *<p>
 * The TeleOp Class gives the driver control over the robot.
 * <p>
 * The driver can also use the following controls on the
 * gamepad: A, B, X, Y, right trigger, left trigger,
 * start, up d-pad, down d-pad, left stick, and right stick.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "Teleop")
public class TeleOp extends LinearOpMode {

    private SimpleDrive drive;
    private ButtonPresser buttonPresser;
    private BigWheel wheel;
    private GuideRail guideRail;

    private boolean turboMode;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();

        while (opModeIsActive()) {
            doLoop();
        }
    }

    /**
     * This method initializes the robot and defines what drive is,
     * defines what the button presser is, defines the motor directions,
     * and defines the gamepad controls that are sticky.
     */
    public void initialize() {
        drive = new SimpleDrive(this, hardwareMap);

        buttonPresser = new ButtonPresser(hardwareMap);

        wheel = new BigWheel(hardwareMap, this);

        guideRail = new GuideRail(hardwareMap, this);

        gamepad1.left_stick_y = 0;
        gamepad1.right_stick_y = 0;

    }

    /**
     * This method allows the driver to control the robot with the gamepad.
     *<p>
     * If the driver presses x, the button presser will extend. If the driver
     * presses b, the button presser will return to the default position.
     * Using the left and right sticks allow the driver to turn the robot
     * and move it forward and backward.
     */
    public void doLoop() {

        //**************** DRIVE TRAIN CONTROLS ****************\\

        if(gamepad1.left_stick_y > 0.5 && gamepad1.right_stick_y > 0.5){
            drive.moveFreely(gamepad1.left_stick_y - 0.25, gamepad1.right_stick_y - 0.25);
        }else{
            drive.moveFreely(gamepad1.left_stick_y/2, gamepad1.right_stick_y/2);
        }

        //**************** BUTTON PRESSER CONTROLS ****************\\
        if (gamepad1.x) {
            buttonPresser.presserLeft();
        }else if (gamepad1.b) {
            buttonPresser.presserRight();
        }

        //**************** BIG WHEEL CONTROLS ****************\\
        if(gamepad1.a){
            wheel.bigWheel.setPower(1.0);
            sleep(600);
        }

        if(gamepad1.left_trigger > 0 && gamepad1.right_trigger == 0){
            wheel.move(-gamepad1.left_trigger/3);
        }else if(gamepad1.right_trigger > 0 && gamepad1.left_trigger == 0){
            wheel.move(gamepad1.right_trigger/3);
        }else{
            wheel.stop();
        }

        //**************** GUIDE RAIL CONTROLS ****************\\
        double guideRailCurrentPosition = guideRail.guideRail.getPosition();

        if(gamepad1.dpad_up){
            guideRail.setPosition(guideRailCurrentPosition + 0.01);
        }else if(gamepad1.dpad_down){
            guideRail.setPosition(guideRailCurrentPosition - 0.01);
        }

        //**************** EXTRANEOUS CONTROLS ****************\\
        if(gamepad1.start){
            killAll();
        }

        telemetry.update();

    }

    private void killAll(){
        drive.stop();
        wheel.stop();
        buttonPresser.setPosition(0.5);
        guideRail.setPosition(1.0);

        turboMode = false;
    }
}
