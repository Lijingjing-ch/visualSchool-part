package visualSchool;  
  
import java.io.BufferedReader;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.ObjectOutputStream;  
import java.io.OutputStream;  
import java.io.PrintWriter;  
import java.net.Socket;  
import org.json.JSONObject;  
  
public class SocketManager {  
    private static Socket socket;  
    private static ObjectOutputStream oos;  
    private static PrintWriter writer;  
    private static BufferedReader reader;  
  
    public static void openSocket() throws Exception {  
        socket = new Socket("127.0.0.1", 8888);  
       
        
    }  
  
    public static void closeSocket() throws Exception {  
        if (reader != null) {  
            reader.close();  
        }  
        if (writer != null) {  
            writer.close();  
        }  
        if (oos != null) {  
            oos.close();  
        }  
        if (socket != null) {  
            socket.close();  
        }  
    }  
  
    public static void sendData(JSONObject jsonObject) throws Exception {  
        if (oos == null || socket == null) {  
            openSocket();  
        }  
        oos = new ObjectOutputStream(socket.getOutputStream());  
        writer = new PrintWriter(oos, true);  
        InputStream inputStream = socket.getInputStream();  
        reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")); 
        String jsonString = jsonObject.toString();  
        System.out.println("Sending data: " + jsonString.toString());
        writer.println(jsonObject.toString());  
        
    }  
  
    public static String receiveData() throws Exception {  
        String responseData = reader.readLine();  
        System.out.println("Received response: " + responseData);  
        return responseData;  
    }  
  
    // 鍙互鍦ㄩ渶瑕佺殑鏃跺�欒皟鐢ㄨ繖涓や釜鏂规硶锛屼緥濡傦細  
    // public static void communicate() throws Exception {  
    //     JSONObject jsonObject = new JSONObject();  
    //     jsonObject.put("key", "value");  
    //     sendData(jsonObject);  
    //     String response = receiveData();  
    //     // 澶勭悊鍝嶅簲  
    // }  
}