package threads;

import data.Robot;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class SensorCheck implements Runnable {

    EV3UltrasonicSensor sensor = new EV3UltrasonicSensor(SensorPort.S2);

    @Override
    public void run() {

        SampleProvider distance = sensor.getDistanceMode();
        float[] sample = new float[distance.sampleSize()];

        while (Robot.getRun() == 1) {

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            distance.fetchSample(sample, 0);
            int distanceCm = (int)(sample[0] * 100);

            System.out.println("Distance: " + distanceCm + " cm");

            if (distanceCm <= 30) {
                System.out.println("Wall detected at " + distanceCm + " cm");

                // Stop normal motor control
                Robot.setManualControl(true);

                // Stop
                Robot.setMotorPowers(0, 0);
                sleep(500);

                // 360-degree turn.
                // One motor forward, one motor backward.
                // You may need to adjust 2800 depending on your robot.
                Robot.setMotorPowers(35, -35);
                sleep(2800);

                // Stop after turning
                Robot.setMotorPowers(0, 0);
                sleep(300);

                // Continue forward again
                Robot.setManualControl(false);
                Robot.setTurn(0);
                Robot.setSpeed(20);

                // Small delay to avoid detecting the same wall immediately again
                sleep(1000);
            }
        }

        sensor.close();
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}