package roulette;

import java.awt.EventQueue;
import java.util.Properties;

import roulette.ui.MainWindow;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Properties props = new Properties();
					props.put("logoString", "");
					
					MainWindow frame = new MainWindow();
					
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
