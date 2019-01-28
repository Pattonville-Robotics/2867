package org.pattonvillerobotics.opmodes.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.drive.SimpleMecanumDrive;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.GamepadData;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableButton;
import org.pattonvillerobotics.commoncode.robotclasses.gamepad.ListenableGamepad;
import org.pattonvillerobotics.robotclasses.CustomizedRobotParameters;
import org.pattonvillerobotics.robotclasses.IntakeMechanism;
import org.pattonvillerobotics.robotclasses.TapeMeasureLifter;

@TeleOp(name = "MainTeleOp", group = OpModeGroups.MAIN)
public class MainTeleOp extends LinearOpMode {

    public SimpleMecanumDrive drive;
    public ListenableGamepad listenableGamepad1;
    private IntakeMechanism intakeMechanism;
    private TapeMeasureLifter lifter;
    private boolean fieldOrientedDriveMode = false;
    private BNO055IMU imu;

    @Override
    public void runOpMode() {

        initialize();
        Vector2D polarCoords;
        Orientation angles;

        waitForStart();

        while (opModeIsActive()) {
            polarCoords = SimpleMecanumDrive.toPolar(-gamepad1.left_stick_x, gamepad1.left_stick_y);
            angles = imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
            listenableGamepad1.update(gamepad1);
            drive.moveFreely(polarCoords.getY() - (fieldOrientedDriveMode ? angles.secondAngle + (Math.PI / 2.) : 0), polarCoords.getX(), -gamepad1.right_stick_x);

            intakeMechanism.setPivotMotorPower(gamepad1.right_trigger-gamepad1.left_trigger);

            telemetry.clearAll();

            telemetry.update();
        }
    }

    public void initialize() {
        listenableGamepad1 = new ListenableGamepad();

        lifter = new TapeMeasureLifter(hardwareMap, this);

        intakeMechanism = new IntakeMechanism(hardwareMap, this);

        listenableGamepad1.addButtonListener(GamepadData.Button.DPAD_UP, ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                lifter.winchMotor.setPower(1);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.DPAD_UP, ListenableButton.ButtonState.JUST_RELEASED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                lifter.winchMotor.setPower(0);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.DPAD_DOWN, ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                lifter.winchMotor.setPower(-1);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.DPAD_DOWN, ListenableButton.ButtonState.JUST_RELEASED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                lifter.winchMotor.setPower(0);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.X, ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                intakeMechanism.extendServo.setDirection(DcMotorSimple.Direction.FORWARD);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.X, ListenableButton.ButtonState.BEING_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                intakeMechanism.setExtendServoPower(1);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.X, ListenableButton.ButtonState.JUST_RELEASED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                intakeMechanism.setExtendServoPower(0);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.Y, ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                intakeMechanism.extendServo.setDirection(DcMotorSimple.Direction.REVERSE);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.Y, ListenableButton.ButtonState.BEING_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                intakeMechanism.setExtendServoPower(1);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.Y, ListenableButton.ButtonState.JUST_RELEASED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                intakeMechanism.setExtendServoPower(0);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.A, ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                intakeMechanism.bucketServo.setDirection(DcMotorSimple.Direction.FORWARD);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.A, ListenableButton.ButtonState.BEING_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                intakeMechanism.setBucketServoPower(1);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.A, ListenableButton.ButtonState.JUST_RELEASED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                intakeMechanism.setBucketServoPower(0);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.B, ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                intakeMechanism.bucketServo.setDirection(DcMotorSimple.Direction.REVERSE);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.B, ListenableButton.ButtonState.BEING_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                intakeMechanism.setBucketServoPower(1);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.B, ListenableButton.ButtonState.JUST_RELEASED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                intakeMechanism.setBucketServoPower(0);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.RIGHT_BUMPER, ListenableButton.ButtonState.JUST_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                intakeMechanism.tubeServo.setDirection(DcMotorSimple.Direction.REVERSE);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.RIGHT_BUMPER, ListenableButton.ButtonState.BEING_PRESSED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                intakeMechanism.setTubeServoPower(1);
            }
        });

        listenableGamepad1.addButtonListener(GamepadData.Button.RIGHT_BUMPER, ListenableButton.ButtonState.BEING_RELEASED, new ListenableButton.ButtonListener() {
            @Override
            public void run() {
                intakeMechanism.setTubeServoPower(0);
            }
        });

        drive = new SimpleMecanumDrive(this, hardwareMap);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        drive.leftRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        drive.rightRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        drive.leftDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        drive.rightDriveMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }
}
