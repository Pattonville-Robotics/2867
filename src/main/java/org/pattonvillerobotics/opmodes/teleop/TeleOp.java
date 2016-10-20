package org.pattonvillerobotics.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.pattonvillerobotics.commoncode.robotclasses.drive.SimpleDrive;
import org.pattonvillerobotics.robotclasses.ButtonPresser;

/**
 * Created by developer on 10/11/16.
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

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();

        while (opModeIsActive()) {
            doLoop();
        }
    }

    /**
     * This method initializes the robot.
     */
    public void initialize() {
        drive = new SimpleDrive(this, hardwareMap);
        buttonPresser = new ButtonPresser(hardwareMap);
        drive.leftDriveMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        drive.rightDriveMotor.setDirection(DcMotorSimple.Direction.FORWARD);
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
        drive.moveFreely(-gamepad1.left_stick_y, -gamepad1.right_stick_y);

        if (gamepad1.x) {
            buttonPresser.presserExtend();
        } else if (gamepad1.b) {
            buttonPresser.presserDefault();
        }
    }
}
