package tr.com.satisvestok.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tr.com.satisvestok.dal.KategoriDAL;
import tr.com.satisvestok.interfaces.FeInterface;

public class KategoriDuzenleFE extends JDialog implements FeInterface{

	public KategoriDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		
		JPanel 	panel = initPanel();
		add(panel);
		setTitle("Kategori Düzenle");
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Düzenleme İşlemleri"));
		JPanel ustPanel = new JPanel(new GridLayout(3,2));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Kategori Düzenle"));
		
		JLabel kategoriAdilabel = new JLabel("Kategori Adı: ");
		ustPanel.add(kategoriAdilabel);
		
		JTextField kategoriAdiField = new JTextField(10);
		ustPanel.add(kategoriAdiField);
		
		JLabel ustKategoriAdiLabel = new JLabel("Üst Kategori Adı: ");
		ustPanel.add(ustKategoriAdiLabel);
		
		JComboBox ustKategoriBox = new JComboBox(new KategoriDAL().getAllParentId().toArray());
		ustPanel.add(ustKategoriBox);
		
		JList kategorList = new JList();
		kategorList.setListData(new KategoriDAL().getAll().toArray());
		JScrollPane pane = new JScrollPane(kategorList);
		pane.setBorder(BorderFactory.createTitledBorder("Düzenlenecek Liste"));
		kategorList.setSelectedIndex(0);

		kategoriAdiField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				kategorList.setListData(new KategoriDAL().GetSearchKategori(kategoriAdiField.getText()).toArray());
				kategorList.setSelectedIndex(0);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
		
		panel.add(ustPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);
		
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JTabbedPane initTab() {
		// TODO Auto-generated method stub
		return null;
	}

}
