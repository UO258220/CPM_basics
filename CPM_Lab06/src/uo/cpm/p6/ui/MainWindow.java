package uo.cpm.p6.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.p6.service.SpaceInvaders;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private SpaceInvaders game = null;

	private JPanel contentPane;
	private JButton btnDice;
	private JLabel lblSpaceShip;
	private JPanel pnShots;
	private JLabel lblScore;
	private JTextField txtScore;
	private JLabel lblEarth;
	private JPanel pnBoard;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;

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

		enableBoard(false);
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
			pnBoard.add(getBtnNewButton_7());
			pnBoard.add(getBtnNewButton_6());
			pnBoard.add(getBtnNewButton_5());
			pnBoard.add(getBtnNewButton_4());
			pnBoard.add(getBtnNewButton_3());
			pnBoard.add(getBtnNewButton_2());
			pnBoard.add(getBtnNewButton_1());
			pnBoard.add(getBtnNewButton());
		}
		return pnBoard;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shoot(7);
				}
			});
			btnNewButton.setBorder(new LineBorder(Color.GREEN, 3));
		}
		return btnNewButton;
	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shoot(6);
				}
			});
			btnNewButton_1.setBorder(new LineBorder(Color.GREEN, 3));
		}
		return btnNewButton_1;
	}

	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shoot(5);
				}
			});
			btnNewButton_2.setBorder(new LineBorder(Color.GREEN, 3));
		}
		return btnNewButton_2;
	}

	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shoot(4);
				}
			});
			btnNewButton_3.setBorder(new LineBorder(Color.GREEN, 3));
		}
		return btnNewButton_3;
	}

	private JButton getBtnNewButton_4() {
		if (btnNewButton_4 == null) {
			btnNewButton_4 = new JButton("");
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shoot(3);
				}
			});
			btnNewButton_4.setBorder(new LineBorder(Color.GREEN, 3));
		}
		return btnNewButton_4;
	}

	private JButton getBtnNewButton_5() {
		if (btnNewButton_5 == null) {
			btnNewButton_5 = new JButton("");
			btnNewButton_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shoot(2);
				}
			});
			btnNewButton_5.setBorder(new LineBorder(Color.GREEN, 3));
		}
		return btnNewButton_5;
	}

	private JButton getBtnNewButton_6() {
		if (btnNewButton_6 == null) {
			btnNewButton_6 = new JButton("");
			btnNewButton_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shoot(1);
				}
			});
			btnNewButton_6.setBorder(new LineBorder(Color.GREEN, 3));
		}
		return btnNewButton_6;
	}

	private JButton getBtnNewButton_7() {
		if (btnNewButton_7 == null) {
			btnNewButton_7 = new JButton("");
			btnNewButton_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shoot(0);
				}
			});
			btnNewButton_7.setBorder(new LineBorder(Color.GREEN, 3));
		}
		return btnNewButton_7;
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
		for (int pos = 0; pos < game.getBoard().DIM; pos++) {
			paintCell(pos);
		}
	}

}
