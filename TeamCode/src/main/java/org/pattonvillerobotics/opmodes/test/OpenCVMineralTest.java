package org.pattonvillerobotics.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.ImageProcessor;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.roverruckus.minerals.MineralDetector;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.util.Contour;
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

        long startTime, endTime;

        while (opModeIsActive()) {

            mineralDetector.process(vuforia.getImage());

            if (mineralDetector.getHorizontalAnalysis() != null) {
                telemetry.addData("Mineral Position:", mineralDetector.getHorizontalAnalysis());
            }
            if (mineralDetector.goldDetector.getContours() != null && mineralDetector.goldDetector.getContours().size() > 0) {
                telemetry.addData("Gold Mineral Size:",
                        Imgproc.contourArea(Contour.findLargestContour(mineralDetector.goldDetector.getContours())));
            }

            telemetry.update();
        }
    }

    public void initialize() {

        ImageProcessor.initOpenCV(hardwareMap, this);

        mineralDetector = new MineralDetector(CustomizedRobotParameters.PHONE_ORIENTATION, true);

        vuforia = new VuforiaNavigation(CustomizedRobotParameters.VUFORIA_PARAMETERS);
    }
}
