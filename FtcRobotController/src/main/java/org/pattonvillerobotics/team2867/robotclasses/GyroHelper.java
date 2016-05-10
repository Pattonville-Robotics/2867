package org.pattonvillerobotics.team2867.robotclasses;

import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class GyroHelper {

    Thread gyroThread = new Thread(new GyroLoop());
    private GyroSensor gyro;
    private double gyroOffset;
    private long numGyroOffsetSamples = 0;
    private double happy_angle = 0;

    public GyroHelper(HardwareMap hardwareMap) {
        this.gyro = hardwareMap.gyroSensor.get("gyro_sensor");
        gyroThread.start();
    }

    /*
       * Code to run when the op mode is first enabled goes here
       * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
       */
    public double calibrate() {
        double currentGyroOffset = gyro.getRotation();
        gyroOffset = (gyroOffset * numGyroOffsetSamples + currentGyroOffset) / (numGyroOffsetSamples + 1);
        numGyroOffsetSamples++;
        happy_angle = 0;
        return gyroOffset;
    }

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */
    public double getAngle() {
        return happy_angle;
    }

    private class GyroLoop implements Runnable {
        public void run() {
            long currentTime;
            long deltaTime;

            try {
                long previousTime = System.currentTimeMillis();

                while (true) {

                    currentTime = System.currentTimeMillis();

                    deltaTime = currentTime - previousTime;

                    double rate = (gyro.getRotation() - gyroOffset);

                    // angle (degrees) = rate (degrees/second) * time (seconds)
                    double angle = rate * deltaTime / 1000;

                    happy_angle += angle;

                    // Pause for 10 milliseconds
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}