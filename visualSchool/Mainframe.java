package visualSchool;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;




import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mainframe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public String userType;
	public String id;
	private JTextField textField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Mainframe(String userType,String id ) {
		setTitle("虚拟校园");
		
		this.userType=userType;
		this.id=id;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 999, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(88, 148, 248));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("虚拟校园");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel.setBounds(435, 0, 132, 58);
		contentPane.add(lblNewLabel);
		
		
		JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 42, 987, 538);
        contentPane.add(layeredPane);

        // 加载图片并添加到JLayeredPane中
        ImageIcon imageIcon = new ImageIcon("/visualSchool/image/东南大学.jpg");
        JLabel imageLabel = new JLabel(new ImageIcon(Mainframe.class.getResource("/visualSchool/image/东南大学2.jpg")));
        imageLabel.setBounds(0, 21, 988, 516);
        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);
        
        JButton btnNewButton = new JButton("学籍管理系统");
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(97, 236, 239));
        btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        btnNewButton.setBounds(39, 608, 147, 47);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("选课系统");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					CourseButton(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        	}
        });
        btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.setBackground(new Color(16, 239, 95));
        btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        btnNewButton_1.setBounds(292, 608, 147, 47);
        contentPane.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("图书馆");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					libraryButton(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnNewButton_2.setForeground(new Color(255, 255, 255));
        btnNewButton_2.setBackground(new Color(192, 192, 192));
        btnNewButton_2.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        btnNewButton_2.setBounds(542, 608, 140, 47);
        contentPane.add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("商店");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		shopButton(e);
        	}
        });
        btnNewButton_3.setForeground(new Color(255, 255, 255));
        btnNewButton_3.setBackground(new Color(243, 186, 107));
        btnNewButton_3.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        btnNewButton_3.setBounds(785, 608, 132, 47);
        contentPane.add(btnNewButton_3);
        
        btnNewButton.setFocusable(false);
		btnNewButton_1.setFocusable(false);
		btnNewButton_2.setFocusable(false);
		btnNewButton_3.setFocusable(false);
		
		textField = new JTextField();
		textField.setBackground(new Color(210, 210, 210));
		textField.setBounds(-16, 577, 1016, 102);
		contentPane.add(textField);
		textField.setColumns(10);
	}

	protected void CourseButton(ActionEvent e) throws Exception {
		// TODO Auto-generated method stub
		setVisible(true);
	}

	protected void shopButton(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//this.dispose();
	}

	protected void libraryButton(ActionEvent e) throws Exception {
		// TODO Auto-generated method stub
		
		
	      Libraryframe frame = new Libraryframe(userType,id);
			frame.setVisible(true);
	}
}
