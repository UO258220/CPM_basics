package uo.cpm.p8.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import uo.cpm.p8.player.MusicPlayer;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private MusicPlayer mp;

	private JPanel contentPane;
	private JPanel pnNorth;
	private JLabel lblLogo;
	private JSlider slVolume;
	private JPanel pnVol;
	private JLabel lblVol;
	private JTextField txtVol;
	private JPanel pnCenter;
	private JPanel pnLibrary;
	private JPanel pnPlaylist;
	private JLabel lblLibrary;
	private JPanel pnLibraryButtons;
	private JButton btnAdd;
	private JButton btnDelete;
	private JScrollPane spLibrary;
	private JList list1;
	private JLabel lblPlaylist;
	private JPanel pnPlaylistButtons;
	private JButton btnRew;
	private JButton btnPlay;
	private JButton btnStop;
	private JButton btnFor;
	private JButton btnDel;
	private JScrollPane spPlaylist;
	private JList list2;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnHelp;
	private JMenu mnPlay;
	private JMenu mnOptions;
	private JMenuItem mntmOpen;
	private JSeparator separator;
	private JMenuItem mntmExit;
	private JMenuItem mntmContent;
	private JSeparator separator_1;
	private JMenuItem mntmAbout;

	private DefaultListModel modelList1 = null;
	private DefaultListModel modelList2 = null;

	private JFileChooser selector = null;

	/**
	 * Create the frame.
	 * 
	 * @param mp
	 */
	public MainWindow(MusicPlayer mp) {
		setTitle("EII Mp3 Player");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/logoTitulo.png")));

		this.mp = mp;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnNorth(), BorderLayout.NORTH);
		contentPane.add(getPnCenter(), BorderLayout.CENTER);
	}

	private JFileChooser getSelector() {
		if (selector == null) {
			selector = new JFileChooser();
			selector.setMultiSelectionEnabled(true);
		}
		return selector;
	}

	private JPanel getPnNorth() {
		if (pnNorth == null) {
			pnNorth = new JPanel();
			pnNorth.setLayout(new GridLayout(1, 0, 0, 0));
			pnNorth.add(getLblLogo());
			pnNorth.add(getSlVolume());
			pnNorth.add(getPnVol());
		}
		return pnNorth;
	}

	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(MainWindow.class.getResource("/img/logo.png")));
		}
		return lblLogo;
	}

	private JSlider getSlVolume() {
		if (slVolume == null) {
			slVolume = new JSlider();
			slVolume.setPaintTicks(true);
			slVolume.setPaintLabels(true);
			slVolume.setMinorTickSpacing(10);
			slVolume.setMajorTickSpacing(20);
		}
		return slVolume;
	}

	private JPanel getPnVol() {
		if (pnVol == null) {
			pnVol = new JPanel();
			pnVol.add(getLblVol());
			pnVol.add(getTxtVol());
		}
		return pnVol;
	}

	private JLabel getLblVol() {
		if (lblVol == null) {
			lblVol = new JLabel("Vol:");
			lblVol.setFont(new Font("Arial", Font.BOLD, 40));
		}
		return lblVol;
	}

	private JTextField getTxtVol() {
		if (txtVol == null) {
			txtVol = new JTextField();
			txtVol.setEditable(false);
			txtVol.setHorizontalAlignment(SwingConstants.CENTER);
			txtVol.setText("50");
			txtVol.setFont(new Font("Arial", Font.BOLD, 50));
			txtVol.setColumns(2);
		}
		return txtVol;
	}

	private JPanel getPnCenter() {
		if (pnCenter == null) {
			pnCenter = new JPanel();
			pnCenter.setLayout(new GridLayout(1, 0, 0, 0));
			pnCenter.add(getPnLibrary());
			pnCenter.add(getPnPlaylist());
		}
		return pnCenter;
	}

	private JPanel getPnLibrary() {
		if (pnLibrary == null) {
			pnLibrary = new JPanel();
			pnLibrary.setLayout(new BorderLayout(0, 0));
			pnLibrary.add(getLblLibrary(), BorderLayout.NORTH);
			pnLibrary.add(getPnLibraryButtons(), BorderLayout.SOUTH);
			pnLibrary.add(getSpLibrary(), BorderLayout.CENTER);
		}
		return pnLibrary;
	}

	private JPanel getPnPlaylist() {
		if (pnPlaylist == null) {
			pnPlaylist = new JPanel();
			pnPlaylist.setLayout(new BorderLayout(0, 0));
			pnPlaylist.add(getLblPlaylist(), BorderLayout.NORTH);
			pnPlaylist.add(getPnPlaylistButtons(), BorderLayout.SOUTH);
			pnPlaylist.add(getSpPlaylist(), BorderLayout.CENTER);
		}
		return pnPlaylist;
	}

	private JLabel getLblLibrary() {
		if (lblLibrary == null) {
			lblLibrary = new JLabel("\u266A Library:");
		}
		return lblLibrary;
	}

	private JPanel getPnLibraryButtons() {
		if (pnLibraryButtons == null) {
			pnLibraryButtons = new JPanel();
			pnLibraryButtons.setLayout(new GridLayout(1, 0, 0, 0));
			pnLibraryButtons.add(getBtnAdd());
			pnLibraryButtons.add(getBtnDelete());
		}
		return pnLibraryButtons;
	}

	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add to PlayList");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List selected = list1.getSelectedValuesList();
					for (Object o : selected) {
						modelList2.addElement(o);
					}
				}
			});
			btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnAdd.setMnemonic('a');
		}
		return btnAdd;
	}

	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List selected = list1.getSelectedValuesList();
					for (Object o : selected) {
						modelList1.removeElement(o);
					}
				}
			});
			btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnDelete.setMnemonic('d');
		}
		return btnDelete;
	}

	private JScrollPane getSpLibrary() {
		if (spLibrary == null) {
			spLibrary = new JScrollPane();
			spLibrary.setViewportView(getList1());
		}
		return spLibrary;
	}

	private JList getList1() {
		if (list1 == null) {
			list1 = new JList();
			modelList1 = new DefaultListModel();
			list1.setModel(modelList1);
		}
		return list1;
	}

	private JLabel getLblPlaylist() {
		if (lblPlaylist == null) {
			lblPlaylist = new JLabel("\u266A Playlist:");
		}
		return lblPlaylist;
	}

	private JPanel getPnPlaylistButtons() {
		if (pnPlaylistButtons == null) {
			pnPlaylistButtons = new JPanel();
			pnPlaylistButtons.setLayout(new GridLayout(1, 0, 0, 0));
			pnPlaylistButtons.add(getBtnRew());
			pnPlaylistButtons.add(getBtnPlay());
			pnPlaylistButtons.add(getBtnStop());
			pnPlaylistButtons.add(getBtnFor());
			pnPlaylistButtons.add(getBtnDel());
		}
		return pnPlaylistButtons;
	}

	private JButton getBtnRew() {
		if (btnRew == null) {
			btnRew = new JButton("\u25C4\u25C4");
			btnRew.setMnemonic('r');
			btnRew.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return btnRew;
	}

	private JButton getBtnPlay() {
		if (btnPlay == null) {
			btnPlay = new JButton("\u25BA");
			btnPlay.setMnemonic('p');
			btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return btnPlay;
	}

	private JButton getBtnStop() {
		if (btnStop == null) {
			btnStop = new JButton("\u25A0");
			btnStop.setMnemonic('s');
			btnStop.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return btnStop;
	}

	private JButton getBtnFor() {
		if (btnFor == null) {
			btnFor = new JButton("\u25BA\u25BA");
			btnFor.setMnemonic('f');
			btnFor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return btnFor;
	}

	private JButton getBtnDel() {
		if (btnDel == null) {
			btnDel = new JButton("Del");
			btnDel.setMnemonic('l');
			btnDel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return btnDel;
	}

	private JScrollPane getSpPlaylist() {
		if (spPlaylist == null) {
			spPlaylist = new JScrollPane();
			spPlaylist.setViewportView(getList2());
		}
		return spPlaylist;
	}

	private JList getList2() {
		if (list2 == null) {
			list2 = new JList();
			list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			modelList2 = new DefaultListModel();
			list2.setModel(modelList2);
		}
		return list2;
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnPlay());
			menuBar.add(getMnOptions());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.add(getMntmOpen());
			mnFile.add(getSeparator());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}

	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.add(getMntmContent());
			mnHelp.add(getSeparator_1());
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}

	private JMenu getMnPlay() {
		if (mnPlay == null) {
			mnPlay = new JMenu("Play");
		}
		return mnPlay;
	}

	private JMenu getMnOptions() {
		if (mnOptions == null) {
			mnOptions = new JMenu("Options");
		}
		return mnOptions;
	}

	private JMenuItem getMntmOpen() {
		if (mntmOpen == null) {
			mntmOpen = new JMenuItem("Open");
			mntmOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Integer response = getSelector().showOpenDialog(rootPane);
					if (response == JFileChooser.APPROVE_OPTION) {
						for (File f : getSelector().getSelectedFiles()) {
							modelList1.addElement(f);
						}
					}
				}
			});
		}
		return mntmOpen;
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}

	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
		}
		return mntmExit;
	}

	private JMenuItem getMntmContent() {
		if (mntmContent == null) {
			mntmContent = new JMenuItem("Content");
		}
		return mntmContent;
	}

	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}

	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
		}
		return mntmAbout;
	}
}
