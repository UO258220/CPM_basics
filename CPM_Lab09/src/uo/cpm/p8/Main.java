package uo.cpm.p8;

import java.awt.EventQueue;
import java.util.Properties;

import javax.swing.UIManager;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

import uo.cpm.p8.player.MusicPlayer;
import uo.cpm.p8.ui.MainWindow;

public class Main {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		MusicPlayer mp = new MusicPlayer();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Properties props = new Properties();
					props.put("logoString", "");
					HiFiLookAndFeel.setCurrentTheme(props);
					UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
					
					MainWindow frame = new MainWindow(mp);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
