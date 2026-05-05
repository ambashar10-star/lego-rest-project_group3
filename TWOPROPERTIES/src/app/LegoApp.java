package app;

import threads.*;

public class LegoApp {

    public static void main(String[] args) {

        RunLego runLego = new RunLego();
        ReadData readData = new ReadData();
        SendData sendData = new SendData();
        SensorCheck sensorCheck = new SensorCheck();
        //LightCheck lightCheck = new LightCheck();

        System.out.println("Run in Threads");

        Thread runLegoThread = new Thread(runLego);
        Thread readDataThread = new Thread(readData);
        Thread sendDataThread = new Thread(sendData);
        Thread sensorThread = new Thread(sensorCheck);
        //Thread lightThread = new Thread(lightCheck);

        runLegoThread.start();
        readDataThread.start();
        sendDataThread.start();
        sensorThread.start();
        //lightThread.start();
    }
}