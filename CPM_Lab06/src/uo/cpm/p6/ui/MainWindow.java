package uo.cpm.p6.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import uo.cpm.p6.rules.Game.Level;
import uo.cpm.p6.service.SpaceInvaders;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private SpaceInvaders game = null;

	private MyButtonListener mBL = new MyButtonListener();

	private JPanel contentPane;
	private JButton btnDice;
	private JLabel lblSpaceShip;
	private JPanel pnShots;
	private JLabel lblScore;
	private JTextField txtScore;
	private JLabel lblEarth;
	private JPanel pnBoard;
	private JMenuBar menuBar;
	private JMenu mnGame;
	private JMenu mnHelp;
	private JMenu mnConfig;
	private JRadioButtonMenuItem rdbtnmntmEasy;
	private JRadioButtonMenuItem rdbtnmntmIntermediate;
	private JRadioButtonMenuItem rdbtnmntmHard;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public MainWindow(SpaceInvaders game) {
		setTitle("Space Invasion");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/invader.jpg")));
		setResizable(false);

		this.game = game;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 500);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnDice());
		contentPane.add(getLblSpaceShip());
		contentPane.add(getPnShots());
		contentPane.add(getLblScore());
		contentPane.add(getTxtScore());
		contentPane.add(getLblEarth());
		contentPane.add(getPnBoard());

		initializeGame(Level.INTERMEDIATE);
	}

	private JButton getBtnDice() {
		if (btnDice == null) {
			btnDice = new JButton("");
			btnDice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					initGame();
				}
			});
			btnDice.setDisabledIcon(new ImageIcon(MainWindow.class.getResource("/img/dice.jpg")));
			btnDice.setToolTipText("Launch the dice");
			btnDice.setIcon(new ImageIcon(MainWindow.class.getResource("/img/dice.jpg")));
			btnDice.setBounds(29, 22, 115, 114);
		}
		return btnDice;
	}

	private JLabel getLblSpaceShip() {
		if (lblSpaceShip == null) {
			lblSpaceShip = new JLabel("");
			lblSpaceShip.setIcon(new ImageIcon(MainWindow.class.getResource("/img/spaceship.png")));
			lblSpaceShip.setBounds(315, 11, 137, 97);
		}
		return lblSpaceShip;
	}

	private JPanel getPnShots() {
		if (pnShots == null) {
			pnShots = new JPanel();
			pnShots.setBounds(189, 143, 395, 97);
		}
		return pnShots;
	}

	private JLabel getLblScore() {
		if (lblScore == null) {
			lblScore = new JLabel("Score:");
			lblScore.setForeground(Color.GREEN);
			lblScore.setFont(new Font("Tahoma", Font.BOLD, 22));
			lblScore.setBounds(656, 33, 72, 32);
		}
		return lblScore;
	}

	private JTextField getTxtScore() {
		if (txtScore == null) {
			txtScore = new JTextField();
			txtScore.setEditable(false);
			txtScore.setHorizontalAlignment(SwingConstants.CENTER);
			txtScore.setForeground(Color.GREEN);
			txtScore.setFont(new Font("Tahoma", Font.BOLD, 34));
			txtScore.setText("0");
			txtScore.setBounds(639, 76, 109, 52);
			txtScore.setColumns(10);
		}
		return txtScore;
	}

	private JLabel getLblEarth() {
		if (lblEarth == null) {
			lblEarth = new JLabel("");
			lblEarth.setIcon(new ImageIcon(MainWindow.class.getResource("/img/earth.jpg")));
			lblEarth.setBounds(881, 11, 193, 175);
		}
		return lblEarth;
	}

	private JPanel getPnBoard() {
		if (pnBoard == null) {
			pnBoard = new JPanel();
			pnBoard.setBorder(new LineBorder(Color.GREEN, 5));
			pnBoard.setBounds(47, 309, 995, 108);
			pnBoard.setLayout(new GridLayout(1, 0, 0, 0));

			for (int i = 0; i < this.game.getSize(); i++) {
				pnBoard.add(newButton(i));
			}
		}
		return pnBoard;
	}

	private void enableBoard(boolean state) {
		for (Component c : pnBoard.getComponents()) {
			c.setEnabled(state);
		}
	}

	private JLabel newShot() {
		JLabel newShot = new JLabel();
		newShot.setBorder(new LineBorder(Color.GREEN, 1));
		newShot.setIcon(ImageFactory.loadImage("/img/shoot.png"));
		return newShot;
	}

	private void paintShots() {
		for (int i = 0; i < game.getShots(); i++) {
			getPnShots().add(newShot());
		}
		validate();
	}

	private void initGame() {
		game.launch();
		paintShots();
		btnDice.setEnabled(false);
		enableBoard(true);
	}

	private void removeShot() {
		getPnShots().remove(0);
		getPnShots().repaint();
	}

	private void paintCell(Integer position) {
		ImageIcon icon = ImageFactory.loadImage(game.getBoard().getPicture(position));
		((JButton) getPnBoard().getComponent(position)).setIcon(icon);
		((JButton) getPnBoard().getComponent(position)).setDisabledIcon(icon);
	}

	private void updateStateOfTheGame(Integer position) {
		getTxtScore().setText(String.valueOf(game.getScore()));
		removeShot();
		paintCell(position);
		if (game.isGameOver()) {
			enableBoard(false);
			paintBoard();
			JOptionPane.showMessageDialog(this, game.getMessage(), "Space Invasion", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void shoot(Integer position) {
		game.shoot(position);
		updateStateOfTheGame(position);
	}

	private void paintBoard() {
		game.getBoard();
		for (int pos = 0; pos < this.game.getSize(); pos++) {
			paintCell(pos);
		}
	}

	private JButton newButton(Integer position) {
		JButton bt = new JButton();
		bt.setActionCommand(position.toString());
		bt.setBorder(new LineBorder(Color.GREEN, 3));
		bt.addActionListener(mBL);
		return bt;
	}

	private void prepareBoard(Level level) {
		Integer width = 0;
		Integer pos = 0;
		switch (level) {
		case EASY: {
			pos = 47;
			width = 1010;
			break;
		}
		case INTERMEDIATE: {
			pos = 150;
			width = 815;
			break;
		}
		case HARD: {
			pos = 240;
			width = 610;
			break;
		}
		}
		getPnBoard().setBounds(pos, 309, width, 100);
		getPnBoard().setLayout(new GridLayout(1, this.game.getSize(), 0, 0));

		getPnBoard().removeAll();

		for (int i = 0; i < this.game.getSize(); i++) {
			getPnBoard().add(newButton(i));
		}
		enableBoard(false);
		validate();
	}

	private void initializeGame(Level level) {
		game.initialize();

		prepareBoard(level);

		getBtnDice().setEnabled(true);
		getPnShots().removeAll();
		getTxtScore().setText(String.valueOf(game.getScore()));
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnGame());
			menuBar.add(getMnConfig());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnGame() {
		if (mnGame == null) {
			mnGame = new JMenu("Game");
		}
		return mnGame;
	}

	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
		}
		return mnHelp;
	}

	private JMenu getMnConfig() {
		if (mnConfig == null) {
			mnConfig = new JMenu("Configuration");
			mnConfig.add(getRdbtnmntmEasy());
			mnConfig.add(getRdbtnmntmIntermediate());
			mnConfig.add(getRdbtnmntmHard());
		}
		return mnConfig;
	}

	private JRadioButtonMenuItem getRdbtnmntmEasy() {
		if (rdbtnmntmEasy == null) {
			rdbtnmntmEasy = new JRadioButtonMenuItem("EASY");
			rdbtnmntmEasy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					game.setLevel(Level.EASY);
					initializeGame(Level.EASY);
				}
			});
			buttonGroup.add(rdbtnmntmEasy);
		}
		return rdbtnmntmEasy;
	}

	private JRadioButtonMenuItem getRdbtnmntmIntermediate() {
		if (rdbtnmntmIntermediate == null) {
			rdbtnmntmIntermediate = new JRadioButtonMenuItem("INT");
			rdbtnmntmIntermediate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					game.setLevel(Level.INTERMEDIATE);
					initializeGame(Level.INTERMEDIATE);
				}
			});
			buttonGroup.add(rdbtnmntmIntermediate);
			rdbtnmntmIntermediate.setSelected(true);
		}
		return rdbtnmntmIntermediate;
	}

	private JRadioButtonMenuItem getRdbtnmntmHard() {
		if (rdbtnmntmHard == null) {
			rdbtnmntmHard = new JRadioButtonMenuItem("HARD");
			rdbtnmntmHard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					game.setLevel(Level.HARD);
					initializeGame(Level.HARD);
				}
			});
			buttonGroup.add(rdbtnmntmHard);
		}
		return rdbtnmntmHard;
	}

	class MyButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			Integer position = Integer.parseInt(source.getActionCommand());
			shoot(position);
		}
	}
}
