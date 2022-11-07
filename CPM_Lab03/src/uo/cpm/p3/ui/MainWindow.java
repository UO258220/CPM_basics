package uo.cpm.p3.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import uo.cpm.p3.model.Product;
import uo.cpm.p3.service.McDonalds;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblLogo;
	private JLabel lblMcDonalds;
	private JLabel lblProducts;
	private JComboBox cbProducts;
	private JLabel lblUnits;
	private JSpinner spUnits;
	private JButton btnAdd;
	private JLabel lblOrderPrice;
	private JTextField txtOrderPrice;
	private JButton btnNext;
	private JButton btnCancel;

	private RegistryForm rF = null;

	private McDonalds mcDonalds = null;
	private JButton btnRemove;

	/**
	 * Create the frame.
	 * 
	 * @param mcDonalds
	 */
	public MainWindow(McDonalds mcDonalds) {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (checkExit()) {
					System.exit(0);
				}
			}
		});

		this.mcDonalds = mcDonalds;

		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/uo/cpm/p3/ui/img/logo.PNG")));
		setTitle("McDonalds");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblLogo());
		contentPane.add(getLblMcDonalds());
		contentPane.add(getLblProducts());
		contentPane.add(getCbProducts());
		contentPane.add(getLblUnits());
		contentPane.add(getSpUnits());
		contentPane.add(getBtnAdd());
		contentPane.add(getLblOrderPrice());
		contentPane.add(getTxtOrderPrice());
		contentPane.add(getBtnNext());
		contentPane.add(getBtnCancel());

		this.getRootPane().setDefaultButton(getBtnNext());
		contentPane.add(getBtnRemove());
	}

	public McDonalds getMcDonalds() {
		return mcDonalds;
	}

	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(MainWindow.class.getResource("/uo/cpm/p3/ui/img/logo.PNG")));
			lblLogo.setBounds(45, 24, 191, 148);
		}
		return lblLogo;
	}

	private JLabel getLblMcDonalds() {
		if (lblMcDonalds == null) {
			lblMcDonalds = new JLabel("McDonalds");
			lblMcDonalds.setFont(new Font("Arial", Font.BOLD, 52));
			lblMcDonalds.setBounds(271, 72, 345, 60);
		}
		return lblMcDonalds;
	}

	private JLabel getLblProducts() {
		if (lblProducts == null) {
			lblProducts = new JLabel("Products:");
			lblProducts.setDisplayedMnemonic('p');
			lblProducts.setLabelFor(getCbProducts());
			lblProducts.setFont(new Font("Arial", Font.PLAIN, 16));
			lblProducts.setBounds(39, 205, 76, 27);
		}
		return lblProducts;
	}

	private JComboBox getCbProducts() {
		if (cbProducts == null) {
			cbProducts = new JComboBox();
			cbProducts.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getSpUnits().setValue(1);
				}
			});
			cbProducts.setFont(new Font("Arial", Font.PLAIN, 16));
			cbProducts.setBounds(39, 253, 324, 27);
			cbProducts.setModel(new DefaultComboBoxModel(mcDonalds.getMenuProducts()));
		}
		return cbProducts;
	}

	private JLabel getLblUnits() {
		if (lblUnits == null) {
			lblUnits = new JLabel("Units:");
			lblUnits.setDisplayedMnemonic('u');
			lblUnits.setLabelFor(getSpUnits());
			lblUnits.setFont(new Font("Arial", Font.PLAIN, 16));
			lblUnits.setBounds(429, 211, 46, 14);
		}
		return lblUnits;
	}

	private JSpinner getSpUnits() {
		if (spUnits == null) {
			spUnits = new JSpinner();
			spUnits.setFont(new Font("Arial", Font.PLAIN, 16));
			spUnits.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spUnits.setBounds(428, 253, 57, 27);
		}
		return spUnits;
	}

	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
			btnAdd.setMnemonic('a');
			btnAdd.setFont(new Font("Arial", Font.PLAIN, 16));
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Product selectedItem = (Product) getCbProducts().getSelectedItem();
					int units = (int) getSpUnits().getValue();
					mcDonalds.addToOrder(selectedItem, units);
					getTxtOrderPrice().setText(String.format("%.2f", mcDonalds.getOrderTotal()));
					checkNextEnabled();
				}
			});
			btnAdd.setForeground(Color.WHITE);
			btnAdd.setBackground(Color.GREEN);
			btnAdd.setBounds(507, 254, 93, 25);
		}
		return btnAdd;
	}
	
	private JButton getBtnRemove() {
		if (btnRemove == null) {
			btnRemove = new JButton("Remove");
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Product selectedItem = (Product) getCbProducts().getSelectedItem();
					int units = (int) getSpUnits().getValue();
					mcDonalds.removeFromOrder(selectedItem, units);
					getTxtOrderPrice().setText(String.format("%.2f", mcDonalds.getOrderTotal()));
					checkNextEnabled();
				}
			});
			btnRemove.setMnemonic('r');
			btnRemove.setForeground(Color.WHITE);
			btnRemove.setFont(new Font("Arial", Font.PLAIN, 16));
			btnRemove.setBackground(Color.GREEN);
			btnRemove.setBounds(610, 254, 93, 25);
		}
		return btnRemove;
	}

	private JLabel getLblOrderPrice() {
		if (lblOrderPrice == null) {
			lblOrderPrice = new JLabel("Order Price:");
			lblOrderPrice.setFont(new Font("Arial", Font.PLAIN, 16));
			lblOrderPrice.setBounds(429, 310, 93, 27);
		}
		return lblOrderPrice;
	}

	private JTextField getTxtOrderPrice() {
		if (txtOrderPrice == null) {
			txtOrderPrice = new JTextField();
			txtOrderPrice.setToolTipText("Total price of the order");
			txtOrderPrice.setFont(new Font("Arial", Font.PLAIN, 16));
			txtOrderPrice.setEditable(false);
			txtOrderPrice.setBounds(429, 348, 274, 36);
			txtOrderPrice.setColumns(10);
		}
		return txtOrderPrice;
	}

	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Next");
			btnNext.setEnabled(false);
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showCustomerInformationDialog();
				}
			});
			btnNext.setFont(new Font("Arial", Font.PLAIN, 16));
			btnNext.setBackground(Color.GREEN);
			btnNext.setForeground(Color.WHITE);
			btnNext.setBounds(519, 514, 113, 36);
		}
		return btnNext;
	}

	private void showCustomerInformationDialog() {
		this.rF = new RegistryForm(this);
		this.rF.setLocationRelativeTo(contentPane);
		this.rF.setModal(true);
		this.rF.setVisible(true);
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					initialize();
				}
			});
			btnCancel.setMnemonic('c');
			btnCancel.setFont(new Font("Arial", Font.PLAIN, 16));
			btnCancel.setForeground(Color.WHITE);
			btnCancel.setBackground(Color.RED);
			btnCancel.setBounds(651, 514, 113, 36);
		}
		return btnCancel;
	}

	public void initialize() {
		if (this.rF != null) {

			if (this.rF.getCd() != null) {
				this.rF.getCd().dispose();
			}

			this.rF.dispose();

		}

		this.mcDonalds.initOrder();
		this.cbProducts.setSelectedIndex(0);
		this.spUnits.setValue(1);
		this.txtOrderPrice.setText("");
		this.btnNext.setEnabled(false);
	}

	private boolean checkExit() {
		return JOptionPane.showConfirmDialog(this,
				"Are you sure you want to leave and cancel the order?") == JOptionPane.YES_OPTION;
	}

	private void checkNextEnabled() {
		getBtnNext().setEnabled(!mcDonalds.orderIsEmpty());
	}
}
