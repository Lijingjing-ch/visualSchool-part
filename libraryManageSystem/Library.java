package libraryManageSystem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
			    // Queries
			    String checkQuery = "SELECT 续借次数 FROM Borrow WHERE [书籍ID] = ? AND [借阅人ID] = ?";
			    String updateCountQuery = "UPDATE Borrow SET 续借次数 = 续借次数 + ? WHERE [书籍ID] = ? AND [借阅人ID] = ?";
			    String updateDueDateQuery = "UPDATE Borrow SET [应还时间] = ? WHERE [书籍ID] = ? AND [借阅人ID] = ?";

			    try (Connection connection = DriverManager.getConnection(URL);
			         PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
			         PreparedStatement updateCountStatement = connection.prepareStatement(updateCountQuery);
			         PreparedStatement updateDueDateStatement = connection.prepareStatement(updateDueDateQuery)) {

			        // Check current renewal count
			        checkStatement.setString(1, bookid);
			        checkStatement.setString(2, id);
			        ResultSet resultSet = checkStatement.executeQuery();

			        if (resultSet.next()) {
			            int renewalCount = resultSet.getInt("续借次数");

			            // Check if the renewal count has reached the limit
			            if (renewalCount >= 2) {
			                // Already reached the maximum renewal count
			                return false;
			            }

			            // Update the renewal count
			            updateCountStatement.setInt(1, 1);
			            updateCountStatement.setString(2, bookid);
			            updateCountStatement.setString(3, id);
			            updateCountStatement.executeUpdate();

			            // Calculate new due date
			            LocalDate today = LocalDate.now();
			            LocalDate futureDate = today.plusDays(30);
			            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
			            String formattedDate = futureDate.format(formatter);

			            // Update the due date
			            updateDueDateStatement.setString(1, formattedDate);
			            updateDueDateStatement.setString(2, bookid);
			            updateDueDateStatement.setString(3, id);
			            updateDueDateStatement.executeUpdate();

			            return true;
			        } else {
			            // No record found for the given book ID and user ID
			            return false;
			        }

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
	    String query4 = "INSERT INTO History (借阅人ID, 书籍ID, 书名, 作者, 借阅时间, 归还时间) VALUES (?, ?, ?, ?, ?, ?)";
	    
	    if(checkBorrow(bookid,id))
	    	return false;
	    
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
	            preparedStatement.setDate(6, null);
	            preparedStatement.executeUpdate();
	        }
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	        // Additional error handling or rollback could be added here if needed
	    }
	}
	
	//check whether borrow or not
	boolean checkBorrow(String bookid, String id) {
	    String query = "SELECT * FROM Borrow WHERE 书籍ID = ? AND 借阅人ID = ?";
	    try (Connection connection = DriverManager.getConnection(URL)) {
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, bookid);
	            preparedStatement.setString(2, id);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                return resultSet.next();
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Additional error handling could be added here
	    }
	    return false;
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
	
	//display e-books
	List<E_book> getEbooks() {
	    List<E_book> books = new ArrayList<>();
	    String query = "SELECT * FROM E_book";  // Ensure this matches the actual table name
	    
	    try (Connection connection = DriverManager.getConnection(URL);
	         Statement statement = connection.createStatement();
	         ResultSet resultSet = statement.executeQuery(query)) {

	        while (resultSet.next()) {
	            String bookid = resultSet.getString("电子书ID");
	            String category = resultSet.getString("电子书类别");
	            String bookname = resultSet.getString("电子书名");
	            String author = resultSet.getString("电子书作者");
	            String filepath = resultSet.getString("文件路径");  // Corrected column name
	            
	            E_book book = new E_book(bookid, category, bookname, author, filepath);
	            books.add(book);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return books;
	}

	
	// display an e-book content
	public String readFile(String bookid) throws UnsupportedEncodingException, FileNotFoundException, IOException {
	    String query = "SELECT * FROM E_book WHERE 电子书ID = ?";
	    String filePath = null;  // Declare filePath outside the try block

	    try (Connection connection = DriverManager.getConnection(URL);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, bookid);
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                filePath = resultSet.getString("文件路径");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;  // Return null or handle it as needed
	    }

	    if (filePath == null) {
	        throw new FileNotFoundException("File path not found for bookid: " + bookid);
	    }

	    StringBuilder content = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            content.append(line).append(System.lineSeparator());
	        }
	    }
	    return content.toString();
	}

	public List<String> getRemark(String bookid)
	{
		List<String> remarks=new ArrayList<>();
	    String query = "SELECT * FROM Remark WHERE 书籍ID = ?";
	    
	    try (Connection connection = DriverManager.getConnection(URL);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, bookid);
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	            	String remark = resultSet.getString("书评");
	                
	                remarks.add(remark);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return remarks;
	}
	
	public boolean addRemark(String bookid,String remark)
	{
        String query = "INSERT INTO Remark (书籍ID, 书评) VALUES (?, ?)";
	    
	    try (Connection connection = DriverManager.getConnection(URL);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, bookid);
	        preparedStatement.setString(2, remark);
	        
	        preparedStatement.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
}
