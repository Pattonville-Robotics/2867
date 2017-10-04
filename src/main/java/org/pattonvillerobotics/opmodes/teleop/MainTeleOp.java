package org.pattonvillerobotics.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.drive.SimpleMecanumDrive;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.GamepadData;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableButton;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableGamepad;

@TeleOp(name = "MainTeleOp", group = OpModeGroups.MAIN)
public class MainTeleOp extends LinearOpMode {

    private SimpleMecanumDrive drive;
    private ListenableGamepad gamepad;
    //private BenGrabber grabber;
    //private boolean grabberOpen;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        double[] polarCoords;

        waitForStart();

        while(opModeIsActive()) {
            polarCoords = SimpleMecanumDrive.toPolar(gamepad1.left_stick_x, gamepad1.left_stick_y);
            gamepad.update(new GamepadData(gamepad1));

            drive.moveFreely(polarCoords[1], polarCoords[0], gamepad1.right_stick_x);
        }
    }

    public void initialize() {
        drive = new SimpleMecanumDrive(this, hardwareMap);
        gamepad = new ListenableGamepad();
        //grabber = new BenGrabber(hardwareMap,this);

        gamepad.getButton(GamepadData.Button.X).addListener(ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                //grabber.open();

            }
        });
        gamepad.getButton(GamepadData.Button.Y).addListener(ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                //grabber.close();
            }
        });
    }
}
