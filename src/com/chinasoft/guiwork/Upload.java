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

public class Upload {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private static int userid;
	
	public static void setUserid(int userid) {
		Upload.userid = userid;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Upload window = new Upload();
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
	public Upload() throws Exception {
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
	    
	    JLabel label_2 = new JLabel("\u6587\u4EF6\u8DEF\u5F84:");
	    label_2.setForeground(Color.BLUE);
	    label_2.setBounds(230, 265, 60, 15);
	    label.add(label_2);
	    
	    JLabel label_3 = new JLabel("\u6587\u4EF6\u7C7B\u578B:");
	    label_3.setForeground(Color.YELLOW);
	    label_3.setBounds(230, 295, 60, 15);
	    label.add(label_3);
	    
	    JLabel label_4 = new JLabel("\u6587\u4EF6\u79EF\u5206:");
	    label_4.setForeground(Color.YELLOW);
	    label_4.setBounds(230, 325, 60, 15);
	    label.add(label_4);
	    
	    JLabel label_5 = new JLabel("\u4EFB\u52A1\u7F16\u53F7:");
	    label_5.setForeground(Color.YELLOW);
	    label_5.setBounds(230, 355, 60, 15);
	    label.add(label_5);
	    
	    textField = new JTextField();
	    textField.setBounds(308, 262, 300, 21);
	    label.add(textField);
	    textField.setColumns(10);
	    
	    final JComboBox comboBox = new JComboBox();
	    comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u6587\u672C", "\u56FE\u7247", "\u8868\u683C", "\u4EE3\u7801", "\u538B\u7F29\u5305"}));
	    comboBox.setBounds(310, 294, 91, 21);
	    label.add(comboBox);
	    
	    textField_1 = new JTextField();
	    textField_1.setBounds(310, 324, 66, 21);
	    label.add(textField_1);
	    textField_1.setColumns(10);
	    
	    textField_2 = new JTextField();
	    textField_2.setBounds(310, 355, 66, 21);
	    label.add(textField_2);
	    textField_2.setColumns(10);
	    
	    JButton button = new JButton("\u4E0A\u4F20");
	    button.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		String localpath=textField.getText();
	    		String filetype="document";
	    		int no=comboBox.getSelectedIndex();
	    		String filepoints=textField_1.getText();
	    		String taskid=textField_2.getText();
	    		switch(no){
	    			case 0:filetype="document";break;
	    			case 1:filetype="picture";break;
	    			case 2:filetype="excel";break;
	    			case 3:filetype="code";break;
	    			case 4:filetype="zip";break;
	    		}
	    		File f=new File(localpath);
	            try {
	            	File file = new File("tp/ftp.txt");
		            if(file.exists()){
		                file.delete();
		            }
					file.createNewFile();
					BufferedWriter bw = new BufferedWriter(
			                new OutputStreamWriter(
			                new FileOutputStream(file), "GB2312"));
			            bw.write("open 192.168.4.53");
			            bw.newLine();
			            bw.write("ftp");
			            bw.newLine();
			            bw.write("ftp");
			            bw.newLine();
			            bw.write("cd "+filetype);
			            bw.newLine();
			            bw.write("lcd "+f.getParentFile().getAbsolutePath());
			            bw.newLine();
			            bw.write("put "+f.getName());
			            bw.newLine();
			            bw.write("bye");
			            bw.newLine();
			            bw.write("exit");
			            bw.close();
			            File file2 = new File("tp/ftplink.bat");
			            Process pro = Runtime.getRuntime().exec("cmd /c start "+file2.getAbsolutePath());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("ÉÏ´«Ê§°Ü~~~");
				}
	            try {
	            	Date time=new Date();
	            	String timer=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
		            String sql="select t_point from task11 where t_id=?";
					s.setPreparedStatement(sql);
					if(!"".equals(taskid)){
						s.getPs().setInt(1, Integer.parseInt(taskid));
					}else{
						s.getPs().setInt(1,0);
						taskid="0";
					}
					ResultSet rs=s.getPs().executeQuery();
					if(rs.next()){
						String sql1="update Member11 set m_apts=m_apts+"+rs.getInt(1)+" where m_id=?";
						s.setPreparedStatement(sql1);
						s.getPs().setInt(1, userid);
						s.getPs().executeUpdate();
					}else{
						String sql1="update Member11 set m_apts=m_apts+3 where m_id=?";
						s.setPreparedStatement(sql1);
						s.getPs().setInt(1, userid);
						s.getPs().executeUpdate();
					}
					String sql2="insert into FileInfo11 values(Sequence_File.nextval,?,?,?,?,?,?,to_date(?,'YYYY-MM-DD HH24:MI:SS'),?,?)";
					s.setPreparedStatement(sql2);
					s.getPs().setInt(1, Integer.parseInt(taskid));
					s.getPs().setString(2, f.getName());
					s.getPs().setString(3, filetype);
					s.getPs().setInt(4,(int)f.length());
					s.getPs().setString(5, f.getParentFile().getAbsolutePath());
					s.getPs().setInt(6, userid);
					s.getPs().setString(7, timer);
					s.getPs().setInt(8, Integer.parseInt(filepoints));
					s.getPs().setInt(9, 0);
					s.getPs().executeUpdate();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            
	            
	    	}
	    });
	    label.add(button);
	    button.setBounds(310, 458, 93, 23);
	    
	    JLabel label_1 = new JLabel("\u5171\u4EAB\u8D44\u6E90\u521B\u9020\u7F8E\u597D\u4E16\u754C");
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
	    
	    
	}
}
