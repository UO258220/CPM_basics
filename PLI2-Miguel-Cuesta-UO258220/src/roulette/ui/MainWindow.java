package roulette.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import roulette.model.Prize;
import roulette.service.PrizeRoulette;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private PrizeRoulette pr;

	private JPanel contentPane;
	private JButton btnRoulette;
	private JTextField txtPoints;
	private JComboBox<Prize> cbPrizes;
	private JButton btnAdd;
	private JButton btnFinish;
	private JLabel lblPrizeIcon;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/roulette/img/ruleta.png")));
		setTitle("Prize Roulette Game");

		this.pr = new PrizeRoulette();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnRoulette());
		contentPane.add(getTxtPoints());
		contentPane.add(getCbPrizes());
		contentPane.add(getBtnAdd());
		contentPane.add(getBtnFinish());
		contentPane.add(getLblPrizeIcon());
	}

	private JButton getBtnRoulette() {
		if (btnRoulette == null) {
			btnRoulette = new JButton("");
			btnRoulette.setDisabledIcon(new ImageIcon(MainWindow.class.getResource("/roulette/img/ruleta.png")));
			btnRoulette.setMnemonic('s');
			btnRoulette.setToolTipText("Spin the roulette");
			btnRoulette.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int points = pr.spinRoulette();
					pr.setPoints(points);
					getTxtPoints().setText(String.valueOf(points));
					getBtnRoulette().setEnabled(false);
					getCbPrizes().setEnabled(true);
					getBtnAdd().setEnabled(true);
				}
			});
			btnRoulette.setIcon(new ImageIcon(MainWindow.class.getResource("/roulette/img/ruleta.png")));
			btnRoulette.setBounds(21, 152, 224, 226);
		}
		return btnRoulette;
	}

	private JTextField getTxtPoints() {
		if (txtPoints == null) {
			txtPoints = new JTextField();
			txtPoints.setEditable(false);
			txtPoints.setFont(new Font("Tahoma", Font.BOLD, 22));
			txtPoints.setHorizontalAlignment(SwingConstants.CENTER);
			txtPoints.setBounds(82, 84, 104, 44);
			txtPoints.setColumns(10);
		}
		return txtPoints;
	}

	private JComboBox<Prize> getCbPrizes() {
		if (cbPrizes == null) {
			cbPrizes = new JComboBox<Prize>();
			cbPrizes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lblPrizeIcon.setIcon(pr.getIconFor((Prize) getCbPrizes().getSelectedItem()));
				}
			});
			cbPrizes.setEnabled(false);
			cbPrizes.setBounds(341, 113, 388, 44);
			cbPrizes.setModel(new DefaultComboBoxModel<Prize>(pr.getPrices()));
		}
		return cbPrizes;
	}

	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add!");
			btnAdd.setMnemonic('a');
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Prize p = (Prize) getCbPrizes().getSelectedItem();
					int points = pr.getPoints();
					if (points < p.getPoints()) {
						JOptionPane.showMessageDialog(null, "You don't have enough points!");
					} else {
						pr.addPrize(p);
						pr.setPoints(points - p.getPoints());
						getTxtPoints().setText(String.valueOf(pr.getPoints()));
						validate();
						getBtnFinish().setEnabled(true);
					}
				}
			});
			btnAdd.setEnabled(false);
			btnAdd.setBounds(766, 112, 89, 46);
		}
		return btnAdd;
	}

	private JButton getBtnFinish() {
		if (btnFinish == null) {
			btnFinish = new JButton("Finish");
			btnFinish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getCbPrizes().setEnabled(false);
					getBtnAdd().setEnabled(false);
					getBtnFinish().setEnabled(false);
					JOptionPane.showMessageDialog(null, "Congrats! Enjoy the prize!");
				}
			});
			btnFinish.setEnabled(false);
			btnFinish.setMnemonic('f');
			btnFinish.setBounds(770, 445, 104, 55);
		}
		return btnFinish;
	}

	private JLabel getLblPrizeIcon() {
		if (lblPrizeIcon == null) {
			lblPrizeIcon = new JLabel("");
			lblPrizeIcon.setBounds(468, 260, 134, 107);
		}
		return lblPrizeIcon;
	}
}
