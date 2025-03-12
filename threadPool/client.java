package threadPool;

import java.io.*;
import java.net.*;
import org.json.*;

public class client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1111);
        System.out.println("Connected to the server");

        OutputStream outputStream = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(outputStream, true);

        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // Create a JSON object to send to the server
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num", 1);
        jsonObject.put("id", "9025102");
        jsonObject.put("psw", "1234");
        jsonObject.put("type", "student");

        // Send JSON object to the server
        writer.println(jsonObject.toString());

        // Read the response from the server
        String responseData = reader.readLine();
        System.out.println("Received response: " + responseData);

        reader.close();
        writer.close();
        socket.close();
    }
}
