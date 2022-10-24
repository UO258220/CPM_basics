package uo.cpm.p3;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import uo.cpm.p3.service.McDonalds;
import uo.cpm.p3.ui.MainWindow;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		McDonalds mcDonalds = new McDonalds();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					
					MainWindow frame = new MainWindow( mcDonalds );
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
