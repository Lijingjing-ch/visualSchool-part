package visualSchool;

public class History {
	
	private int ID;
	private String studentID;
	private String bookID;
	private String bookname;
	private String author;
	private String btime;
	private String ltime;
	
	public History(int ID,String studentID,String bookID,String bookname,String author,
			String btime,String ltime)
	{
		this.ID=ID;
		this.studentID=studentID;
		this.bookID=bookID;
		this.bookname=bookname;
		this.author=author;
		this.btime=btime;
		this.ltime=ltime;
	}
	
	public int getID()
	{
		return ID;
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

}
