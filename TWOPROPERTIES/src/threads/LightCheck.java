package threads;

import data.Robot;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class LightCheck implements Runnable {

    EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S4);

    @Override
    public void run() {

        SampleProvider light = colorSensor.getRedMode();
        float[] sample = new float[light.sampleSize()];

        while (Robot.getRun() == 1) {

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            light.fetchSample(sample, 0);

            int lightValue = (int)(sample[0] * 100);

            System.out.println("Light: " + lightValue);

          // below 15 is a black surface
            if (lightValue < 15) {
                System.out.println("Black line detected");

                Robot.setManualControl(true);

                // Stop
                Robot.setMotorPowers(0, 0);
                sleep(500);

                // Turn away from line
                Robot.setMotorPowers(30, -30);
                sleep(1000);

                // Continue forward
                Robot.setManualControl(false);
                Robot.setTurn(0);
                Robot.setSpeed(20);

                sleep(1000);
            }
        }

        colorSensor.close();
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
