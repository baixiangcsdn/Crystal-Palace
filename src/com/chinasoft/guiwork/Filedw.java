package com.chinasoft.guiwork;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

import java.awt.Color;

public class Filedw {

	private JFrame frame;
	private static int userid;
	private static int fileid;
	private static int y=1;
	private int ys;
	private int fuserid;
	private String filetype;
	private String filename;
	private int filepoints;
	private JTable table;
	private JTextField textField_1;
	
	public static void setUserid(int userid) {
		Filedw.userid = userid;
	}

	public static void setFileid(int fileid) {
		Filedw.fileid = fileid;
	}

	public static void setY(int y) {
		Filedw.y = y;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Filedw window = new Filedw();
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
	public Filedw() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6C34\u6676\u5BAB\u6587\u4EF6\u8BE6\u60C5\u9875");
		frame.setBounds(100, 100, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 984, 962);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u4F1A\u5458:");
		label.setBounds(54, 48, 43, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u79EF\u5206:");
		label_1.setBounds(261, 48, 43, 15);
		panel.add(label_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(96, 48, 100, 15);
		panel.add(lblNewLabel);
		
		final JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(314, 48, 54, 15);
		panel.add(lblNewLabel_1);
		
		
		
		final OrSql s=new OrSql();
		String[] xp =new String[12];
		Object[][] obj1=new Object[10][3];
		try {
		    String sql="select * from Member11 where m_id=?";
			s.setPreparedStatement(sql);
			s.getPs().setInt(1, userid);
			ResultSet rs=s.getPs().executeQuery();
			if(rs.next()){
				String user=rs.getString(2);
				int points=rs.getInt(4);
				lblNewLabel.setText(user);
				lblNewLabel_1.setText(points+"");
			}
			String sql1="select * from task_file tf where tf.f_id=?";
			s.setPreparedStatement(sql1);
			s.getPs().setInt(1, fileid);
			ResultSet rs1=s.getPs().executeQuery();
			if(rs1.next()){
				String[] xq={"文件编号："+rs1.getString(1),"文件名称："+rs1.getString(2),"文件类型："+rs1.getString(3),"文件大小："+rs1.getString(4),"上传用户编号："+rs1.getString(5),"上传者本地文件位置："+rs1.getString(6),"上传时间："+rs1.getString(7),"下载积分："+rs1.getString(8),"下载次数："+rs1.getString(9),"所属任务编号："+rs1.getString(10),"所属任务积分："+rs1.getString(11)};
				for (int i = 0; i < 11; i++) {
					xp[i]=xq[i];
				}
				fuserid=rs1.getInt(5);
				filetype=rs1.getString(3);
				filename=rs1.getString(2);
				filepoints=rs1.getInt(8);
			}
			String sql2="select count(cf.c_id) from comment_file cf full join Member11 m on cf.m_id=m.m_id where cf.f_id=?";
			s.setPreparedStatement(sql2);
			s.getPs().setInt(1, fileid);
			ResultSet rs2=s.getPs().executeQuery();
			if(rs2.next()){
				xp[11]="评论数量："+rs2.getInt(1);
				ys=rs2.getInt(1);
			}
			
			String sql3="select * from (select rownum no,cs.c_x,cs.c_date,cs.m_name from "+
			"(select cf.f_id,cf.c_x,cf.c_date,m.m_name from comment_file cf full join Member11 m on cf.m_id=m.m_id where cf.f_id=? order by cf.c_date desc) cs) cc where cc.no>=? and cc.no<?";
			s.setPreparedStatement(sql3);
			s.getPs().setInt(2, y);
			s.getPs().setInt(3, y+10);
			s.getPs().setInt(1, fileid);
			ResultSet rs3=s.getPs().executeQuery();
			int i=0;
			while(rs3.next()){
				obj1[i][0]=rs3.getString(2);
				obj1[i][1]=rs3.getString(3);
				obj1[i][2]=rs3.getString(4);
				i++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u6587\u4EF6", "\u4EFB\u52A1", "\u8BC4\u8BBA"}));
		comboBox.setBounds(378, 45, 60, 21);
		panel.add(comboBox);
		
		final JTextField textField = new JTextField();
		textField.setBounds(448, 45, 197, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u641C\u7D22");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				String search=textField.getText();
				int select=comboBox.getSelectedIndex();
				String sql="select * from FileInfo11 f where f.f_name like '%"+search+"%'";
				String sql1="select * from Task11 t where t.t_x like '%"+search+"%' or t.t_key like '%"+search+"%'";
				String sql2="select * from Comment11 c where c.c_x like '%"+search+"%'";
				try {
					if(select==0){
						    s.setPreparedStatement(sql);
							ResultSet rs=s.getPs().executeQuery();
							List<Integer> ids1=new ArrayList<Integer>();
							while(rs.next()){
								ids1.add(rs.getInt(1));
							}
							SearchFile sf=new SearchFile();
							sf.setIds1(ids1);
							sf.setUserid(userid);
							sf.main(null);
					}else if(select==1){
						    s.setPreparedStatement(sql1);
							ResultSet rs=s.getPs().executeQuery();
							List<Integer> ids2=new ArrayList<Integer>();
							while(rs.next()){
								ids2.add(rs.getInt(1));
							}
							SearchTask st=new SearchTask();
							st.setIds2(ids2);
							st.setUserid(userid);
							st.main(null);
					}else if(select==2){
						    s.setPreparedStatement(sql2);
							ResultSet rs=s.getPs().executeQuery();
							List<Integer> ids3=new ArrayList<Integer>();
							while(rs.next()){
								ids3.add(rs.getInt(1));
							}
							SearchComm sc=new SearchComm();
							sc.setIds3(ids3);
							sc.setUserid(userid);
							sc.main(null);
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
			}
		});
		button.setBounds(655, 44, 93, 23);
		panel.add(button);
		
		JLabel label_2 = new JLabel("\u9996\u9875");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				Main mt;
				try {
					mt = new Main();
					mt.setUserid(userid);
					mt.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		label_2.setFont(new Font("宋体", Font.BOLD, 12));
		label_2.setBounds(54, 126, 54, 15);
		panel.add(label_2);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				frame.setVisible(false);
				int t=comboBox_1.getSelectedIndex();
				String type = null;
				switch(t){
					case 1:type="document";
						break;
					case 2:type="picture";
						break;
					case 3:type="excel";
						break;
					case 4:type="code";
						break;
					case 5:type="zip";
						break;
				}
				String sql="select * from FileInfo11 f where f.f_type=?";
				try {
					s.setPreparedStatement(sql);
					s.getPs().setString(1, type);
					ResultSet rs=s.getPs().executeQuery();
					List<Integer> ids1=new ArrayList<Integer>();
					while(rs.next()){
						ids1.add(rs.getInt(1));
					}
					SearchFile sf=new SearchFile();
					sf.setIds1(ids1);
					sf.setUserid(userid);
					sf.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"\u5206\u7C7B", "\u6587\u6863", "\u56FE\u7247", "\u8868\u683C", "\u4EE3\u7801", "\u538B\u7F29\u5305"}));
		comboBox_1.setBounds(131, 123, 65, 21);
		panel.add(comboBox_1);
		
		JLabel label_4 = new JLabel("\u53D1\u5E03\u4EFB\u52A1");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				PutTask pt;
				try {
					pt = new PutTask();
					pt.setUserid(userid);
					pt.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		label_4.setFont(new Font("宋体", Font.BOLD, 12));
		label_4.setBounds(240, 126, 54, 15);
		panel.add(label_4);
		
		JLabel label_6 = new JLabel("\u5F53\u524D\u4F4D\u7F6E>\u6587\u4EF6\u4E0B\u8F7D");
		label_6.setBounds(54, 152, 213, 15);
		panel.add(label_6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 202, 397, 603);
		panel.add(scrollPane);
		JList list = new JList(xp);
		scrollPane.setViewportView(list);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(489, 202, 465, 222);
		panel.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			obj1,
			new String[] {
				"\u8BC4\u8BBA", "\u8BC4\u8BBA\u65F6\u95F4", "\u8BC4\u8BBA\u4EBA"
			}
		));
		scrollPane_1.setViewportView(table);
		
		JButton button_1 = new JButton("<<");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				Filedw fd;
				try {
					fd = new Filedw();
					fd.setFileid(fileid);
					fd.setUserid(userid);
					int x=y-10;
					if(x<1){
						fd.setY(y);
					}else{
						fd.setY(x);
					}
					fd.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(489, 171, 93, 23);
		panel.add(button_1);
		
		JButton button_2 = new JButton(">>");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				Filedw fd;
				try {
					fd = new Filedw();
					fd.setFileid(fileid);
					fd.setUserid(userid);
					int x=y+10;
					if(x<ys){
						fd.setY(x);
					}else{
						fd.setY(y);
					}
					fd.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_2.setBounds(861, 169, 93, 23);
		panel.add(button_2);
		
		JLabel label_5 = new JLabel((y/10+1)+"");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(667, 175, 81, 15);
		panel.add(label_5);
		
		final JTextArea textArea = new JTextArea();
		textArea.setText("\u6807\u70B9\u6309\u534A\u5B57\u7B97\uFF0C\u4E0D\u5F97\u8D85\u8FC750\u5B57");
		textArea.setBounds(489, 514, 465, 259);
		panel.add(textArea);
		
		JButton button_3 = new JButton("\u53D1\u8868\u8BC4\u8BBA");
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				String pl=textArea.getText();
				Date time=new Date();
            	String timer=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
            	String sql2="insert into Comment11 values(Sequence_Comment.nextval,?,?,?,?,?,to_date(?,'YYYY-MM-DD HH24:MI:SS'))";
				try {
					s.setPreparedStatement(sql2);
					s.getPs().setInt(1, userid);
					s.getPs().setInt(2, fileid);
					s.getPs().setInt(3, fuserid);
					s.getPs().setString(4,pl);
					s.getPs().setInt(5, pl.length());
					s.getPs().setString(6, timer);
					s.getPs().executeUpdate();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Filedw fd=new Filedw();
				fd.setFileid(fileid);
				fd.setUserid(userid);
				fd.setY(y);
				fd.main(null);
			}
		});
		button_3.setBounds(861, 481, 93, 23);
		panel.add(button_3);
		
		textField_1 = new JTextField();
		textField_1.setText("\u8BF7\u8F93\u5165\u4FDD\u5B58\u6587\u4EF6\u7684\u672C\u5730\u8DEF\u5F84");
		textField_1.setBounds(64, 177, 258, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		final JLabel label_7 = new JLabel("");
		label_7.setForeground(Color.RED);
		label_7.setBounds(345, 152, 54, 15);
		panel.add(label_7);
		
		JButton button_4 = new JButton("\u4E0B\u8F7D");
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String path=textField_1.getText();
				int points=Integer.parseInt(lblNewLabel_1.getText());
				if(filepoints>points){
					label_7.setText("积分不足");
					filename="";
					filepoints=0;
				}
				try {
	            	File file = new File("tp/ftp1.txt");
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
			            bw.write("lcd "+path);
			            bw.newLine();
			            bw.write("get "+filename);
			            bw.newLine();
			            bw.write("bye");
			            bw.newLine();
			            bw.write("exit");
			            bw.close();
			            File file2 = new File("tp/ftplink1.bat");
			            Process pro = Runtime.getRuntime().exec("cmd /c start "+file2.getAbsolutePath());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("下载失败~~~");
				}
				String sql="update Member11 m set m.m_apts=m.m_apts-?,m.m_dwc=m.m_dwc+1 where m.m_id=?";
				String sql1="update FileInfo11 f set f.f_dwcount=f.f_dwcount+1 where f.f_id=?";
				try {
					s.setPreparedStatement(sql);
					s.getPs().setInt(1, filepoints);
					s.getPs().setInt(2, userid);
					s.getPs().executeUpdate();
					s.setPreparedStatement(sql1);
					s.getPs().setInt(1, fileid);
					s.getPs().executeUpdate();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Filedw fd=new Filedw();
				fd.setFileid(fileid);
				fd.setUserid(userid);
				fd.setY(y);
				fd.main(null);
			}
		});
		button_4.setBounds(345, 171, 93, 23);
		panel.add(button_4);

		
	}
}

