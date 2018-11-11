package org.pattonvillerobotics.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.ImageProcessor;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.roverruckus.minerals.MineralDetector;
import org.pattonvillerobotics.commoncode.robotclasses.vuforia.VuforiaNavigation;
import org.pattonvillerobotics.robotclasses.CustomizedRobotParameters;

@Autonomous(name = "OpenCV Mineral Test", group = OpModeGroups.TESTING)
public class OpenCVMineralTest extends LinearOpMode {

    public MineralDetector mineralDetector;
    public VuforiaNavigation vuforia;
    
    private final String TAG = "MineralDetector";

    @Override
    public void runOpMode() {

        initialize();

        waitForStart();

        mineralDetector.process(vuforia.getImage());
    }

    public void initialize() {

        ImageProcessor.initOpenCV(hardwareMap, this);

        mineralDetector = new MineralDetector(CustomizedRobotParameters.PHONE_ORIENTATION, true);

        vuforia = new VuforiaNavigation(CustomizedRobotParameters.VUFORIA_PARAMETERS);
    }
}
