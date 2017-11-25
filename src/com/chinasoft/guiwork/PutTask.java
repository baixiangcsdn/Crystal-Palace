package com.chinasoft.guiwork;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class PutTask {

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;
	private static int userid;
	public static void setUserid(int userid) {
		PutTask.userid = userid;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PutTask window = new PutTask();
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
	public PutTask() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setTitle("\u6C34\u6676\u5BAB\u4F1A\u5458\u4E0A\u4F20\u754C\u9762");
		frame.setBounds(300, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 984, 762);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		Picture pic=new Picture();
        final OrSql s=new OrSql();
		
	    JLabel label=new JLabel();	   
	    label.setBounds(0, 0, 984, 762);
	    label.setHorizontalAlignment(0);
	    label.setIcon(pic.getIco("001-1"));	  
	    panel.add(label);
	    
	    JLabel label_3 = new JLabel((String) null);
	    label_3.setForeground(Color.YELLOW);
	    label_3.setBounds(230, 295, 60, 15);
	    label.add(label_3);
	    
	    JLabel label_1 = new JLabel("\u6C42\u4EBA\u5F97\u4EBA\uFF0C\u6C42\u79CD\u5F97\u79CD");
	    label_1.setBackground(Color.WHITE);
	    label_1.setFont(new Font("·ÂËÎ", Font.BOLD, 30));
	    label_1.setBounds(244, 135, 360, 66);
	    label_1.setHorizontalAlignment(SwingConstants.CENTER);
	    label.add(label_1);
	    
	    JButton button_1 = new JButton("\u8FD4\u56DE\u9996\u9875");
	    button_1.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		frame.setVisible(false);
	    		try {
					new Main().main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    button_1.setBounds(794, 135, 93, 33);
	    label.add(button_1);
	    
	    textField_1 = new JTextField();
	    label.add(textField_1);
	    textField_1.setBounds(344, 447, 66, 21);
	    textField_1.setColumns(10);
	    
	    textField_2 = new JTextField();
	    label.add(textField_2);
	    textField_2.setBounds(344, 473, 159, 21);
	    textField_2.setColumns(10);
	    
	    final JTextArea textArea = new JTextArea();
	    textArea.setText("\u8BF4\u660E\u4F60\u9700\u8981\u7684\u8D44\u6E90");
	    textArea.setBounds(333, 307, 308, 126);
	    label.add(textArea);
	    
	    final JLabel label_6 = new JLabel("");
	    label_6.setForeground(Color.ORANGE);
	    label_6.setBounds(486, 521, 77, 15);
	    label.add(label_6);
	    
	    JButton button = new JButton("\u53D1\u5E03");
	    label.add(button);
	    button.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		String task=textArea.getText();
	    		String taskpoints=textField_1.getText();
	    		String taskey=textField_2.getText();
	    			            
	            try {
	            	Date time=new Date();
	            	String timer=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
		            String sql="insert into Task11 values(Sequence_Task.Nextval,?,?,?,?,to_date(?,'YYYY-MM-DD HH24:MI:SS'))";
					s.setPreparedStatement(sql);					
					s.getPs().setInt(1, userid);
					s.getPs().setString(2, task);
					s.getPs().setString(3, taskey);
					s.getPs().setInt(4,Integer.parseInt(taskpoints));
					s.getPs().setString(5, timer);
					s.getPs().executeUpdate();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            label_6.setText("\u4EFB\u52A1\u5DF2\u53D1\u5E03\uFF01");
	            
	    	}
	    });
	    button.setBounds(345, 516, 93, 23);
	    
	    JLabel label_4 = new JLabel("\u4EFB\u52A1\u79EF\u5206:");
	    label.add(label_4);
	    label_4.setForeground(Color.YELLOW);
	    label_4.setBounds(243, 448, 60, 15);
	    
	    JLabel label_5 = new JLabel("\u4EFB\u52A1\u5173\u952E\u5B57:");
	    label.add(label_5);
	    label_5.setForeground(Color.YELLOW);
	    label_5.setBounds(245, 476, 80, 15);
	    
	    JLabel label_2 = new JLabel("\u6C42\u79CD\u4EFB\u52A1:");
	    label.add(label_2);
	    label_2.setForeground(Color.BLUE);
	    label_2.setBounds(245, 310, 60, 15);
  
	}
}

