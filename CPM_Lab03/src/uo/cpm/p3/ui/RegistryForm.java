package uo.cpm.p3.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class RegistryForm extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton btnCancel;
	private JButton btnNext;
	private JPanel pnCustomerInformation;
	private JLabel lblNameAndSurname;
	private JTextField txtNameAndSurname;
	private JComboBox<String> cbBirthYear;
	private JPasswordField password1;
	private JPasswordField password2;
	private JLabel lblBirthDate;
	private JLabel lblPassword1;
	private JLabel lblPassword2;
	private JPanel pnOrder;
	private JRadioButton rdbtnOnSite;
	private JRadioButton rdbtnTakeAway;

	private final ButtonGroup buttonGroup = new ButtonGroup();

	private MainWindow mw = null;
	private ConfirmationDialog cd = null;

	/**
	 * Create the frame.
	 */
	public RegistryForm(MainWindow mainWindow) {

		this.mw = mainWindow;

		setResizable(false);
		setTitle("McDonalds: Customer Information");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnCancel());
		contentPane.add(getBtnNext());
		contentPane.add(getPnCustomerInformation());
		contentPane.add(getPnOrder());

		this.getRootPane().setDefaultButton(getBtnNext());
	}

	public MainWindow getMw() {
		return mw;
	}

	public ConfirmationDialog getCd() {
		return cd;
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.setMnemonic('c');
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mw.initialize();
				}
			});
			btnCancel.setForeground(Color.WHITE);
			btnCancel.setBackground(Color.RED);
			btnCancel.setBounds(571, 361, 103, 39);
		}
		return btnCancel;
	}

	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Next");
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkFields();
				}
			});
			btnNext.setForeground(Color.WHITE);
			btnNext.setBackground(Color.GREEN);
			btnNext.setBounds(458, 361, 103, 39);
		}
		return btnNext;
	}

	private void checkFields() {
		if (getTxtNameAndSurname().getText().isBlank() || String.valueOf(getPassword1().getPassword()).isBlank()
				|| String.valueOf(getPassword2().getPassword()).isBlank()) {
			JOptionPane.showMessageDialog(null, "None of the fields can be empty");
		} else if (!String.valueOf(getPassword1().getPassword()).equals(String.valueOf(getPassword2().getPassword()))) {
			JOptionPane.showMessageDialog(null, "The password fields don't match");
		} else {
			this.cd = new ConfirmationDialog(this);
			this.cd.setLocationRelativeTo(contentPane);
			this.cd.setModal(true);
			this.cd.setVisible(true);
		}
	}

	private JPanel getPnCustomerInformation() {
		if (pnCustomerInformation == null) {
			pnCustomerInformation = new JPanel();
			pnCustomerInformation.setBorder(
					new TitledBorder(null, "Customer Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnCustomerInformation.setBackground(Color.WHITE);
			pnCustomerInformation.setBounds(10, 11, 664, 270);
			pnCustomerInformation.setLayout(null);
			pnCustomerInformation.add(getLblNameAndSurname());
			pnCustomerInformation.add(getTxtNameAndSurname());
			pnCustomerInformation.add(getCbBirthYear());
			pnCustomerInformation.add(getPassword1());
			pnCustomerInformation.add(getPassword2());
			pnCustomerInformation.add(getLblBirthDate());
			pnCustomerInformation.add(getLblPassword1());
			pnCustomerInformation.add(getLblPassword2());
		}
		return pnCustomerInformation;
	}

	private JLabel getLblNameAndSurname() {
		if (lblNameAndSurname == null) {
			lblNameAndSurname = new JLabel("Name and Surname:");
			lblNameAndSurname.setDisplayedMnemonic('n');
			lblNameAndSurname.setLabelFor(getTxtNameAndSurname());
			lblNameAndSurname.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNameAndSurname.setBounds(10, 24, 135, 27);
		}
		return lblNameAndSurname;
	}

	private JTextField getTxtNameAndSurname() {
		if (txtNameAndSurname == null) {
			txtNameAndSurname = new JTextField();
			txtNameAndSurname.setBounds(186, 23, 426, 32);
			txtNameAndSurname.setColumns(10);
		}
		return txtNameAndSurname;
	}

	private JComboBox<String> getCbBirthYear() {
		if (cbBirthYear == null) {
			cbBirthYear = new JComboBox<String>();
			cbBirthYear.setModel(
					new DefaultComboBoxModel(IntStream.rangeClosed(1920, 2020).mapToObj(String::valueOf).toArray()));
			cbBirthYear.setBounds(186, 81, 194, 38);
		}
		return cbBirthYear;
	}

	private JPasswordField getPassword1() {
		if (password1 == null) {
			password1 = new JPasswordField();
			password1.setBounds(186, 149, 426, 38);
		}
		return password1;
	}

	private JPasswordField getPassword2() {
		if (password2 == null) {
			password2 = new JPasswordField();
			password2.setBounds(186, 209, 426, 38);
		}
		return password2;
	}

	private JLabel getLblBirthDate() {
		if (lblBirthDate == null) {
			lblBirthDate = new JLabel("Birthdate:");
			lblBirthDate.setLabelFor(getCbBirthYear());
			lblBirthDate.setDisplayedMnemonic('b');
			lblBirthDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblBirthDate.setBounds(10, 85, 135, 27);
		}
		return lblBirthDate;
	}

	private JLabel getLblPassword1() {
		if (lblPassword1 == null) {
			lblPassword1 = new JLabel("Password:");
			lblPassword1.setLabelFor(getPassword1());
			lblPassword1.setDisplayedMnemonic('p');
			lblPassword1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPassword1.setBounds(10, 149, 135, 27);
		}
		return lblPassword1;
	}

	private JLabel getLblPassword2() {
		if (lblPassword2 == null) {
			lblPassword2 = new JLabel("Repeat Password");
			lblPassword2.setLabelFor(getPassword2());
			lblPassword2.setDisplayedMnemonic('r');
			lblPassword2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPassword2.setBounds(10, 209, 135, 27);
		}
		return lblPassword2;
	}

	private JPanel getPnOrder() {
		if (pnOrder == null) {
			pnOrder = new JPanel();
			pnOrder.setLayout(null);
			pnOrder.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Order",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnOrder.setBackground(Color.WHITE);
			pnOrder.setBounds(10, 292, 390, 108);
			pnOrder.add(getRdbtnOnSite());
			pnOrder.add(getRdbtnTakeAway());
		}
		return pnOrder;
	}

	private JRadioButton getRdbtnOnSite() {
		if (rdbtnOnSite == null) {
			rdbtnOnSite = new JRadioButton("On site");
			rdbtnOnSite.setMnemonic('o');
			rdbtnOnSite.setSelected(true);
			buttonGroup.add(rdbtnOnSite);
			rdbtnOnSite.setBackground(Color.WHITE);
			rdbtnOnSite.setBounds(40, 45, 109, 23);
		}
		return rdbtnOnSite;
	}

	private JRadioButton getRdbtnTakeAway() {
		if (rdbtnTakeAway == null) {
			rdbtnTakeAway = new JRadioButton("Take away");
			rdbtnTakeAway.setMnemonic('t');
			buttonGroup.add(rdbtnTakeAway);
			rdbtnTakeAway.setBackground(Color.WHITE);
			rdbtnTakeAway.setBounds(215, 45, 109, 23);
		}
		return rdbtnTakeAway;
	}
}
