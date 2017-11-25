package study;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
public class CountDown {
	public static	int i=0;

	public CountDown() {
		CDown();
	}
	
	public void CDown() {
		Timer timer = new Timer();
		final JFrame jf = new JFrame();
		final JLabel jl = new JLabel();
		
		jf.add(jl);
		jf.setVisible(true);
		jf.setSize(400, 150);
		jf.getDefaultCloseOperation();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if(i>5){
					jf.setVisible(false);
				}
				jl.setText(" javaº∆ ±" +i+"√Î");
				i++;
				
			}
		
		}, 0, 1000);
	}
	
	public static void main(String[] args) {
		new CountDown();
	}
}