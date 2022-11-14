package uo.cpm.p8.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.p8.player.MusicPlayer;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MusicPlayer mp;
	
	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @param mp 
	 */
	public MainWindow(MusicPlayer mp) {
		
		this.mp = mp;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
