package uo.cpm.p3.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmationDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblOk;
	private JLabel lblMessage;
	private JLabel lblOrderCode;
	private JTextField txtCode;
	private JButton btnFinish;
	
	private RegistryForm rf = null;

	/**
	 * Create the dialog.
	 */
	public ConfirmationDialog(RegistryForm registryForm) {
		
		this.rf = registryForm;
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblOk());
		getContentPane().add(getLblMessage());
		getContentPane().add(getLblOrderCode());
		getContentPane().add(getTxtCode());
		getContentPane().add(getBtnFinish());
		setTitle("McDonalds: Order Confirmation");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConfirmationDialog.class.getResource("/uo/cpm/p3/ui/img/logo.PNG")));
		setBounds(100, 100, 500, 300);

		getTxtCode().setText(this.rf.getMw().getMcDonalds().getOrderCode());
	}

	private JLabel getLblOk() {
		if (lblOk == null) {
			lblOk = new JLabel("");
			lblOk.setIcon(new ImageIcon(ConfirmationDialog.class.getResource("/uo/cpm/p3/ui/img/ok.png")));
			lblOk.setBounds(10, 28, 64, 62);
		}
		return lblOk;
	}
	private JLabel getLblMessage() {
		if (lblMessage == null) {
			lblMessage = new JLabel("We are processing your order");
			lblMessage.setFont(new Font("Arial", Font.BOLD, 26));
			lblMessage.setBounds(84, 23, 371, 78);
		}
		return lblMessage;
	}
	private JLabel getLblOrderCode() {
		if (lblOrderCode == null) {
			lblOrderCode = new JLabel("The order code is:");
			lblOrderCode.setFont(new Font("Arial", Font.PLAIN, 16));
			lblOrderCode.setBounds(119, 122, 134, 25);
		}
		return lblOrderCode;
	}
	private JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField();
			txtCode.setFont(new Font("Arial", Font.PLAIN, 16));
			txtCode.setEditable(false);
			txtCode.setBounds(263, 112, 134, 46);
			txtCode.setColumns(10);
		}
		return txtCode;
	}
	private JButton getBtnFinish() {
		if (btnFinish == null) {
			btnFinish = new JButton("Finish");
			btnFinish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rf.getMw().getMcDonalds().saveOrder();
					System.exit(0);
				}
			});
			btnFinish.setFont(new Font("Arial", Font.PLAIN, 16));
			btnFinish.setBackground(Color.GREEN);
			btnFinish.setForeground(Color.WHITE);
			btnFinish.setBounds(367, 212, 94, 38);
		}
		return btnFinish;
	}
}
