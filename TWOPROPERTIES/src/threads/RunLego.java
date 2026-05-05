package threads;

import data.Robot;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;

public class RunLego implements Runnable {

    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

    @Override
    public void run() {

        while (Robot.getRun() == 1) {

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (Robot.isManualControl()) {
                motorA.setPower(Robot.getLeftPower());
                motorB.setPower(Robot.getRightPower());
            } else {
                motorA.setPower(Robot.turnLeft());
                motorB.setPower(Robot.turnRight());
            }
        }

        motorA.setPower(0);
        motorB.setPower(0);
    }
}