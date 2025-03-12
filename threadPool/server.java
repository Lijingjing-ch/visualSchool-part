package threadPool;

import java.io.*;
import java.net.*;
import org.json.*;
import org.json.simple.JSONObject;

public class server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("Server is listening on port 8888");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("New client connected");

            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);

            String receivedData;
            while ((receivedData = reader.readLine()) != null) {
                System.out.println("Received data: " + receivedData);
               
                // Parse JSON data
                JSONObject jsonObject = new JSONObject(receivedData);
                int num=jsonObject.getInt("num");
                JSONObject responseJson = new JSONObject();
                switch(num) {
                case 1:
                {
                	UserService usv=new UserService();
                	Boolean res=usv.login(jsonObject.getString("id"), jsonObject.getString("psw"),jsonObject.getString("type"));
                	if(res)
                	{
                		responseJson.put("num", 1);
                		responseJson.put("result", true);
                	}
                	else
                	{
                		responseJson.put("num", 1);
                		responseJson.put("result", false);
                	}
                	
                	
                		
                }
                break;
                }
                writer.println(responseJson.toString());
            }

            reader.close();
            writer.close();
            socket.close();
        }
    }
}
