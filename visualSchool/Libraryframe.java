package visualSchool;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.*;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.awt.event.ActionEvent;


import javax.swing.ImageIcon;
public class Libraryframe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	public String userType;
	public String id;
	//private JTable table;
   
	/**
	 * Launch the application.
	 */

public void bookshow() throws Exception {
	
	 JSONObject object = new JSONObject();
       object.put("num", 24);       
      
       SocketManager.sendData(object);
      String res=SocketManager.receiveData();
     
	JSONObject jsonObject = new JSONObject(res);
   JSONArray booksArray = jsonObject.getJSONArray("books");
   JTable table = new JTable() {  
       @Override  
       public boolean isCellEditable(int rowIndex, int columnIndex) {  
           return false; // 绂佹缂栬緫鎵�鏈夊崟鍏冩牸  
       }  
   };  
	 DefaultTableModel model =new DefaultTableModel(
		new Object[][] {
			
		},
		new String[] {
			"书籍ID", "类别", "书名", "作者", "出版社", "剩余库存","总库存", "价格"
		}
	);

	 for (int i = 0; i < booksArray.length(); i++) {
           JSONObject bookObject = booksArray.getJSONObject(i);
           model.addRow(new Object[]{
           		bookObject.getString("bookid"),
           		 bookObject.getString("category"),
           		bookObject.getString("bookname"),
           		bookObject.getString("author"),
           		bookObject.getString("publisher"),
           		bookObject.getInt("remain"),
                   bookObject.getInt("total"),	                              
                   bookObject.getDouble("price")
                   	                   	                    
           });
       }
	 table.setModel(model);
	table.getColumnModel().getColumn(0).setPreferredWidth(60);
	table.setBounds(69, 137, 873, 380);
	  JScrollPane scrollPane = new JScrollPane(table);
	  scrollPane.setSize(841, 362);
	  scrollPane.setLocation(96, 139);
	contentPane.add(scrollPane);
	
	
}
public void bookshowSearch(String res) throws Exception {
	
	 
	JSONObject jsonObject = new JSONObject(res);
  JSONArray booksArray = jsonObject.getJSONArray("books");
  JTable table = new JTable(){  
      @Override  
      public boolean isCellEditable(int rowIndex, int columnIndex) {  
          return false; // 绂佹缂栬緫鎵�鏈夊崟鍏冩牸  
      }  
  };  
	 DefaultTableModel model =new DefaultTableModel(
		new Object[][] {
			
		},
		new String[] {
			"\u4E66\u7C4DID", "\u7C7B\u522B", "\u4E66\u540D", "\u4F5C\u8005", "\u51FA\u7248\u793E", "\u603B\u5E93\u5B58", "\u5269\u4F59\u5E93\u5B58", "\u4EF7\u683C"
		}
	);
	 table.setModel(model);
	 for (int i = 0; i < booksArray.length(); i++) {
          JSONObject bookObject = booksArray.getJSONObject(i);
          model.addRow(new Object[]{
          		bookObject.getString("bookid"),
          		 bookObject.getString("category"),
          		bookObject.getString("bookname"),
          		bookObject.getString("author"),
          		bookObject.getString("publisher"),
          		bookObject.getInt("remain"),
                  bookObject.getInt("total"),	                              
                  bookObject.getDouble("price")
                  	                   	                    
          });
      }
	 table.setModel(model);
	table.getColumnModel().getColumn(0).setPreferredWidth(60);
	table.setBounds(69, 137, 873, 380);
	  JScrollPane scrollPane = new JScrollPane(table);
	  scrollPane.setSize(841, 362);
	  scrollPane.setLocation(96, 139);
	contentPane.add(scrollPane);
	
	
}
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
public Libraryframe(String userType,String id ) throws Exception {
	this.id=id;
	this.userType=userType;
	setTitle("图书管理");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 999, 700);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel lblNewLabel = new JLabel("书目检索:");
	lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
	lblNewLabel.setBounds(96, 90, 147, 37);
	contentPane.add(lblNewLabel);
	
	textField = new JTextField();
	textField.setBounds(265, 93, 241, 30);
	contentPane.add(textField);
	textField.setColumns(10);
	
	JButton btnNewButton = new JButton("搜索");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				SearchButton(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
	btnNewButton.setBounds(516, 90, 84, 37);
	contentPane.add(btnNewButton);
	
	JButton btnNewButton_1 = new JButton("我的图书馆");
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				MyLibButton(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
	btnNewButton_1.setBounds(134, 537, 304, 43);
	contentPane.add(btnNewButton_1);
	
	JButton btnNewButton_2 = new JButton("功能");
	btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			OrderButton(e);
		}
	});
	btnNewButton_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
	btnNewButton_2.setBounds(537, 537, 288, 43);
	contentPane.add(btnNewButton_2);
	
    bookshow();
	
	JLabel lblNewLabel_1 = new JLabel("书名");
	lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
	lblNewLabel_1.setBounds(204, 93, 84, 30);
	contentPane.add(lblNewLabel_1);
	
	JButton btnNewButton_3 = new JButton("添加");
	btnNewButton_3.setIcon(new ImageIcon(Libraryframe.class.getResource("/visualSchool/image/应用管理员管理.png")));
	btnNewButton_3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				AddButton(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	btnNewButton_3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
	btnNewButton_3.setBounds(804, 90, 133, 37);
	contentPane.add(btnNewButton_3);
	
	JLabel lblNewLabel_2 = new JLabel("管理操作:");
	lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
	lblNewLabel_2.setBounds(702, 90, 147, 37);
	contentPane.add(lblNewLabel_2);
	
	JButton btnNewButton_4 = new JButton("返回");
	btnNewButton_4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				bookshow();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	btnNewButton_4.setFont(new Font("微软雅黑", Font.PLAIN, 20));
	btnNewButton_4.setBounds(608, 90, 84, 37);
	contentPane.add(btnNewButton_4);
}

protected void SearchButton(ActionEvent e) throws Exception {
	// TODO Auto-generated method stub
	String bookname = textField.getText();
	
        JSONObject object = new JSONObject();
        object.put("num", 25);       
        object.put("bookname", bookname);
       
        SocketManager.sendData(object);
        String res=SocketManager.receiveData();
	     //if(res)
	     bookshowSearch(res);
	       
        
}

protected void AddButton(ActionEvent e) throws Exception {
	// TODO Auto-generated method stub
	if(userType.equals("admin")) {
	LibAdd lib=new LibAdd(this);
	lib.setVisible(true);}
	else
	{
		 showMessageDialog("操作无权限");
	}
}

private void showMessageDialog(String message) {
	// TODO Auto-generated method stub
	 JOptionPane.showMessageDialog(null, message, "提示", JOptionPane.INFORMATION_MESSAGE);
}
protected void OrderButton(ActionEvent e) {
	// TODO Auto-generated method stub
	LibOrder libOrder =new LibOrder(this);
	libOrder.setVisible(true);
}

protected void MyLibButton(ActionEvent e) throws Exception {
	// TODO Auto-generated method stub
	Mylib mylib = new Mylib(userType,id);
	mylib.setVisible(true);
	
}
}
