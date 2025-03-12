package libraryManageSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		Library li = new Library();
		//Book b = new Book("13922007","小说","aaa","bbb","ccc",20,30,40);
		//System.out.println(li.borrow("05822002","09022313"));
        
        //System.out.println(li.borrow("05822004", "1002"));
		/*List<E_book> books = li.getEbooks();
		for(E_book book : books)
		{
			System.out.println(book.getFilepath());
		}*/
		System.out.println(li.readFile("00522001"));
		
		/*List<String> remarks = li.getRemark("13922003");
		for(String remark : remarks)
		{
			System.out.println(remark);
		}
		
		li.addRemark("13922004", "我要吊死在余华家门口");
		*/
	}

}
