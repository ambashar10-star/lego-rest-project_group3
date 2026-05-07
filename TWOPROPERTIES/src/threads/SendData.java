package threads;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import data.Robot;

/**
 * SendData sends robot status data back to the REST service.
 * This proves that the robot can also send data to the database.
 */
public class SendData implements Runnable {

    @Override
    public void run() {

        while (Robot.getRun() == 1) {

            try {
                Thread.sleep(2000); // Send data every 2 seconds
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                URL url = new URL("http://192.168.0.102:8080/rest/lego/setsensordata");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");  // sending info back to web
                conn.setRequestProperty("Content-Type", "application/json"); // data formatted as JSON
                conn.setDoOutput(true); // write data to the connection

                // building the json string
                String json = "{"
                        + "\"sensorType\":\"wall\","
                        + "\"sensorValue\":10,"
                        + "\"speed\":" + Robot.getSpeed() + ","
                        + "\"turn\":" + Robot.getTurn() + ","
                        + "\"action\":\"avoid\""
                        + "}";

                OutputStream os = conn.getOutputStream();
                os.write(json.getBytes()); //pushes the JSON string through the network to thr web service.
                os.flush();
                os.close();

                int responseCode = conn.getResponseCode();
                System.out.println("SendData response: " + responseCode);

                conn.disconnect();
            } 
            catch (Exception e) {
                System.out.println("SendData error!");
                e.printStackTrace();
            }
        }
    }
}
