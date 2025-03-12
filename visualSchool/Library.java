package visualSchool;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Library {
	
	String URL = "jdbc:ucanaccess://D:/����ѧУ-2/רҵʵѵ/Database1.accdb";

	//��ȡ�����鼮
	List<Book> getBooks()
	{
		List<Book> books=new ArrayList<>();
		String query="SELECT * FROM Book";
		
		try (Connection connection = DriverManager.getConnection(URL);
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(query)) {

	            while (resultSet.next()) {
	                String bookid = resultSet.getString("�鼮ID");
	                String category = resultSet.getString("���");
	                String bookname = resultSet.getString("����");
	                String author = resultSet.getString("����");
	                String publisher = resultSet.getString("������");
	                int remain = resultSet.getInt("ʣ����");
	                int total = resultSet.getInt("�ܿ��");
	                double price = resultSet.getDouble("�۸�");

	                Book book = new Book(bookid,category,bookname,author,
	                		publisher,remain,total,price);
	                books.add(book);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		return books;
		
	}
	
	//��ȡ�����鼮
	List<Book> getBook(String name)
	{
		List<Book> books = new ArrayList<>();
	    String query = "SELECT * FROM Book WHERE ���� LIKE ?";
	    
	    try (Connection connection = DriverManager.getConnection(URL);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, "%" + name + "%");
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                String bookid = resultSet.getString("�鼮ID");
	                String category = resultSet.getString("���");
	                String bookname = resultSet.getString("����");
	                String author = resultSet.getString("����");
	                String publisher = resultSet.getString("������");
	                int remain = resultSet.getInt("ʣ����");
	                int total = resultSet.getInt("�ܿ��");
	                double price = resultSet.getDouble("�۸�");

	                Book book = new Book(bookid, category, bookname, author, publisher, remain, total, price);
	                books.add(book);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return books;
	}
	
	//�����鼮
	boolean addBook(Book book)
	{
		String query = "INSERT INTO Book (�鼮ID, ���, ����, ����, ������, ʣ����, �ܿ��, �۸�) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    
	    try (Connection connection = DriverManager.getConnection(URL);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, book.getBookID());
	        preparedStatement.setString(2, book.getCategory());
	        preparedStatement.setString(3, book.getBookname());
	        preparedStatement.setString(4, book.getAuthor());
	        preparedStatement.setString(5, book.getPublisher());
	        preparedStatement.setInt(6, book.getRemain());
	        preparedStatement.setInt(7, book.getTotal());
	        preparedStatement.setDouble(8, book.getPrice());

	        preparedStatement.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	boolean search(String id) {

	    String query = "SELECT * FROM Book WHERE �鼮ID = ?";


	    try (Connection connection = DriverManager.getConnection(URL);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {


	        preparedStatement.setString(1, id);


	        try (ResultSet resultSet = preparedStatement.executeQuery()) {

	            return resultSet.next();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	
	        return false;
	    }
	}

	
	//ɾ���鼮
	boolean delBook(String bookId)
	{
		String query = "DELETE FROM Book WHERE �鼮ID = ?";
	    
	    try (Connection connection = DriverManager.getConnection(URL);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, bookId);
	        preparedStatement.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	   
	}
	
	//��ȡ���н��ļ�¼
	List<Borrow> getBorrow()
	{
		List<Borrow> borrows=new ArrayList<>();
		String query="SELECT * FROM Borrow";
		
		try (Connection connection = DriverManager.getConnection(URL);
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(query)) {

	            while (resultSet.next()) {
	            	String studentid = resultSet.getString("������ID");
	                String bookid = resultSet.getString("�鼮ID");
	                String bookname = resultSet.getString("����");
	                String author = resultSet.getString("����");
	                String btime = resultSet.getString("����ʱ��");
	                String ltime = resultSet.getString("Ӧ��ʱ��");
	                int renew = resultSet.getInt("�������");

	                Borrow borrow = new Borrow(studentid,bookid,bookname,author,btime,
	                		ltime,renew);
	                borrows.add(borrow);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		return borrows;
	}
	
	//��ȡ�������ļ�¼
		List<Borrow> getSearchBorrow(String id)
		{
			List<Borrow> borrows=new ArrayList<>();
		    String query = "SELECT * FROM Borrow WHERE ������ID = ?";
		    
		    try (Connection connection = DriverManager.getConnection(URL);
		         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

		        preparedStatement.setString(1, id);
		        
		        try (ResultSet resultSet = preparedStatement.executeQuery()) {
		            while (resultSet.next()) {
		            	String studentid = resultSet.getString("������ID");
		                String bookid = resultSet.getString("�鼮ID");
		                String bookname = resultSet.getString("����");
		                String author = resultSet.getString("����");
		                String btime = resultSet.getString("����ʱ��");
		                String ltime = resultSet.getString("Ӧ��ʱ��");
		                int renew = resultSet.getInt("�������");

		                Borrow borrow = new Borrow(studentid,bookid,bookname,author,btime,
		                		ltime,renew);
		                borrows.add(borrow);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    return borrows;
		}
	
	//��ȡ������ʷ��¼
	List<History> getHistory()
	{
		List<History> historys=new ArrayList<>();
		String query="SELECT * FROM History";
		
		try (Connection connection = DriverManager.getConnection(URL);
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(query)) {

	            while (resultSet.next()) {
	            	int ID = resultSet.getInt("ID");
	            	String studentid = resultSet.getString("������ID");
	                String bookid = resultSet.getString("�鼮ID");
	                String bookname = resultSet.getString("����");
	                String author = resultSet.getString("����");
	                String btime = resultSet.getString("����ʱ��");
	                String ltime = resultSet.getString("�黹ʱ��");

	                History history = new History(ID,studentid,bookid,bookname,author,btime,
	                		ltime);
	                historys.add(history);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		return historys;
	}
	
	//��ȡ������ʷ��¼
			List<History> getSearchHistory(String id)
			{
				List<History> historys=new ArrayList<>();
			    String query = "SELECT * FROM History WHERE ������ID = ?";
			    
			    try (Connection connection = DriverManager.getConnection(URL);
			         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			        preparedStatement.setString(1, id);
			        
			        try (ResultSet resultSet = preparedStatement.executeQuery()) {
			            while (resultSet.next()) {
			            	int ID = resultSet.getInt("ID");
			            	String studentid = resultSet.getString("������ID");
			                String bookid = resultSet.getString("�鼮ID");
			                String bookname = resultSet.getString("����");
			                String author = resultSet.getString("����");
			                String btime = resultSet.getString("����ʱ��");
			                String ltime = resultSet.getString("�黹ʱ��");

			                History history = new History(ID,studentid,bookid,bookname,author,btime,
			                		ltime);
			                historys.add(history);
			            }
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			    
			    return historys;
			}
	
			boolean renew(String bookid, String id) {
			    String query1 = "UPDATE Borrow SET ������� = ������� + 1 WHERE �鼮ID = ? AND ������ID = ?";
			    String query2 = "UPDATE Borrow SET Ӧ��ʱ�� = ? WHERE �鼮ID = ? AND ������ID = ?";

			    try (Connection connection = DriverManager.getConnection(URL);
			         PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
			         PreparedStatement preparedStatement2 = connection.prepareStatement(query2)) {

			        // Update the renewal count
			        preparedStatement1.setString(1, bookid);
			        preparedStatement1.setString(2, id);
			        preparedStatement1.executeUpdate();

			        // Calculate new due date
			        LocalDate today = LocalDate.now();
			        LocalDate futureDate = today.plusDays(30);
			        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
			        String formattedDate = futureDate.format(formatter);

			        // Update the due date
			        preparedStatement2.setString(1, formattedDate);
			        preparedStatement2.setString(2, bookid);
			        preparedStatement2.setString(3, id);
			        preparedStatement2.executeUpdate();
			        
			        return true;

			    } catch (SQLException e) {
			    	 e.printStackTrace();
			    	    // Optionally log the SQL state and error code
			    	    System.err.println("SQLState: " + e.getSQLState());
			    	    System.err.println("Error Code: " + e.getErrorCode());
			    	    return false;
			    }
			}

	
	//�����鼮
	boolean borrow(String bookid,String id) {
	    String query1 = "UPDATE Book SET ʣ���� = ʣ���� - 1 WHERE �鼮ID = ?";
	    String query2 = "SELECT * FROM Book WHERE �鼮ID = ?";
	    String query3 = "INSERT INTO Borrow (������ID, �鼮ID, ����, ����, ����ʱ��, Ӧ��ʱ��, �������) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    String query4 = "INSERT INTO History (������ID, �鼮ID, ����, ����, ����ʱ��) VALUES (?, ?, ?, ?, ?)";
	    
	    try (Connection connection = DriverManager.getConnection(URL)) {
	        // Check current stock
	        int currentStock = 0;
	        String bookname = "";
	        String author = "";
	        LocalDate today = LocalDate.now();
	        LocalDate futureDate = today.plusDays(30);
	        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
	        String formattedDate = futureDate.format(formatter);
	        
	        // Retrieve book details and check stock
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query2)) {
	            preparedStatement.setString(1, bookid);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    bookname = resultSet.getString("����");
	                    author = resultSet.getString("����");
	                    currentStock = resultSet.getInt("ʣ����");
	                } else {
	                    // Book not found, handle accordingly
	                    throw new SQLException("Book with ID " + bookid + " not found.");
	                }
	            }
	        }
	        
	        // Check if stock is available
	        if (currentStock <= 0) {
	            // Handle no stock case
	            throw new SQLException("No stock available for book ID " + bookid);
	        }
	        
	        // Update book stock
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query1)) {
	            preparedStatement.setString(1, bookid);
	            preparedStatement.executeUpdate();
	        }
	        
	        // Insert borrow record
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query3)) {
	        	preparedStatement.setString(1, id);
	        	preparedStatement.setString(2, bookid);
	            preparedStatement.setString(3, bookname);
	            preparedStatement.setString(4, author);
	            preparedStatement.setDate(5, java.sql.Date.valueOf(today));
	            preparedStatement.setDate(6, java.sql.Date.valueOf(futureDate));
	            preparedStatement.setInt(7, 0);
	            preparedStatement.executeUpdate();
	        }
	        
	        // Insert history record
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query4)) {
	        	preparedStatement.setString(1, id);
	        	preparedStatement.setString(2, bookid);
	            preparedStatement.setString(3, bookname);
	            preparedStatement.setString(4, author);
	            preparedStatement.setDate(5, java.sql.Date.valueOf(today));
	            preparedStatement.executeUpdate();
	        }
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	        // Additional error handling or rollback could be added here if needed
	    }
	}
	
	//�黹�鼮
	boolean returnBook(String bookid)
	{
		String query1 = "UPDATE Book SET ʣ���� = ʣ���� + 1 WHERE �鼮ID = ?";
	    String query2 = "DELETE * FROM Borrow WHERE �鼮ID = ?";
	    String query3 = "UPDATE History SET �黹ʱ�� = ? WHERE �鼮ID = ?";
	    
	    LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String formattedDate = today.format(formatter);
        
	    
	    try (Connection connection = DriverManager.getConnection(URL)) {
	        // Update book stock
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query1)) {
	            preparedStatement.setString(1, bookid);
	            preparedStatement.executeUpdate();
	        }
	        
	     // Delete borrow record
	     try (PreparedStatement preparedStatement = connection.prepareStatement(query2)) {
	            preparedStatement.setString(1, bookid);
	            preparedStatement.executeUpdate();
	        }
	        
	     // Update history record
	     try (PreparedStatement preparedStatement = connection.prepareStatement(query3)) {
	            preparedStatement.setDate(1, java.sql.Date.valueOf(today));
	            preparedStatement.setString(2, bookid);
	            preparedStatement.executeUpdate();
	        }
	     return true;
	} catch (SQLException e) {
        e.printStackTrace();
        return false;
        // Additional error handling or rollback could be added here if needed
    }
	    
	}
	
	
	
	
}
