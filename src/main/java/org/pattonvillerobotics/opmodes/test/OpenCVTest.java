package org.pattonvillerobotics.opmodes.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.pattonvillerobotics.commoncode.opmodes.OpModeGroups;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.JewelColorDetector;
import org.pattonvillerobotics.commoncode.robotclasses.vuforia.VuforiaNavigation;
import org.pattonvillerobotics.opmodes.CustomizedRobotParameters;

import java.io.FileOutputStream;

/**
 * Created by greg on 10/7/2017.
 */

@Autonomous(name = "OpenCV Test", group = OpModeGroups.TESTING)
public class OpenCVTest extends LinearOpMode {

    private JewelColorDetector jewelColorDetector;
    private VuforiaNavigation vuforia;

    @Override
    public void runOpMode() throws InterruptedException {
        jewelColorDetector = new JewelColorDetector(hardwareMap);
        vuforia = new VuforiaNavigation(CustomizedRobotParameters.VUFORIA_PARAMETERS);
        Bitmap img;

        waitForStart();

        img = vuforia.getImage();

        Log.i("OpenCVTest", "Width: " + img.getWidth() + " Height: " + img.getHeight());

        try {
            FileOutputStream fos = hardwareMap.appContext.openFileOutput("testPic.png", Context.MODE_PRIVATE);
            img.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            Log.i("OpenCVTest", "Image saved.");
            Log.i("OpenCVTest", hardwareMap.appContext.getFilesDir().getAbsolutePath());
        } catch (Exception e) {
            Log.e("OpenCVTest", e.getMessage());
        }

        while(opModeIsActive()) {
            idle();
        }
    }
}
