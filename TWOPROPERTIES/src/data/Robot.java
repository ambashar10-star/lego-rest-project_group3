package data;

public class Robot {

    private static int id;
    private static int speed = 20;
    private static int turn;
    private static int run = 1;

    private static boolean manualControl = false;
    private static int leftPower = 0;
    private static int rightPower = 0;

    public static int getId() {
        return id;
    }

    public static void setId(String id) {
        try {
            Robot.id = Integer.parseInt(id);
        } catch (Exception e) {
        }
    }

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        Robot.speed = speed;
    }

    public static void setSpeed(String speed) {
        try {
            Robot.speed = Integer.parseInt(speed);
        } catch (Exception e) {
        }
    }

    public static int getTurn() {
        return turn;
    }

    public static void setTurn(int turn) {
        Robot.turn = turn;
    }

    public static void setTurn(String turn) {
        try {
            Robot.turn = Integer.parseInt(turn);
        } catch (Exception e) {
        }
    }

    public static int getRun() {
        return run;
    }

    public static void setRun(int run) {
        Robot.run = run;
    }

    public static void setRun(String run) {
        try {
            Robot.run = Integer.parseInt(run);
        } catch (Exception e) {
        }
    }

    public static boolean isManualControl() {
        return manualControl;
    }

    public static void setManualControl(boolean manualControl) {
        Robot.manualControl = manualControl;
    }

    public static void setMotorPowers(int left, int right) {
        Robot.leftPower = left;
        Robot.rightPower = right;
    }

    public static int getLeftPower() {
        return leftPower;
    }

    public static int getRightPower() {
        return rightPower;
    }

    public static int turnRight() {
        if (turn < 0) {
            return speed + turn;
        } else {
            return speed;
        }
    }

    public static int turnLeft() {
        if (turn > 0) {
            return speed - turn;
        } else {
            return speed;
        }
    }
}