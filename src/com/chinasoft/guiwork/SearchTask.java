package com.chinasoft.guiwork;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SearchTask {

	private JFrame frame;
	private static int userid;
	private static List<Integer> ids2=new ArrayList<Integer>();
	private static int ye=1;

	public static void setUserid(int userid) {
		SearchTask.userid = userid;
	}

	public static void setIds2(List<Integer> ids2) {
		SearchTask.ids2 = ids2;
	}

	public static void setYe(int ye) {
		SearchTask.ye = ye;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchTask window = new SearchTask();
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
	public SearchTask() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setTitle("\u6C34\u6676\u5BAB\u641C\u7D22\u9875");
		frame.setBounds(100, 100, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 984, 962);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		final OrSql s=new OrSql();
		String sql="select  * from Task11 where t_id=?";
		s.setPreparedStatement(sql);
		
		Object[][] ob=new Object[ids2.size()][6];
		for (int i = 0; i < ids2.size(); i++) {
			s.getPs().setInt(1, ids2.get(i));
			ResultSet rs=s.getPs().executeQuery();
			while(rs.next()){
				for (int j = 0; j < 6; j++) {
					ob[i][j]=rs.getString(j+1);
				}
			}
		}
		final Object[][] obj=new Object[30][6];
		final int yes;
		if(ids2.size()%30==0){
			yes=ids2.size()/30;
		}else{
			yes=ids2.size()/30+1;
		}
		if(ye==yes){
			for (int j = 0; j < ids2.size()%30; j++) {
				for (int j2 = 0; j2 < 6; j2++) {
					obj[j][j2]=ob[30*(ye-1)+j][j2];
				}
			}
		}else if(ye<yes){
			for (int j = 0; j < 30; j++) {
				for (int j2 = 0; j2 < 6; j2++) {
					obj[j][j2]=ob[30*(ye-1)+j][j2];
				}
			}
		}
		
			
		
		
		JLabel label = new JLabel("\u4F1A\u5458:");
		label.setBounds(54, 48, 43, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u79EF\u5206:");
		label_1.setBounds(261, 48, 43, 15);
		panel.add(label_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(96, 48, 100, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(314, 48, 54, 15);
		panel.add(lblNewLabel_1);
		
		try {
		    String sql1="select * from Member11 where m_id=?";
			s.setPreparedStatement(sql1);
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
		
		JLabel label_5 = new JLabel("\u5F53\u524D\u4F4D\u7F6E>\u641C\u7D22-\u8BC4\u8BBA");
		label_5.setBounds(54, 152, 213, 15);
		panel.add(label_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 196, 880, 528);
		panel.add(scrollPane);
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(obj,
			new String[] {
				"评论编号", "评论者编号", "评论文件编号", "评论内容", "评论长度", "评论时间"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			
		});
		scrollPane.setViewportView(table);
		
		JLabel label_6 = new JLabel("\u9996\u9875");
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				SearchTask st;
				try {
					st = new SearchTask();
					st.setIds2(ids2);
					st.setUserid(userid);
					st.setYe(1);
					st.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		label_6.setBounds(70, 754, 54, 15);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("\u5C3E\u9875");
		label_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				SearchTask st;
				try {
					st = new SearchTask();
					st.setIds2(ids2);
					st.setUserid(userid);
					st.setYe(yes);
					st.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		label_7.setBounds(832, 754, 54, 15);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("\u4E0A\u4E00\u9875");
		label_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				SearchTask st;
				try {
					st = new SearchTask();
					st.setIds2(ids2);
					st.setUserid(userid);
					int x=ye;
					if(x>1){
						st.setYe(--x);
					}else{
						st.setYe(x);
					}
					st.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		label_8.setBounds(134, 754, 43, 15);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("\u4E0B\u4E00\u9875");
		label_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				SearchTask st;
				try {
					st = new SearchTask();
					st.setIds2(ids2);
					st.setUserid(userid);
					int x=ye;
					if(x<yes){
						st.setYe(++x);
					}else{
						st.setYe(x);
					}
					st.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		label_9.setBounds(774, 754, 48, 15);
		panel.add(label_9);
		
		JLabel lblNewLabel_2 = new JLabel("\u7B2C");
		lblNewLabel_2.setBounds(215, 754, 21, 15);
		panel.add(lblNewLabel_2);
		
		JLabel label_10 = new JLabel(ye+"");
		label_10.setBounds(240, 754, 37, 15);
		panel.add(label_10);
		
		JLabel label_11 = new JLabel("\u9875");
		label_11.setBounds(285, 754, 21, 15);
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("\u5171");
		label_12.setBounds(334, 754, 21, 15);
		panel.add(label_12);
		
		JLabel label_13 = new JLabel(yes+"");
		label_13.setBounds(365, 754, 37, 15);
		panel.add(label_13);
		
		JLabel label_14 = new JLabel("\u9875");
		label_14.setBounds(409, 754, 29, 15);
		panel.add(label_14);
		
		JLabel label_15 = new JLabel("\u6BCF\u987530\u884C");
		label_15.setBounds(551, 754, 54, 15);
		panel.add(label_15);
		
		JLabel label_16 = new JLabel("\u5171");
		label_16.setBounds(641, 754, 21, 15);
		panel.add(label_16);
		
		JLabel label_17 = new JLabel(ids2.size()+"");
		label_17.setBounds(670, 754, 46, 15);
		panel.add(label_17);
		
		JLabel label_18 = new JLabel("\u884C");
		label_18.setBounds(726, 754, 29, 15);
		panel.add(label_18);
		
		final JTextField textField_1 = new JTextField();
		textField_1.setBounds(457, 751, 66, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button_1 = new JButton("\u8DF3\u8F6C");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x=Integer.parseInt(textField_1.getText());
				frame.setVisible(false);
				SearchTask st;
				try {
					st = new SearchTask();
					st.setIds2(ids2);
					st.setUserid(userid);
					st.setYe(x);
					st.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(457, 795, 66, 23);
		panel.add(button_1);
	}
}

