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
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.io.ObjectOutputStream;
import java.net.*;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;


public class Loginframe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField adminName;
	private JPasswordField adminPsd;
    private JComboBox adminTpeCom;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loginframe frame = new Loginframe();
					//frame.pack(); // 浣跨敤 pack() 鏂规硶鏇夸唬 setVisible(true)
	                frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Loginframe() {
		setTitle("登录页面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("学生信息管理系统");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel.setBounds(92, 10, 256, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("用户名：");
		lblNewLabel_1.setIcon(new ImageIcon(Loginframe.class.getResource("/visualSchool/image/用户名.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(94, 81, 72, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("密码：");
		lblNewLabel_2.setIcon(new ImageIcon(Loginframe.class.getResource("/visualSchool/image/密码.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(92, 119, 74, 21);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("用户类型：");
		lblNewLabel_3.setIcon(new ImageIcon(Loginframe.class.getResource("/visualSchool/image/用户类型.png")));
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(94, 157, 85, 20);
		contentPane.add(lblNewLabel_3);
		
		adminName = new JTextField();
		adminName.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		adminName.setBounds(175, 79, 134, 21);
		contentPane.add(adminName);
		adminName.setColumns(10);
		
		adminPsd = new JPasswordField();
		adminPsd.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		adminPsd.setBounds(176, 117, 133, 21);
		contentPane.add(adminPsd);
		
		adminTpeCom = new JComboBox();
		adminTpeCom.setModel(new DefaultComboBoxModel(new UserType [] {UserType.STUDENT, UserType.ADMIN, UserType.TEACHER}));
		adminTpeCom.setBounds(175, 154, 134, 23);
		contentPane.add(adminTpeCom);
		
		JButton btnNewButton = new JButton("注册");
		btnNewButton.setIcon(new ImageIcon(Loginframe.class.getResource("/visualSchool/image/注册.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterButton(e);
			}
		});
		btnNewButton.setBounds(26, 213, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetButton(ae);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Loginframe.class.getResource("/visualSchool/image/重置.png")));
		btnNewButton_1.setBounds(129, 213, 93, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("登录");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmButton(e);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(Loginframe.class.getResource("/visualSchool/image/登录.png")));
		btnNewButton_2.setBounds(232, 213, 93, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("取消");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(Loginframe.class.getResource("/visualSchool/image/取消.png")));
		btnNewButton_3.setBounds(335, 213, 93, 23);
		contentPane.add(btnNewButton_3);
		
		btnNewButton.setFocusable(false);
		btnNewButton_1.setFocusable(false);
		btnNewButton_2.setFocusable(false);
		btnNewButton_3.setFocusable(false);
		
		setLocationRelativeTo(null);
		
		
	}

	protected void RegisterButton(ActionEvent e) {
		// TODO Auto-generated method stub
		String name=this.adminName.getText();
		String password=this.adminPsd.getText();
		
		UserType userType=(UserType)this.adminTpeCom.getSelectedItem();
		String user=userType.getName();
		if("学生".equals(userType.getName())) {
			
			try {
			
	            JSONObject object = new JSONObject();
	            object.put("num", 22);
	            object.put("id", name);
	            object.put("psw", password);
	            object.put("type", "student");
	           
	            SocketManager.sendData(object);
	            String data=SocketManager.receiveData();
	            JSONObject jsonObject = new JSONObject(data);
	            
	            String opr = jsonObject.getString("opr");
	            if (opr.equals("true")) {
	            	Mainframe mainframe = new Mainframe("student",name);
	    			mainframe.setVisible(true);
	    			this.dispose();
		         
		        } else {
		        	JOptionPane.showMessageDialog(this, "用户名或密码错误");
					return;
		        }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "连接服务器失败");
	        }
			//AdminDao adminDao =new AdminDao();
		
		}else if("系统管理员".equals(userType.getName())) {
			
			try {
				
	            JSONObject object = new JSONObject();
	            object.put("num", 22);
	            object.put("id", name);
	            object.put("psw", password);
	            object.put("type", "admin");
	        
	            SocketManager.sendData(object);
	            String data=SocketManager.receiveData();
	            JSONObject jsonObject = new JSONObject(data);
	            
	            String opr = jsonObject.getString("opr");
	            if (opr.equals("true")) {
	            	Mainframe mainframe = new Mainframe("admin",name);
	    			mainframe.setVisible(true);
	    			this.dispose();
		         
		        } else {
		        	JOptionPane.showMessageDialog(this, "用户名或密码错误");
					return;
		        }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "连接服务器失败");
	        }
			
		}else {
			
			try {
				
	            JSONObject object = new JSONObject();
	            object.put("num", 22);
	            object.put("id", name);
	            object.put("psw", password);
	            object.put("type", "teacher");
	          
	          
	            SocketManager.sendData(object);
	            String data=SocketManager.receiveData();
	            JSONObject jsonObject = new JSONObject(data);
	            
	            String opr = jsonObject.getString("opr");
	            if (opr.equals("true")) {
	            	Mainframe mainframe = new Mainframe("teacher",name);
	    			mainframe.setVisible(true);
	    			this.dispose();
		         
		        } else {
		        	JOptionPane.showMessageDialog(this, "用户名或密码错误");
					return;
		        }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "连接服务器失败");
	        }
			//AdminDao adminDao =new AdminDao();
		
		}
	}


	

	protected void confirmButton(ActionEvent e) {
		// TODO Auto-generated method stub
		String name=this.adminName.getText();
		String password=this.adminPsd.getText();
		
		UserType userType=(UserType)this.adminTpeCom.getSelectedItem();
		String user=userType.getName();
		if("学生".equals(userType.getName())) {
			
			try {
			
	            JSONObject object = new JSONObject();
	            object.put("num", 23);
	            object.put("id", name);
	            object.put("psw", password);
	            object.put("type", "student");
	            
	            SocketManager.sendData(object);
	            String data=SocketManager.receiveData();
	            JSONObject jsonObject = new JSONObject(data);
	            
	            String opr = jsonObject.getString("opr");
	            if (opr.equals("true")) {
	            	Mainframe mainframe = new Mainframe("student",name);
	    			mainframe.setVisible(true);
	    			this.dispose();
		         
		        } else {
		        	JOptionPane.showMessageDialog(this, "用户名或密码错误");
					return;
		        }
	            
	           // SocketManager.receiveData();
	            } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "连接服务器失败");
	        }
			
		}else if("系统管理员".equals(userType.getName())) {
			
			try {
				
	            JSONObject object = new JSONObject();
	            object.put("num", 23);
	            object.put("id", name);
	            object.put("psw", password);
	            object.put("type", "admin");
	     
	            SocketManager.sendData(object);
	            String data=SocketManager.receiveData();
	            JSONObject jsonObject = new JSONObject(data);
	            
	            String opr = jsonObject.getString("opr");
	            if (opr.equals("true")) {
	            	Mainframe mainframe = new Mainframe("admin",name);
	    			mainframe.setVisible(true);
	    			this.dispose();
		         
		        } else {
		        	JOptionPane.showMessageDialog(this, "用户名或密码错误");
					return;
		        }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "连接服务器失败");
	        }
			
		}else {
			
			try {
				
	            JSONObject object = new JSONObject();
	            object.put("num", 23);
	            object.put("id", name);
	            object.put("psw", password);
	            object.put("type", "teacher");
	           
	            SocketManager.sendData(object);
	            String data=SocketManager.receiveData();
	            System.out.println(data);
	            JSONObject jsonObject = new JSONObject(data);
	            
	            String opr = jsonObject.getString("opr");
	            if (opr.equals("true")) {
	            	Mainframe mainframe = new Mainframe("teacher",name);
	    			mainframe.setVisible(true);
	    			this.dispose();
		         
		        } else {
		        	JOptionPane.showMessageDialog(this, "用户名或密码错误");
					return;
		        }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "连接服务器失败");
	        }
			
		}
	}

	protected void resetButton(ActionEvent ae) {
		// TODO Auto-generated method stub
		this.adminName.setText("");
		this.adminPsd.setText("");
		this.adminTpeCom.setSelectedIndex(0);
	}
}
