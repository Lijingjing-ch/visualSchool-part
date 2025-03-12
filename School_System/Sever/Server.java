package Sever;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    @SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
    	
    	System.out.println("server start");
    	
    	ServerSocket serverSocket = new ServerSocket(1111);
    	
    	while(true) {
    
    		Socket socket = serverSocket.accept();
    		System.out.println("someone login"+socket.getRemoteSocketAddress());
    		
    		new Sever_ThreadHandler(socket).start();
    	}
    	
    	/*
    	Socket socket = serverSocket.accept();
    	InputStream is = socket.getInputStream();	
    	DataInputStream dis = new DataInputStream(is);
    
    	String bookid = dis.readUTF();
    	String category = dis.readUTF();
    	String name = dis.readUTF();
    	String author = dis.readUTF();
    	String publisher = dis.readUTF();
    	int remain = dis.read();
    	int total = dis.read();
    	double price = dis.readDouble();

    	
    	Library li = new Library();
    	Book b = new Book(bookid,category,name,author,publisher,remain,total,price);
    	li.addBook(b);
    	*/
    }

}
