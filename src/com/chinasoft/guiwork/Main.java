package com.chinasoft.guiwork;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JTextPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.JEditorPane;
import javax.swing.JTextArea;

import java.awt.Color;

public class Main {

	private JFrame frame;
	private JTextField textField;
	private static int userid;


	public static void setUserid(int userid) {
		Main.userid = userid;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setTitle("\u6C34\u6676\u5BAB\u4E3B\u9875");
		frame.setBounds(100, 100, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 984, 962);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		Picture pic=new Picture();
	    JLabel label0=new JLabel();	   
	    label0.setBounds(0, 0, 984, 962);
	    label0.setHorizontalAlignment(0);
	    label0.setIcon(pic.getIco("002"));	  
	    panel.add(label0);
		
		JLabel label = new JLabel("\u4F1A\u5458:");
		label.setBounds(54, 48, 43, 15);
		label0.add(label);
		
		JLabel label_1 = new JLabel("\u79EF\u5206:");
		label_1.setBounds(261, 48, 43, 15);
		label0.add(label_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				MyUser mu=new MyUser();
				mu.setUserid(userid);
				mu.main(null);
			}
		});
		lblNewLabel.setBounds(96, 48, 100, 15);
		label0.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(314, 48, 54, 15);
		label0.add(lblNewLabel_1);
		
		final OrSql s=new OrSql();
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u6587\u4EF6", "\u4EFB\u52A1", "\u8BC4\u8BBA"}));
		comboBox.setBounds(378, 45, 60, 21);
		label0.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(448, 45, 197, 21);
		label0.add(textField);
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
		label0.add(button);
		
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
		label0.add(label_2);
		
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
		label0.add(label_4);
		
		JLabel label_5 = new JLabel("\u53EA\u8981\u4EBA\u4EBA\u90FD\u5206\u4EAB\u4E00\u70B9\u7231\uFF0C\u4E16\u754C\u4F1A\u53D8\u6210\u7F8E\u597D\u660E\u5929");
		label_5.setForeground(Color.BLUE);
		label_5.setFont(new Font("宋体", Font.ITALIC, 17));
		label_5.setBounds(54, 222, 367, 21);
		label0.add(label_5);
		
		JButton button_1 = new JButton("\u4E0A\u4F20");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					frame.setVisible(false);
					Upload up=new Upload();
					up.setUserid(userid);
					up.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button_1.setBounds(451, 222, 93, 23);
		label0.add(button_1);
		
		JLabel label_6 = new JLabel("\u5F53\u524D\u4F4D\u7F6E>\u9996\u9875");
		label_6.setBounds(54, 152, 213, 15);
		label0.add(label_6);
		
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
		label0.add(comboBox_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("楷体", Font.BOLD, 30));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setTabSize(10);
		textArea.setText("\u6C34\u6676\u5BAB\u4F1A\u5458\u987B\u77E5\r\n1.\u4E0D\u5F97\u53D1\u5E03\u6C61\u67D3\u6E90\r\n2.\u8BC4\u8BBA\u4E2D\u4E0D\u5F97\u51FA\u73B0\u4E0D\u6587\u660E\u5B57\u773C\r\n3.\u672C\u7AD9\u6240\u6709\u8D44\u6E90\u4E0B\u8F7D\u5747\u9700\u79EF\u5206\r\n4.\u672C\u7AD9\u6240\u6709\u79EF\u5206\u53EA\u80FD\u7531\u4E0A\u4F20\u8D44\u6E90\u83B7\u5F97\r\n5.\u672C\u7AD9\u524D100\u540D\u4F1A\u5458\u5747\u53EF\u83B7\u5F9710\u70B9\u521D\u59CB\u79EF\u5206");
		textArea.setBounds(64, 318, 610, 289);
		label0.add(textArea);
		
		JLabel label_3 = new JLabel("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label_3.setBounds(10, 76, 911, 15);
		label0.add(label_3);
		
		JLabel label_7 = new JLabel("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label_7.setBounds(10, 141, 911, 15);
		label0.add(label_7);
		
		
	}
}
