package visualSchool;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;



import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class LibOrder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
    private  Libraryframe s;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public LibOrder(Libraryframe s) {
		this.s=s;
		setTitle("功能");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("书籍ID：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblNewLabel.setBounds(41, 32, 90, 29);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(145, 32, 191, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("借阅");
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnNewButton.setBounds(25, 186, 93, 36);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("归还");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BackButton(e);
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
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnNewButton_1.setBounds(174, 186, 93, 36);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("删除");
		btnNewButton_1_1.setIcon(new ImageIcon(LibOrder.class.getResource("/visualSchool/image/应用管理员管理.png")));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DelButton(e);
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
		btnNewButton_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnNewButton_1_1.setBounds(314, 186, 114, 36);
		contentPane.add(btnNewButton_1_1);
	}

	protected void DelButton(ActionEvent e) throws Exception {
		// TODO Auto-generated method stub
		if(s.userType.equals("admin"))
		{
		String bookID = textField.getText();
		

	        JSONObject object = new JSONObject();
	        object.put("num", 27);       
	        object.put("bookid", bookID);
	     
	        SocketManager.sendData(object);
	        String isSuccess = SocketManager.receiveData();
	        System.out.println(isSuccess);
	        JSONObject jsonObject = new JSONObject(isSuccess);
	        s.bookshow();
	        String opr = jsonObject.getString("opr");
	        if (opr.equals("true")) {
	            showMessageDialog1("操作成功");
	            s.bookshow();
	        } else {
	            showMessageDialog1("操作失败");
	        }
		}else
		{
			 showMessageDialog1("操作无权限");
		}
	}

	private void showMessageDialog1(String message) {
		// TODO Auto-generated method stub
		 JOptionPane.showMessageDialog(null, message, "提示", JOptionPane.INFORMATION_MESSAGE);
	}
		


	protected void BackButton(ActionEvent e) throws Exception {
		// TODO Auto-generated method stub
		
		String bookID = textField.getText();
		

	        JSONObject object = new JSONObject();
	        object.put("num", 32);       
	        object.put("bookid", bookID);
	        object.put("id", s.id);
	       
	        SocketManager.sendData(object);
	        SocketManager.receiveData();
	        String isSuccess = SocketManager.receiveData();
	        System.out.println(isSuccess);
	        JSONObject jsonObject = new JSONObject(isSuccess);
	        
	        String opr = jsonObject.getString("opr");
	        if (opr.equals("true")) {
	            showMessageDialog1("操作成功");
	            s.bookshow();
	        } else {
	            showMessageDialog1("操作失败");
	        }
	        s.bookshow();
	      
	}

	protected void BorrowButton(ActionEvent e) throws Exception {
		// TODO Auto-generated method stub
		String bookID = textField.getText();
	

	        JSONObject object = new JSONObject();
	        object.put("num", 31);       
	        object.put("bookid", bookID);
	        object.put("id", s.id);
	        
	   
	        SocketManager.sendData(object);
	        
	        String isSuccess = SocketManager.receiveData();
	        System.out.println(isSuccess);
	        JSONObject jsonObject = new JSONObject(isSuccess);
	        
	        String opr = jsonObject.getString("opr");
	        if (opr.equals("true")) {
	            showMessageDialog1("操作成功");
	            s.bookshow();
	        } else {
	            showMessageDialog1("操作失败");
	        }
	        s.bookshow();
	}
	
}




// ...

