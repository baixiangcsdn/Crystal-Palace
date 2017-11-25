package com.chinasoft.guiwork;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class Register {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6C34\u6676\u5BAB\u6CE8\u518C\u754C\u9762");
		frame.setBounds(100, 100, 582, 404);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 566, 366);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u6210\u4E3A\u6C34\u6676\u5BAB\u7684\u4E00\u5458");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("华文彩云", Font.PLAIN, 18));
		lblNewLabel.setBounds(134, 76, 192, 28);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("\u6CE8\u518C\u7528\u6237\u540D:");
		label.setBounds(88, 166, 85, 15);
		panel.add(label);
		
		textField = new JTextField();
		textField.setBounds(183, 163, 117, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u6CE8\u518C\u5BC6\u7801:");
		label_1.setBounds(100, 204, 65, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801:");
		label_2.setBounds(100, 243, 65, 15);
		panel.add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(183, 201, 117, 21);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(183, 240, 117, 21);
		panel.add(passwordField_1);
		
		final JLabel label_3 = new JLabel("");
		label_3.setForeground(Color.RED);
		label_3.setBounds(326, 243, 136, 15);
		panel.add(label_3);
		
		JButton button = new JButton("\u6CE8\u518C");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user=textField.getText();
				String password=passwordField.getText();
				String password1=passwordField_1.getText();
				if(!password.equals(password1)){
					label_3.setText("两次密码输入不一致！");
				}else{
					
					String sql="insert into Member11 values(Sequence_Member.nextval,?,?,?,?)";
					OrSql s=new OrSql();
					try {
						s.setPreparedStatement(sql);
						s.getPs().setString(1, user);
						s.getPs().setString(2, password);
						s.getPs().setInt(3, 0);
						s.getPs().setInt(4, 0);
						s.getPs().executeUpdate();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					label_3.setText("注册成功！");
				}
			}
		});
		button.setBounds(197, 300, 93, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				Login ln;
				try {
					ln = new Login();
					ln.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button_1.setBounds(444, 32, 93, 23);
		panel.add(button_1);
		
		
	}
}
