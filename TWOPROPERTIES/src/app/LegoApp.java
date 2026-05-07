package app;

import threads.*;

public class LegoApp {

    public static void main(String[] args) {

        // creating objects
        RunLego runLego = new RunLego();
        ReadData readData = new ReadData();
        SendData sendData = new SendData();
        SensorCheck sensorCheck = new SensorCheck();
        LightCheck lightCheck = new LightCheck();

        System.out.println("Run in Threads");

        // creating threads for the objects
        Thread runLegoThread = new Thread(runLego);
        Thread readDataThread = new Thread(readData);
        Thread sendDataThread = new Thread(sendData);
        Thread sensorThread = new Thread(sensorCheck);
        Thread lightThread = new Thread(lightCheck);

        // starting the threads
        runLegoThread.start();
        readDataThread.start();
        sendDataThread.start();
        sensorThread.start();
        lightThread.start();
    }
}
