package shop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
public class itemjason extends Thread{
	private Socket socket;
	public itemjason(Socket socket) 
	{
		this.socket=socket;
	}
    public void run()
    {
    	InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        OutputStream outputStream = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(outputStream, true);

        // Read the received data
        String receivedData = reader.readLine();
        JSONObject jsonObject = new JSONObject(receivedData);
        
        int num = jsonObject.getInt("num");
        
        item li = new item();
        manage ma=new manage();
        
        switch(num)
        {
           case 1:
           {
        	     List<commodity> commods = li.browseItems();
        	     
        	 
        	     JSONArray jsonArray = new JSONArray();
        	     

        	     for (commodity commod : commods) {
        	         JSONObject commodJson = new JSONObject();
        	         commodJson.put("ID", commod.getID());
        	         commodJson.put("commodity", commod.getCommodity());
        	         commodJson.put("money", commod.getMoney());
        	         commodJson.put("category", commod.getCategory());
        	         jsonArray.put(commodJson);
        	     }
        	     
        
        	     JSONObject responseJson = new JSONObject();
        	     responseJson.put("num", 1); 
        	     responseJson.put("commods", jsonArray); 
        	     
        	     
        	     writer.println(responseJson.toString());
        	     break;
        	 }
           case 2:
           {
        	     List<commodity> commods = li.browseItems();
        	     
        	 
        	     JSONArray jsonArray = new JSONArray();
        	     

        	     for (commodity commod : commods) {
        	         JSONObject commodJson = new JSONObject();
        	         commodJson.put("ID", commod.getID());
        	         commodJson.put("commodity", commod.getCommodity());
        	         commodJson.put("money", commod.getMoney()); 
        	         commodJson.put("amount", commod.getAmount());
        	         commodJson.put("category", commod.getCategory());
        	         commodJson.put("income", commod.getIncome());
        	         jsonArray.put(commodJson);
        	     }
        	     
        
        	     JSONObject responseJson = new JSONObject();
        	     responseJson.put("num", 2); 
        	     responseJson.put("commods", jsonArray); 
        	     
        	     
        	     writer.println(responseJson.toString());
        	     break;
        	 }
           
           case 3:
           {
        	   String categ = jsonObject.getString("categ");
        	   
        	   List<commodity> commods = li.browseItemscategory(categ);
      	     
          	 
      	     JSONArray jsonArray = new JSONArray();
      	     
      	     
      	   for (commodity commod : commods) {
  	         JSONObject commodJson = new JSONObject();
  	         commodJson.put("ID", commod.getID());
  	         commodJson.put("commodity", commod.getCommodity());
  	         commodJson.put("money", commod.getMoney());
  	         commodJson.put("category", commod.getCategory());
  	         jsonArray.put(commodJson);
  	     }
  	     
      	     
      	     
      	     JSONObject responseJson = new JSONObject();
      	     responseJson.put("num", 3); 
      	     responseJson.put("commods", jsonArray); 
      	     
      	     
      	     writer.println(responseJson.toString());
      	     break;
           }
           case 4:
           {
        	   String commodi = jsonObject.getString("commodi");
        	   
        	   List<commodity> commods = li.browseItemscategory(commodi);
      	     
          	 
      	     JSONArray jsonArray = new JSONArray();
      	     
      	     
      	   for (commodity commod : commods) {
  	         JSONObject commodJson = new JSONObject();
  	         commodJson.put("ID", commod.getID());
  	         commodJson.put("commodity", commod.getCommodity());
  	         commodJson.put("money", commod.getMoney());
  	         commodJson.put("category", commod.getCategory());
  	         jsonArray.put(commodJson);
  	     }
  	     
      	     
      	     
      	     JSONObject responseJson = new JSONObject();
      	     responseJson.put("num", 4); 
      	     responseJson.put("commods", jsonArray); 
      	     
      	     
      	     writer.println(responseJson.toString());
      	     break;
           }
           
           
           
           
           case 5:
           {
            	
                String commodi = jsonObject.getString("commodi");
                int amoun = jsonObject.getInt("amoun");
                String buye = jsonObject.getString("buye");
           
                li.buyItems(commodi,amoun,buye);

         	     JSONObject responseJson = new JSONObject();
         	     responseJson.put("num", 5); 
         	     responseJson.put("opr", "true"); 
         	     
         	    
         	     writer.println(responseJson.toString());
         	     break;
           }
           
           case 6:
           {
        	   String buyerer = jsonObject.getString("buyerer");
        	   
        	   List<buy> buyes = li.browseItemsbuy(buyerer);
      	     
          	 
      	     JSONArray jsonArray = new JSONArray();
      	     
      	     
      	   for (buy buye : buyes) {
  	         JSONObject buyJson = new JSONObject();
  	         buyJson.put("ID", buye.getID());
  	         buyJson.put("buycommodity", buye.getBuycommodity());
  	         buyJson.put("buyamount", buye.getBuyamount());
  	         buyJson.put("buydate", buye.getBuydate());
  	         
  	         buyJson.put("buymoney", buye.getBuymoney());
	         buyJson.put("buyer", buye.getBuyer());
  	         jsonArray.put(buyJson);
  	     }
  	     
      	     
      	     
      	     JSONObject responseJson = new JSONObject();
      	     responseJson.put("num", 6); 
      	     responseJson.put("buyes", jsonArray); 
      	     
      	     
      	     writer.println(responseJson.toString());
      	     break;
           }
           
           case 7:
           {
            	
                int id = jsonObject.getInt("id");
                
                li.refundItems(id);

         	     JSONObject responseJson = new JSONObject();
         	     responseJson.put("num", 7); 
         	     responseJson.put("opr", "true"); 
         	     
         	    
         	     writer.println(responseJson.toString());
         	     break;
           }
           case 8:
           {
        	   String refunderer = jsonObject.getString("refunderer");
        	   
        	   List<refund> refundes = li.browseItemsrefund(refunderer);
      	     
      	     JSONArray jsonArray = new JSONArray();
      	     
      	     
      	   for (refund refunde : refundes) {
  	         JSONObject refundJson = new JSONObject();
  	         refundJson.put("id", refunde.getId());
  	         refundJson.put("refundcommodity", refunde.getRefundcommodity());
  	         refundJson.put("refundamount", refunde.getRefundamount() );
  	         refundJson.put("refunddate", refunde.getRefunddate());
  	         
  	         refundJson.put("refundmoney", refunde.getRefundmoney());
  	         refundJson.put("refunder", refunde.getRefunder());
  	         jsonArray.put(refundJson);
  	     }
  	     
      	     
      	     
      	     JSONObject responseJson = new JSONObject();
      	     responseJson.put("num", 8); 
      	     responseJson.put("refundes", jsonArray); 
      	     
      	     
      	     writer.println(responseJson.toString());
      	     break;
           }
           
           case 9:
           {
 	   
        	   List<buy> buyes = ma.buymanage();
      	     
          	 
      	     JSONArray jsonArray = new JSONArray();
      	     
      	   for (buy buye : buyes) {
  	         JSONObject buyJson = new JSONObject();
  	         buyJson.put("ID", buye.getID());
  	         buyJson.put("buycommodity", buye.getBuycommodity());
  	         buyJson.put("buyamount", buye.getBuyamount());
  	         buyJson.put("buydate", buye.getBuydate());
  	         
  	         buyJson.put("buymoney", buye.getBuymoney());
	         buyJson.put("buyer", buye.getBuyer());
  	         jsonArray.put(buyJson);
  	     }
  	     
      	     
      	     
      	     JSONObject responseJson = new JSONObject();
      	     responseJson.put("num", 9); 
      	     responseJson.put("buyes", jsonArray); 
      	     
      	     
      	     writer.println(responseJson.toString());
      	     break;
           }
           
           case 10:
           {
        	  
        	   List<refund> refundes = ma.refundmanage();
      	     
      	     JSONArray jsonArray = new JSONArray();
      	     
      	     
      	   for (refund refunde : refundes) {
  	         JSONObject refundJson = new JSONObject();
  	         refundJson.put("id", refunde.getId());
  	         refundJson.put("refundcommodity", refunde.getRefundcommodity());
  	         refundJson.put("refundamount", refunde.getRefundamount() );
  	         refundJson.put("refunddate", refunde.getRefunddate());
  	         
  	         refundJson.put("refundmoney", refunde.getRefundmoney());
  	         refundJson.put("refunder", refunde.getRefunder());
  	         jsonArray.put(refundJson);
  	     }
  	     
      	     
      	     
      	     JSONObject responseJson = new JSONObject();
      	     responseJson.put("num", 10); 
      	     responseJson.put("refundes", jsonArray); 
      	     
      	     
      	     writer.println(responseJson.toString());
      	     break;
           }
           
           case 11:
           {
            	int amoun = jsonObject.getInt("amoun");
                String commodi = jsonObject.getString("commodi");
                
           
                ma.stock(amoun,commodi);

         	     JSONObject responseJson = new JSONObject();
         	     responseJson.put("num", 11); 
         	     responseJson.put("opr", "true"); 
         	     
         	    
         	     writer.println(responseJson.toString());
         	     break;
           }
           
           case 12:
           {
        	   String mone = jsonObject.getString("mone");
            	int amoun = jsonObject.getInt("amoun");
            	String incom = jsonObject.getString("incom");
            	String categ = jsonObject.getString("categ");
                String commodi = jsonObject.getString("commodi");
                
           
                ma.newstock(mone,amoun,incom,categ,commodi);

         	     JSONObject responseJson = new JSONObject();
         	     responseJson.put("num", 12); 
         	     responseJson.put("opr", "true"); 
         	     
         	    
         	     writer.println(responseJson.toString());
         	     break;
           }
          
           
        }
        
     // Close resources
        reader.close();
        writer.close();
        socket.close();

        
    } catch (Exception e) {
        e.printStackTrace();
    }
    	
    	
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
