package visualSchool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class ServerReaderThread extends Thread {

    private Socket socket;

    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            System.out.println(reader);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);

            // Read the received data
            String receivedData = reader.readLine();
            while(true)
            {
            	
            if(receivedData.equals("EXIT"))
            	break;
            if(receivedData.equals(null))
            	continue;
            System.out.println("Original Data: " + receivedData);

            // Remove non-JSON related characters at the beginning of the string
            String jsonStr = receivedData.replaceAll("^[^\\{]*", "");
            System.out.println("Cleaned JSON Data: " + jsonStr);

            // Parse the cleaned JSON string
            JSONObject jsonObject = new JSONObject(jsonStr);
            
            int num = jsonObject.getInt("num");
            
            Library li = new Library();
            UserService userService=new UserService();
            switch(num)
            {
            
            
            case 22: // Register
            { String registerUserId = jsonObject.getString("id");
                String registerUserPassword = jsonObject.getString("psw");
                String registerIdentity = jsonObject.getString("type");
                System.out.println(registerUserId);
                System.out.println(registerUserPassword);
                System.out.println(registerIdentity);
                
                boolean registerResult = userService.register(registerUserId, registerUserPassword, registerIdentity);
                JSONObject registerResponse = new JSONObject();
                registerResponse.put("num", num);
                registerResponse.put("opr", registerResult ? "true" : "false");
                System.out.println(registerResponse.toString());
                writer.println(registerResponse.toString());
                break;
            }
            case 23: // Login
            { String loginUserId = jsonObject.getString("id");
                String loginUserPassword = jsonObject.getString("psw");
                String loginIdentity = jsonObject.getString("type");
                boolean loginResult = "true".equals(userService.login(loginUserId, loginUserPassword, loginIdentity));
                JSONObject loginResponse = new JSONObject();
                loginResponse.put("num", num);
                loginResponse.put("opr", loginResult ? "true" : "false");
                writer.println(loginResponse.toString());
                break;
            }
               case 24:
               {
            	     List<Book> books = li.getBooks();
            	     
            	 
            	     JSONArray jsonArray = new JSONArray();
            	     
  
            	     for (Book book : books) {
            	         JSONObject bookJson = new JSONObject();
            	         bookJson.put("bookid", book.getBookID());
            	         bookJson.put("category", book.getCategory());
            	         bookJson.put("bookname", book.getBookname());
            	         bookJson.put("author", book.getAuthor());
            	         bookJson.put("publisher", book.getPublisher());
            	         bookJson.put("remain", book.getRemain());
            	         bookJson.put("total", book.getTotal());
            	         bookJson.put("price", book.getPrice());
            	         jsonArray.put(bookJson);
            	     }
            	     
            
            	     JSONObject responseJson = new JSONObject();
            	     responseJson.put("num", 24); 
            	     responseJson.put("books", jsonArray); 
            	   
            	     System.out.println("Data: " + responseJson);
            	     writer.println(responseJson.toString());
            	     
            	     break;
            	 }
            
               case 25:
               {
            	   String bookname = jsonObject.getString("bookname");
            	   
            	   List<Book> books = li.getBook(bookname);
            	
          	     JSONArray jsonArray = new JSONArray();
          	     
          	     
          	     for (Book book : books) {
          	         JSONObject bookJson = new JSONObject();
          	         bookJson.put("bookid", book.getBookID());
          	         bookJson.put("category", book.getCategory());
          	         bookJson.put("bookname", book.getBookname());
          	         bookJson.put("author", book.getAuthor());
          	         bookJson.put("publisher", book.getPublisher());
          	         bookJson.put("remain", book.getRemain());
          	         bookJson.put("total", book.getTotal());
          	         bookJson.put("price", book.getPrice());
          	         jsonArray.put(bookJson);
          	     }
          	     
          	     
          	     JSONObject responseJson = new JSONObject();
          	     responseJson.put("num", 25); 
          	     responseJson.put("books", jsonArray); 
          	     
          	     
          	     writer.println(responseJson.toString());
          	     break;
               }
            
                                 
               case 26:
                              {
                               	
                                   String bookid = jsonObject.getString("bookid");
                                   String category = jsonObject.getString("category");
                                   String bookname = jsonObject.getString("bookname");
                                   String author = jsonObject.getString("author");
                                   String publisher = jsonObject.getString("publisher");
                                   int remain = jsonObject.getInt("remain");
                                   int total = jsonObject.getInt("total");
                                   int price = jsonObject.getInt("price");

                                  
                                   
                                   Book b = new Book(bookid, category, bookname, author, publisher, remain, total, price);
                                   
                                   if(li.addBook(b))
                                   {
                                   	JSONObject responseJson = new JSONObject();
                               	     responseJson.put("num", 26); 
                               	     responseJson.put("opr", "true");
                               	     writer.println(responseJson.toString());
                                   }
                                   else
                                   {
                                   	JSONObject responseJson = new JSONObject();
                              	     responseJson.put("num", 26); 
                              	     responseJson.put("opr", "false");
                              	     writer.println(responseJson.toString());
                                   }
                                            
                            	     
                            	     break;
                              }

              
               case 27:
                              {
                           	   String bookid = jsonObject.getString("bookid");
                           	   
                           	   if(li.search(bookid))
                           	   {
                           		   if(li.delBook(bookid))
                           		   {
                           			   JSONObject responseJson = new JSONObject();
                                    	   responseJson.put("num", 27); 
                                    	   responseJson.put("opr", "true"); 
                                    	  writer.println(responseJson.toString());
                           		   }
                           		   else
                           		   {
                           			   JSONObject responseJson = new JSONObject();
                                    	   responseJson.put("num", 27); 
                                    	   responseJson.put("opr", "false"); 
                                    	  writer.println(responseJson.toString());
                           		   }
                           	   }
                           	   else 
                           	   {
                           		   JSONObject responseJson = new JSONObject();
                                	   responseJson.put("num", 27); 
                                	   responseJson.put("opr", "false"); 
                                	  writer.println(responseJson.toString());
                           	   }
                           	   
                          	   
                          	     break;
                              }
                              
               case 28:
               {
            	     List<Borrow> borrows = li.getBorrow();
             	     JSONArray jsonArray = new JSONArray();
             	     
             	     for (Borrow record : borrows) {
             	         JSONObject bookJson = new JSONObject();
             	         bookJson.put("bookid", record.getBookID());
             	         bookJson.put("bookname", record.getBookname());
             	         bookJson.put("author", record.getAuthor());
             	         bookJson.put("btime", record.getBtime());
             	         bookJson.put("ltime", record.getLtime());
             	         bookJson.put("renew", record.getRenew());
             	         jsonArray.put(bookJson);
             	     }
             	     
             	     JSONObject responseJson = new JSONObject();
             	     responseJson.put("num", 28); 
             	     responseJson.put("books", jsonArray); 
             	     
             	     writer.println(responseJson.toString());
             	     break;
               }
               
               case 29:
               {
            	 List<History> historys = li.getHistory();
           	     JSONArray jsonArray = new JSONArray();
           	     
           	     for (History record : historys) {
           	         JSONObject bookJson = new JSONObject();
           	         bookJson.put("id", record.getID());
           	         bookJson.put("bookid", record.getBookID());
           	         bookJson.put("bookname", record.getBookname());
           	         bookJson.put("author", record.getAuthor());
           	         bookJson.put("btime", record.getBtime());
           	         bookJson.put("ltime", record.getLtime());
           	         
           	         jsonArray.put(bookJson);
           	     }
           	     
           	     JSONObject responseJson = new JSONObject();
           	     responseJson.put("num", 29); 
           	     responseJson.put("books", jsonArray); 
           	     
           	     writer.println(responseJson.toString());
           	     break;
               }
               
              
               case 30:
                              {
                                String bookid = jsonObject.getString("bookid");
                                String id = jsonObject.getString("id");
                           	   
                           	 if(li.renew(bookid,id))
                           	 {
                           		 JSONObject responseJson = new JSONObject();
                              	     responseJson.put("num", 30); 
                              	     responseJson.put("opr", "true");
                              	     writer.println(responseJson.toString());
                           	 }
                           	 else
                           	 {
                           		 JSONObject responseJson = new JSONObject();
                              	     responseJson.put("num", 30); 
                              	     responseJson.put("opr", "false");
                              	     writer.println(responseJson.toString());
                           	 }
                           	
                          	     
                          	     break;
                              }


               
               case 31:
                              {
                           	   String bookid = jsonObject.getString("bookid");
                           	   String id = jsonObject.getString("id");
                           	   
                             	   if(li.borrow(bookid,id))
                             	   {
                             		 JSONObject responseJson = new JSONObject();
                            	     responseJson.put("num", 31); 
                            	     responseJson.put("opr", "true"); 
                            	     
                            	     
                            	     writer.println(responseJson.toString());
                             	   }
                             	   else
                             	   {
                             		 JSONObject responseJson = new JSONObject();
                            	     responseJson.put("num", 31); 
                            	     responseJson.put("opr", "false"); 
                            	     
                            	     
                            	     writer.println(responseJson.toString());
                             	   }
                             	
                            	     
                            	     break;
                              }

        
               case 32:
                              {
                           	   String bookid = jsonObject.getString("bookid");

                           	   
                             	   if(li.returnBook(bookid))
                             	   {
                             		 JSONObject responseJson = new JSONObject();
                            	     responseJson.put("num", 32); 
                            	     responseJson.put("opr", "true"); 
                            	     
                            	     
                            	     writer.println(responseJson.toString());
                             	   }
                             	   else
                             	   {
                             		 JSONObject responseJson = new JSONObject();
                            	     responseJson.put("num", 32); 
                            	     responseJson.put("opr", "false"); 
                            	     
                            	     
                            	     writer.println(responseJson.toString());
                             	   }
                             	
                            	     
                            	     break;
                              }

               case 57:
               {
            	   String id = jsonObject.getString("id");
            	   
            	   List<Borrow> borrows = li.getSearchBorrow(id);
           	       JSONArray jsonArray = new JSONArray();
           	     
           	     for (Borrow record : borrows) {
           	         JSONObject bookJson = new JSONObject();
           	         bookJson.put("id", record.getStudentID());
           	         bookJson.put("bookid", record.getBookID());
           	         bookJson.put("bookname", record.getBookname());
           	         bookJson.put("author", record.getAuthor());
           	         bookJson.put("btime", record.getBtime());
           	         bookJson.put("ltime", record.getLtime());
           	         bookJson.put("renew", record.getRenew());
           	         jsonArray.put(bookJson);
           	     }
           	     
           	     JSONObject responseJson = new JSONObject();
           	     responseJson.put("num", 57); 
           	     responseJson.put("books", jsonArray); 
           	     
           	     writer.println(responseJson.toString());
           	     break;
               }
               
               case 58:
               {
            	   
            	   String id = jsonObject.getString("id");
            	   
            	   List<History> historys = li.getSearchHistory(id);
             	     JSONArray jsonArray = new JSONArray();
             	     
             	     for (History record : historys) {
             	         JSONObject bookJson = new JSONObject();
             	         bookJson.put("id", record.getID());
             	         bookJson.put("studentid", record.getStudentID());
             	         bookJson.put("bookid", record.getBookID());
             	         bookJson.put("bookname", record.getBookname());
             	         bookJson.put("author", record.getAuthor());
             	         bookJson.put("btime", record.getBtime());
             	         bookJson.put("ltime", record.getLtime());
             	         
             	         jsonArray.put(bookJson);
             	     }
             	     
             	     JSONObject responseJson = new JSONObject();
             	     responseJson.put("num", 58); 
             	     responseJson.put("books", jsonArray); 
             	     
             	     writer.println(responseJson.toString());
             	     break;
               }
               
            
               
            }
            
         // Close resources
          //  reader.close();
           // writer.close();
           // socket.close();

            receivedData=reader.readLine();
        } 
            }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
