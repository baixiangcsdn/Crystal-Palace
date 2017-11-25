package com.chinasoft.guiwork;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;

public class MyUser {

	private JFrame frame;
	private static int userid;
	

	public static void setUserid(int userid) {
		MyUser.userid = userid;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyUser window = new MyUser();
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
	public MyUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 10, 189, 242);
		panel.add(scrollPane);
		
		OrSql s=new OrSql();
		String[] x=new String[9];
		String sql="select * from Member11 where m_id=?";
		try {
			s.setPreparedStatement(sql);
			s.getPs().setInt(1, userid);
			ResultSet rs=s.getPs().executeQuery();
			while(rs.next()){
				x[0]="��Ա���"+rs.getString(1);
				x[1]="��Ա����"+rs.getString(2);
				x[2]="��Ա����"+rs.getString(3);
				x[3]="��Ա����"+rs.getString(4);
				x[4]="��Ա���ش���"+rs.getString(5);
			}
			String sql1="select count(ftc.f_id),sum(ftc.f_size),count(ftc.t_id),count(ftc.c_id) from Member11 m full join (select tf.f_id,tf.f_size,tf.fil_m_id,tf.t_id,c.c_id from task_file tf full join Comment11 c on tf.f_id=c.f_id) ftc on m.m_id=ftc.fil_m_id where m.m_id=?";
			s.setPreparedStatement(sql);
			s.getPs().setInt(1, userid);
			ResultSet rs1=s.getPs().executeQuery();
			while(rs.next()){
				x[5]="�ϴ��ļ���"+rs1.getString(1)+"��";
				x[6]="�ϴ��ļ�����"+rs1.getString(2)+"�ֽ�";
				x[7]="�������"+rs1.getString(3)+"��";
				x[8]="��������"+rs1.getString(4)+"��";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JList list = new JList(x);
		scrollPane.setViewportView(list);
		
		JButton button = new JButton("\u8FD4\u56DE");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				Main m;
				try {
					m = new Main();
					m.setUserid(userid);
					m.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button.setBounds(314, 31, 93, 23);
		panel.add(button);
	}
}
