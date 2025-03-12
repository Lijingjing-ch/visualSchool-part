package libraryManageSystem;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.json.JSONObject;

public class Client {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		
		Socket socket = new Socket("127.0.0.1",8888);
		
		OutputStream outputStream = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(outputStream, true);

        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // Create a JSON object to send to the server
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num", 30);
        jsonObject.put("bookid", "13922002");
        jsonObject.put("id", "09022124");
        

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
