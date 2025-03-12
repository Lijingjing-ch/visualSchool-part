package visualSchool;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;


import javax.swing.SwingConstants;




import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;


import javax.swing.SwingConstants;

public class Mylib extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel borrowPane= new JPanel();
	private JPanel historyPane= new JPanel();
	private JPanel contentPane=new JPanel();
	private CardLayout cardLayout= new CardLayout();  
	
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private JTable table;
	public String userType;
	public String id;
	public String bookid="";
	

	
public void Historyshow(String id) throws Exception {
    JSONObject object = new JSONObject();
    if(userType.equals("admin"))
    {
    object.put("num", 29);
 
    }else
    {
    	
    	object.put("num", 58);
        object.put("id", id);
    }
    SocketManager.sendData(object);
    String res=SocketManager.receiveData();
    
	JSONObject jsonObject = new JSONObject(res);
   JSONArray booksArray = jsonObject.getJSONArray("books");
  
	 DefaultTableModel model =new DefaultTableModel(
		new Object[][] {
			
		},
		new String[] {
				 "\u4E66\u7C4DID", "\u4E66\u540D", "\u4F5C\u8005", "\u501F\u9605\u65F6\u95F4", "\u5F52\u8FD8\u65F6\u95F4"
		}
	);
	 
	 for (int i = 0; i < booksArray.length(); i++) {
           JSONObject bookObject = booksArray.getJSONObject(i);
           model.addRow(new Object[]{
        			
           		bookObject.getString("bookid"),           	
           		bookObject.getString("bookname"),
           		bookObject.getString("author"),
           		bookObject.getString("btime"),
           	    bookObject.getString("ltime"),           		
           	
           		
                   	                   	                    
           });
       }
	 table_1.setModel(model);
	table_1.getColumnModel().getColumn(1).setPreferredWidth(65);
	table_1.getColumnModel().getColumn(2).setPreferredWidth(65);
	table_1.getColumnModel().getColumn(3).setPreferredWidth(85);
	table_1.getColumnModel().getColumn(4).setPreferredWidth(85);
	
	show("history");
	
}	
public void Borrowshow(String id) throws Exception {
    JSONObject object = new JSONObject();
    if(userType.equals("admin"))
    {
    object.put("num", 28);
 
    }else
    {
    	
    	object.put("num", 57);
        object.put("id", id);
    }
    SocketManager.sendData(object);
    String res=SocketManager.receiveData();
    
	JSONObject jsonObject = new JSONObject(res);
   JSONArray booksArray = jsonObject.getJSONArray("books");
 
	 DefaultTableModel model =new DefaultTableModel(
		new Object[][] {
			
		},
		new String[] {
			"书籍ID", "\u4E66\u540D", "\u4F5C\u8005", "\u501F\u9605\u65F6\u95F4", "\u5E94\u8FD8\u65F6\u95F4", "\u7EED\u501F\u6B21\u6570"
		}
	);
	 
	 for (int i = 0; i < booksArray.length(); i++) {
           JSONObject bookObject = booksArray.getJSONObject(i);
           model.addRow(new Object[]{
           		bookObject.getString("bookid"),           	
           		bookObject.getString("bookname"),
           		bookObject.getString("author"),
           		bookObject.getString("btime"),
           	    bookObject.getString("ltime"),           		
           		bookObject.getInt("renew")
           		
                   	                   	                    
           });
       }
	 table.setModel(model);
	table.getColumnModel().getColumn(1).setPreferredWidth(65);
	table.getColumnModel().getColumn(2).setPreferredWidth(65);
	table.getColumnModel().getColumn(3).setPreferredWidth(85);
	table.getColumnModel().getColumn(4).setPreferredWidth(85);
	
	show("borrow");
}


private void show(String name)
 {
	  CardLayout cardLayout = (CardLayout) contentPane.getLayout();
	    cardLayout.show(contentPane, name);
 }
 
 
 
 
	public Mylib(String userType,String id) throws Exception {
		this.id=id;
		this.userType=userType;
		setTitle("我的图书馆");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 999, 700);
		
		

		setContentPane(contentPane);
		 contentPane.setLayout(cardLayout);
		 
		  table = new JTable(){  
		       @Override  
		       public boolean isCellEditable(int rowIndex, int columnIndex) {  
		           return false; // 禁止编辑所有单元格  
		       }  
		   };  
			scrollPane = new JScrollPane(table);
			scrollPane.setBounds(167, 97, 794, 509);			
			scrollPane.setViewportView(table); 			
			borrowPane.add(scrollPane);
			contentPane.add(borrowPane,"borrow");
			table.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			        int row = table.rowAtPoint(e.getPoint());
			        if (row != -1) {
			            bookid = (String) table.getValueAt(row, 0); // 假设bookid在第0列
			            // 在这里处理bookId，例如将其存储在一个全局变量中
			        }
			    }
			});
			
			 
			   table_1 = new JTable(){  
			       @Override  
			       public boolean isCellEditable(int rowIndex, int columnIndex) {  
			           return false; // 禁止编辑所有单元格  
			       }  
			   };  
			   scrollPane_1 = new JScrollPane(table_1);
				scrollPane_1.setBounds(167, 97, 794, 509);				
				scrollPane_1.setViewportView(table_1);
				historyPane.add(scrollPane_1);
				contentPane.add(historyPane,"history");
			   	
		JButton btnNewButton_1 = new JButton("续借");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BorrowMore(e);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton_1.setBounds(779, 32, 182, 44);
		borrowPane.add(btnNewButton_1);
		


		
		
		
		JButton btnNewButton_2 = new JButton("借阅记录");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BorrowButton(e);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton_2.setBounds(39, 97, 129, 38);
		historyPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("历史记录");
		btnNewButton_2_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HistoryButton(e);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2_1.setBounds(39, 133, 129, 38);
		borrowPane.add(btnNewButton_2_1);
		Borrowshow(id);
		
	}

	protected void BorrowMore(ActionEvent e) throws Exception {
		// TODO Auto-generated method stub

		
		 JSONObject object = new JSONObject();
		    object.put("num", 30);
		    object.put("id", id);
		    if(bookid.equals(""))
			{
				 showMessageDialog1("请选择书籍所在行");
			}	
		    else
		    { object.put("bookid", bookid);
		    bookid="";
		    SocketManager.sendData(object);
		    SocketManager.receiveData();}

		 
	}
	
	private void showMessageDialog1(String message) {
		// TODO Auto-generated method stub
		 JOptionPane.showMessageDialog(null, message, "提示", JOptionPane.INFORMATION_MESSAGE);
	}

	protected void BorrowButton(ActionEvent e) throws Exception {
		// TODO Auto-generated method stub
		Borrowshow(id);
	}

	protected void HistoryButton(ActionEvent e) throws Exception {
		// TODO Auto-generated method stub
		

		

	        Historyshow(id);
	}
	
}


