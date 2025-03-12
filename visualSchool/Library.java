package visualSchool;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Library {
	
	String URL = "jdbc:ucanaccess://D:/暑期学校-2/专业实训/Database1.accdb";

	//获取所有书籍
	List<Book> getBooks()
	{
		List<Book> books=new ArrayList<>();
		String query="SELECT * FROM Book";
		
		try (Connection connection = DriverManager.getConnection(URL);
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(query)) {

	            while (resultSet.next()) {
	                String bookid = resultSet.getString("书籍ID");
	                String category = resultSet.getString("类别");
	                String bookname = resultSet.getString("书名");
	                String author = resultSet.getString("作者");
	                String publisher = resultSet.getString("出版社");
	                int remain = resultSet.getInt("剩余库存");
	                int total = resultSet.getInt("总库存");
	                double price = resultSet.getDouble("价格");

	                Book book = new Book(bookid,category,bookname,author,
	                		publisher,remain,total,price);
	                books.add(book);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		return books;
		
	}
	
	//获取搜索书籍
	List<Book> getBook(String name)
	{
		List<Book> books = new ArrayList<>();
	    String query = "SELECT * FROM Book WHERE 书名 LIKE ?";
	    
	    try (Connection connection = DriverManager.getConnection(URL);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, "%" + name + "%");
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                String bookid = resultSet.getString("书籍ID");
	                String category = resultSet.getString("类别");
	                String bookname = resultSet.getString("书名");
	                String author = resultSet.getString("作者");
	                String publisher = resultSet.getString("出版社");
	                int remain = resultSet.getInt("剩余库存");
	                int total = resultSet.getInt("总库存");
	                double price = resultSet.getDouble("价格");

	                Book book = new Book(bookid, category, bookname, author, publisher, remain, total, price);
	                books.add(book);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return books;
	}
	
	//增加书籍
	boolean addBook(Book book)
	{
		String query = "INSERT INTO Book (书籍ID, 类别, 书名, 作者, 出版社, 剩余库存, 总库存, 价格) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    
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

	    String query = "SELECT * FROM Book WHERE 书籍ID = ?";


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

	
	//删除书籍
	boolean delBook(String bookId)
	{
		String query = "DELETE FROM Book WHERE 书籍ID = ?";
	    
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
	
	//获取所有借阅记录
	List<Borrow> getBorrow()
	{
		List<Borrow> borrows=new ArrayList<>();
		String query="SELECT * FROM Borrow";
		
		try (Connection connection = DriverManager.getConnection(URL);
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(query)) {

	            while (resultSet.next()) {
	            	String studentid = resultSet.getString("借阅人ID");
	                String bookid = resultSet.getString("书籍ID");
	                String bookname = resultSet.getString("书名");
	                String author = resultSet.getString("作者");
	                String btime = resultSet.getString("借阅时间");
	                String ltime = resultSet.getString("应还时间");
	                int renew = resultSet.getInt("续借次数");

	                Borrow borrow = new Borrow(studentid,bookid,bookname,author,btime,
	                		ltime,renew);
	                borrows.add(borrow);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		return borrows;
	}
	
	//获取搜索借阅记录
		List<Borrow> getSearchBorrow(String id)
		{
			List<Borrow> borrows=new ArrayList<>();
		    String query = "SELECT * FROM Borrow WHERE 借阅人ID = ?";
		    
		    try (Connection connection = DriverManager.getConnection(URL);
		         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

		        preparedStatement.setString(1, id);
		        
		        try (ResultSet resultSet = preparedStatement.executeQuery()) {
		            while (resultSet.next()) {
		            	String studentid = resultSet.getString("借阅人ID");
		                String bookid = resultSet.getString("书籍ID");
		                String bookname = resultSet.getString("书名");
		                String author = resultSet.getString("作者");
		                String btime = resultSet.getString("借阅时间");
		                String ltime = resultSet.getString("应还时间");
		                int renew = resultSet.getInt("续借次数");

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
	
	//获取所有历史记录
	List<History> getHistory()
	{
		List<History> historys=new ArrayList<>();
		String query="SELECT * FROM History";
		
		try (Connection connection = DriverManager.getConnection(URL);
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(query)) {

	            while (resultSet.next()) {
	            	int ID = resultSet.getInt("ID");
	            	String studentid = resultSet.getString("借阅人ID");
	                String bookid = resultSet.getString("书籍ID");
	                String bookname = resultSet.getString("书名");
	                String author = resultSet.getString("作者");
	                String btime = resultSet.getString("借阅时间");
	                String ltime = resultSet.getString("归还时间");

	                History history = new History(ID,studentid,bookid,bookname,author,btime,
	                		ltime);
	                historys.add(history);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		return historys;
	}
	
	//获取搜索历史记录
			List<History> getSearchHistory(String id)
			{
				List<History> historys=new ArrayList<>();
			    String query = "SELECT * FROM History WHERE 借阅人ID = ?";
			    
			    try (Connection connection = DriverManager.getConnection(URL);
			         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			        preparedStatement.setString(1, id);
			        
			        try (ResultSet resultSet = preparedStatement.executeQuery()) {
			            while (resultSet.next()) {
			            	int ID = resultSet.getInt("ID");
			            	String studentid = resultSet.getString("借阅人ID");
			                String bookid = resultSet.getString("书籍ID");
			                String bookname = resultSet.getString("书名");
			                String author = resultSet.getString("作者");
			                String btime = resultSet.getString("借阅时间");
			                String ltime = resultSet.getString("归还时间");

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
			    String query1 = "UPDATE Borrow SET 续借次数 = 续借次数 + 1 WHERE 书籍ID = ? AND 借阅人ID = ?";
			    String query2 = "UPDATE Borrow SET 应还时间 = ? WHERE 书籍ID = ? AND 借阅人ID = ?";

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

	
	//借阅书籍
	boolean borrow(String bookid,String id) {
	    String query1 = "UPDATE Book SET 剩余库存 = 剩余库存 - 1 WHERE 书籍ID = ?";
	    String query2 = "SELECT * FROM Book WHERE 书籍ID = ?";
	    String query3 = "INSERT INTO Borrow (借阅人ID, 书籍ID, 书名, 作者, 借阅时间, 应还时间, 续借次数) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    String query4 = "INSERT INTO History (借阅人ID, 书籍ID, 书名, 作者, 借阅时间) VALUES (?, ?, ?, ?, ?)";
	    
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
	                    bookname = resultSet.getString("书名");
	                    author = resultSet.getString("作者");
	                    currentStock = resultSet.getInt("剩余库存");
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
	
	//归还书籍
	boolean returnBook(String bookid)
	{
		String query1 = "UPDATE Book SET 剩余库存 = 剩余库存 + 1 WHERE 书籍ID = ?";
	    String query2 = "DELETE * FROM Borrow WHERE 书籍ID = ?";
	    String query3 = "UPDATE History SET 归还时间 = ? WHERE 书籍ID = ?";
	    
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
