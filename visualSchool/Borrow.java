package visualSchool;

public class Borrow {
	
	private String studentID;
	private String bookID;
	private String bookname;
	private String author;
	private String btime;
	private String ltime;
	private int renew;
	
	public Borrow(String studentID,String bookID,String bookname,String author,
			String btime,String ltime,int renew)
	{
		this.studentID=studentID;
		this.bookID=bookID;
		this.bookname=bookname;
		this.author=author;
		this.btime=btime;
		this.ltime=ltime;
		this.renew=renew;
	}
	
	public String getStudentID()
	{
		return studentID;
	}
	
	public String getBookID()
	{
		return bookID;
	}
	
	public String getBookname()
	{
		return bookname;
	}

	public String getAuthor()
	{
		return author;
	}
	
	public String getBtime()
	{
		return btime;
	}
	
	public String getLtime()
	{
		return ltime;
	}
	
	public int getRenew()
	{
		return renew;
	}
}
