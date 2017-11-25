package com.chinasoft.guiwork;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public Login() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setTitle("\u6C34\u6676\u5BAB\u4F1A\u5458\u767B\u5F55\u754C\u9762");
		frame.setBounds(300, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 984, 762);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		Picture pic=new Picture();
		
	    JLabel label=new JLabel();	   
	    label.setBounds(0, 0, 984, 762);
	    label.setHorizontalAlignment(0);
	    label.setIcon(pic.getIco("002"));	  
	    panel.add(label);
	    
	    JLabel label_1=new JLabel();	   
	    label_1.setBounds(399, 130, 186, 200);
	    label_1.setHorizontalAlignment(0);
	    label_1.setIcon(pic.getIco("000"));	  
	    label.add(label_1);
	    
	    JLabel label_2 = new JLabel("\u6C34\u6676\u5BAB", 0);
	    label_2.setForeground(Color.YELLOW);
	    label_2.setFont(new Font("»ªÎÄÐÂÎº", Font.PLAIN, 36));
	    label_2.setBounds(73, 0, 113, 120);
	    label_1.add(label_2);
	    
	    JLabel label_3 = new JLabel("\u7528\u6237\u540D:");
	    label_3.setFont(new Font("·ÂËÎ", Font.BOLD, 16));
	    label_3.setBounds(390, 353, 66, 22);
	    label.add(label_3);
		
	    JLabel label_4 = new JLabel("\u5BC6  \u7801:");
	    label_4.setFont(new Font("·ÂËÎ", Font.BOLD, 16));
	    label_4.setBounds(390, 388, 66, 22);
	    label.add(label_4);
	    
	    textField = new JTextField();
	    textField.setToolTipText("\u6570\u5B57\u3001\u5B57\u6BCD\u3001\u4E0B\u5212\u7EBF\u7EC4\u5408\uFF0C\u4E0D\u8D85\u8FC710\u4F4D");
	    textField.setBounds(465, 355, 120, 21);
	    label.add(textField);
	    textField.setColumns(10);
	    
	    passwordField = new JPasswordField();
	    passwordField.setToolTipText("\u4E0D\u5F97\u5305\u542B\u6C49\u5B57\u53CA\u975E\u952E\u76D8\u7B26\uFF0C\u81F3\u5C116\u4F4D");
	    passwordField.setBounds(465, 390, 120, 21);
	    label.add(passwordField);
	    
	    final JRadioButton radioButton = new JRadioButton("\u7BA1\u7406\u5458");
	    radioButton.setBackground(new Color(0, 0, 0, 1));
	    radioButton.setBounds(401, 433, 70, 23);
	    label.add(radioButton);
	    
	    JRadioButton radioButton_1 = new JRadioButton("\u4F1A\u5458");
	    radioButton_1.setBackground(new Color(0, 0, 0, 1));
	    radioButton_1.setBounds(493, 433, 70, 23);
	    radioButton_1.setSelected(true);
	    label.add(radioButton_1);
	    
	    ButtonGroup bg=new ButtonGroup();
	    bg.add(radioButton);
	    bg.add(radioButton_1);
	    
	    JButton button = new JButton("\u767B\u5F55");
	    button.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		frame.setVisible(false);
	    		OrSql s=new OrSql();
	    		String user=textField.getText();
	    		String password=passwordField.getText();
	    		boolean tf=radioButton.isSelected();
	    		if(tf){
	    			s.setPASSWORD(user);
		    		s.setPASSWORD(password);
		    		String sql="select * from dept";
		    		try{
		    			s.setPreparedStatement(sql);
		    			System.out.println("pass");
		    		}catch(Exception ex){
		    			LoginError le=new LoginError();
		    			le.main(null);
		    		}
	    		}else{
	    			String sql="select * from member11 where m_name=? and m_pwd=?";
	    			try {
						s.setPreparedStatement(sql);
						s.getPs().setString(1, user);
		    			s.getPs().setString(2, password);
		    			ResultSet rs=s.getPs().executeQuery();
		    			if(rs.next()){
		    				Main mt=new Main();
		    				mt.setUserid(rs.getInt(1));
		    				mt.main(null);
		    			}else{
		    				LoginError le=new LoginError();
			    			le.main(null);
		    			}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		}
	    			    		
	    	}
	    });
	    button.setBounds(392, 493, 93, 23);
	    label.add(button);
	    
	    JButton button_1 = new JButton("\u6CE8\u518C");
	    button_1.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		frame.setVisible(false);
	    		new Register().main(null);
	    	}
	    });
	    button_1.setBounds(500, 493, 93, 23);
	    label.add(button_1);
	    
	   
	}
}
