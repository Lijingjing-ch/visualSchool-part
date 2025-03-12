package visualSchool;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;



import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class LibAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private  Libraryframe s;
    
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public LibAdd(Libraryframe s) {
		this.s=s;
		setTitle("图书管理");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setText("");
		textField.setBounds(97, 46, 141, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("");
		textField_1.setColumns(10);
		textField_1.setBounds(97, 80, 141, 21);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText("");
		textField_2.setColumns(10);
		textField_2.setBounds(97, 111, 141, 21);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setText("");
		textField_3.setColumns(10);
		textField_3.setBounds(97, 142, 141, 21);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setText("");
		textField_4.setColumns(10);
		textField_4.setBounds(97, 173, 141, 21);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setText("");
		textField_5.setColumns(10);
		textField_5.setBounds(97, 204, 141, 21);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setText("");
		textField_6.setColumns(10);
		textField_6.setBounds(97, 15, 141, 21);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setText("");
		textField_7.setColumns(10);
		textField_7.setBounds(97, 239, 141, 21);
		contentPane.add(textField_7);
		
		JLabel lblNewLabel = new JLabel("书籍ID：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 15, 66, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("书名：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 80, 66, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("作者：");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(10, 111, 66, 21);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("类别：");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(10, 46, 91, 21);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("出版社：");
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(10, 145, 66, 21);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("剩余库存：");
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(10, 176, 91, 21);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("总库存：");
		lblNewLabel_6.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(10, 207, 66, 21);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("价格：");
		lblNewLabel_7.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(10, 237, 66, 21);
		contentPane.add(lblNewLabel_7);
		
		btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MessagePassButton(e);
				}  catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnNewButton.setBounds(299, 45, 93, 31);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_6.setText("");
				textField_1.setText("");
				textField.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_7.setText("");
			}
		});
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnNewButton_1.setBounds(299, 163, 93, 31);
		contentPane.add(btnNewButton_1);
	}

	protected void MessagePassButton(ActionEvent e) throws Exception {
		// TODO Auto-generated method stub
		  // 在这里获取文本框的值并处理
        String bookId = textField_6.getText();
        String bookName = textField_1.getText();
        String author = textField_2.getText();
        String category = textField.getText();
        String publisher = textField_3.getText();
        int remainingStock = Integer.parseInt(textField_4.getText());
        int totalStock = Integer.parseInt(textField_5.getText());
        int price = Integer.parseInt(textField_7.getText());

       
      

        JSONObject object = new JSONObject();
        object.put("num", 26);
        object.put("bookid", bookId);
        object.put("bookname", bookName);
        object.put("author", author);
        object.put("category", category);
        object.put("publisher", publisher);
        object.put("remain", remainingStock);
        object.put("total", totalStock);
        object.put("price", price);
     
        SocketManager.sendData(object);
        String isSuccess = SocketManager.receiveData();
        System.out.println(isSuccess);
        JSONObject jsonObject = new JSONObject(isSuccess);
        
        String opr = jsonObject.getString("opr");
        if (opr.equals("true")) {
            showMessageDialog("操作成功");
            s.bookshow();
        } else {
            showMessageDialog("操作失败");
        }
      
}
private void showMessageDialog(String message) {
    JOptionPane.showMessageDialog(null, message, "提示", JOptionPane.INFORMATION_MESSAGE);
}
       
	

}
