package com.chinasoft.guiwork;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class LoginError {

	private JFrame frame;
	private static int time=5;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginError window = new LoginError();
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
	public LoginError() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u767B\u5F55\u5931\u8D25\u63D0\u793A\u754C\u9762");
		frame.setBounds(300, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 984, 762);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u767B\u5F55\u5931\u8D25\uFF0C\u8BF7\u786E\u8BA4\u7528\u6237\u540D\u548C\u5BC6\u7801\u65E0\u8BEF");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 45));
		lblNewLabel.setBounds(126, 116, 754, 105);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("5\u79D2\u949F\u540E\u81EA\u52A8\u8FD4\u56DE\u767B\u5F55\u754C\u9762\uFF0C\u5982\u8FD4\u56DE\u5931\u8D25\u8BF7\u70B9\u51FB\u8FD4\u56DE\u6309\u94AE");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 27));
		lblNewLabel_1.setBounds(126, 200, 821, 105);
		panel.add(lblNewLabel_1);
		
		JButton button = new JButton("\u8FD4\u56DE");
		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				try {
					new Login().main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
			
		});
		button.setForeground(Color.RED);
		button.setBackground(Color.YELLOW);
		button.setFont(new Font("宋体", Font.BOLD, 22));
		button.setBounds(483, 345, 121, 51);
		panel.add(button);
		
		JLabel label = new JLabel("\u5012\u8BA1\u65F6:");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(141, 357, 76, 29);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u79D2. . . . . .");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(300, 357, 137, 29);
		panel.add(label_1);
		
		final JLabel label_2 = new JLabel();
		final Timer Timer=new Timer();
		final TimerTask tt=new TimerTask() {
			
			@Override
			public void run() {
					if(time<0){
						time=5;
						Timer.cancel();
						frame.setVisible(false);
						//System.exit(0);
						try {
							new Login().main(null);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
				    String str=time+"";
					label_2.setText(str);
					time--;
			}
		};
		Timer.schedule(tt, 0, 1000);
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("宋体", Font.PLAIN, 88));
		label_2.setBounds(236, 315, 54, 97);
		panel.add(label_2);
	}
}
