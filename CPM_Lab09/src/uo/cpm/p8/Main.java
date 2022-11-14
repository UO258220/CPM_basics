package uo.cpm.p8;

import java.awt.EventQueue;

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
					MainWindow frame = new MainWindow(mp);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
