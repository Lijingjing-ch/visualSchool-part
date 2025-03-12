package libraryManageSystem;

public class Book {
	
	private String bookID;
	private String category;
	private String bookname;
	private String author;
	private String publisher;
	private int remain;
	private int total;
	private double price;
	
	public Book(String bookID,String category,String bookname,
			String author,String publisher,int remain,int total,double price)
	{
		this.bookID=bookID;
		this.category=category;
		this.bookname=bookname;
		this.author=author;
		this.publisher=publisher;
		this.remain=remain;
		this.total=total;
		this.price=price;
	}
	
	public String getBookID()
	{
		return bookID;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public String getBookname()
	{
		return bookname;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public String getPublisher()
	{
		return publisher;
	}
	
	public int getRemain()
	{
		return remain;
	}
	
	public int getTotal()
	{
		return total;
	}
	
	public double getPrice()
	{
		return price;
	}
}
