package libraryManageSystem;

public class E_book {
	
	private String bookid;
	private String category;
	private String bookname;
	private String author;
	private String filepath;
	
	public E_book(String bookid,String category,String bookname,String author,String filepath)
	{
		this.bookid=bookid;
		this.category=category;
		this.bookname=bookname;
		this.author=author;
		this.filepath=filepath;
		
	}
	
	public String getBookid()
	{
		return bookid;
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
	
	public String getFilepath()
	{
		return filepath;
	}

}
