package tr.com.satisvestok.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import tr.com.satisvestok.dal.MusteriDAL;
import tr.com.satisvestok.dal.SatisDAL;
import tr.com.satisvestok.dal.StokDAL;
import tr.com.satisvestok.dal.UrunDAL;
import tr.com.satisvestok.interfaces.FeInterface;
import tr.com.satisvestok.model.MusteriModel;
import tr.com.satisvestok.model.PersonelModel;
import tr.com.satisvestok.model.SatisModel;
import tr.com.satisvestok.model.StokModel;
import tr.com.satisvestok.model.UrunModel;
import tr.com.satisvestok.model.complex.SatisComplexModel;
import tr.com.satisvestok.model.complex.StokComplexModel;
import tr.com.satisvestok.model.complex.StokComplexTotalModel;
import tr.com.satisvestok.utility.AnaMenuler;

public class AnaPencereFE extends JFrame implements FeInterface {

	private static final long serialVersionUID = 1L;

	public AnaPencereFE() {

		initPencere();
	}

	@Override
	public void initPencere() {

		JPanel panel = initPanel();
		JMenuBar bar = initBar();

		add(panel);
		setJMenuBar(bar);
		setTitle("Satış ve Stok Projesi");
		setSize(600, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {

		JPanel panel = new JPanel(new BorderLayout());
		JTabbedPane pane = initTab();

		panel.add(pane, BorderLayout.CENTER);

		return panel;
	}

	@Override
	public JMenuBar initBar() {

		JMenuBar bar = AnaMenuler.initBar();
		return bar;
	}

	@Override
	public JTabbedPane initTab() {

		JTabbedPane pane = new JTabbedPane();
		ImageIcon Icon = new ImageIcon("icons/stok.png");

		// Stok Tab

		JPanel stokPanel = new JPanel(new BorderLayout());
		stokPanel.setBorder(BorderFactory.createTitledBorder("Stok İşlemleri"));

		JPanel stokSolPanel = new JPanel(new BorderLayout());
		JPanel stokSolUstPanel = new JPanel(new GridLayout(5, 2));
		JPanel stokSolAltPanel = new JPanel();

		Object[] stokKolon = { "Id", "Ürün Adı", "Personel Adı", "Adet", "Tarih", "Toplam" };
		DefaultTableModel stokTableModel = new DefaultTableModel(stokKolon, 0);
		JTable stokTable = new JTable(stokTableModel);
		JScrollPane stokPane = new JScrollPane(stokTable);
		stokPanel.add(stokPane, BorderLayout.CENTER);

		for (StokComplexModel model : new StokDAL().GetAllStok()) {

			stokTableModel.addRow(model.getData());
		}

		JLabel urunAdiLabel = new JLabel("Ürün Seç:");
		stokSolUstPanel.add(urunAdiLabel);

		JComboBox urunBox = new JComboBox(new UrunDAL().getAll().toArray());
		stokSolUstPanel.add(urunBox);

		JLabel adetLabel = new JLabel("Adet:");
		stokSolUstPanel.add(adetLabel);

		JTextField stokAdetField = new JTextField(10);
		stokSolUstPanel.add(stokAdetField);

		JLabel stokTarihLabel = new JLabel("Tarih:");
		stokSolUstPanel.add(stokTarihLabel);

		JDateChooser tarihField = new JDateChooser();
		stokSolUstPanel.add(tarihField);

		JButton stokKaydetButon = new JButton("Stok Ekle");
		stokSolUstPanel.add(stokKaydetButon);
		stokKaydetButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				StokModel model = new StokModel();
				UrunModel uModel = (UrunModel) urunBox.getSelectedItem();
				PersonelModel pModel = (PersonelModel) LoginFE.userNameBox.getSelectedItem();
				SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
				String date = format.format(tarihField.getDate());

				model.setPersonelId(pModel.getId());
				model.setUrunId(uModel.getId());
				model.setTarih(date);
				model.setAdet(Integer.parseInt(stokAdetField.getText()));

				new StokDAL().insert(model);
				JOptionPane.showMessageDialog(null, uModel.getAdi() + " stoklara eklenmiştir!");

				int satir = stokTableModel.getRowCount();
				for (int i = 0; i < satir; i++) {

					stokTableModel.removeRow(0);
				}
				for (StokComplexModel cModel : new StokDAL().GetAllStok()) {

					stokTableModel.addRow(cModel.getData());
				}
			}
		});

		JButton stokYenileButon = new JButton("Stok Yenile");
		stokSolUstPanel.add(stokYenileButon);
		stokYenileButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int satir = stokTableModel.getRowCount();
				for (int i = 0; i < satir; i++) {

					stokTableModel.removeRow(0);
				}
				for (StokComplexModel cModel : new StokDAL().GetAllStok()) {

					stokTableModel.addRow(cModel.getData());
				}
			}
		});
		
		JButton stokToplam = new JButton("Stok Özet");
		stokSolUstPanel.add(stokToplam);
		stokToplam.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int satir = stokTableModel.getRowCount();
				for (int i = 0; i < satir; i++) {

					stokTableModel.removeRow(0);
				}
				for (StokComplexTotalModel tModel : new StokDAL().GetTotalStok()) {

					stokTableModel.addRow(tModel.getData());
				}
			}
		});

		stokSolPanel.add(stokSolUstPanel, BorderLayout.NORTH);
		stokSolPanel.add(stokSolAltPanel);

		stokPanel.add(stokSolPanel, BorderLayout.WEST);

		// Satış Tab

		JPanel satisPanel = new JPanel(new BorderLayout());
		satisPanel.setBorder(BorderFactory.createTitledBorder("Satış İşelmleri"));

		JPanel satisSagPanel = new JPanel(new BorderLayout());
		JPanel satisSagUstPanel = new JPanel(new GridLayout(5, 2));
		JPanel satisSagAltPanel = new JPanel(new GridLayout());

		Object[] satisKolon = { "Id", "Ürün Adı", "Personel Adı", "Müşteri Adı", "Adet", "Tarih" };
		DefaultTableModel satisTableModel = new DefaultTableModel(satisKolon, 0);
		JTable satisTable = new JTable(satisTableModel);
		JScrollPane satisPane = new JScrollPane(satisTable);
		satisPanel.add(satisPane, BorderLayout.CENTER);
		
		for(SatisComplexModel model : new SatisDAL().GetAllSatis()) {
			
			satisTableModel.addRow(model.getData());
		}

		JLabel uAdiLabel = new JLabel("Ürün Seç: ");
		satisSagUstPanel.add(uAdiLabel);

		JComboBox uAdiBox = new JComboBox(new UrunDAL().getAll().toArray());
		satisSagUstPanel.add(uAdiBox);

		JLabel mAdiLabel = new JLabel("Müşteri Seç: ");
		satisSagUstPanel.add(mAdiLabel);

		JComboBox mAdiBox = new JComboBox(new MusteriDAL().getAll().toArray());
		satisSagUstPanel.add(mAdiBox);

		JLabel satisAdetLabel = new JLabel("Adet Gir: ");
		satisSagUstPanel.add(satisAdetLabel);

		JTextField satisAdetField = new JTextField(10);
		satisSagUstPanel.add(satisAdetField);

		JLabel satisTarihLabel = new JLabel("Tarih: ");
		satisSagUstPanel.add(satisTarihLabel);

		JDateChooser satisTarihField = new JDateChooser();
		satisSagUstPanel.add(satisTarihField);

		JButton satisYapButon = new JButton("Satış Yap");
		satisSagUstPanel.add(satisYapButon);
		satisYapButon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SatisModel model = new SatisModel();
				UrunModel uModel = (UrunModel) uAdiBox.getSelectedItem();
				PersonelModel pModel = (PersonelModel) LoginFE.userNameBox.getSelectedItem();
				MusteriModel mModel = (MusteriModel) mAdiBox.getSelectedItem();
				SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
				String date = format.format(satisTarihField.getDate());
				
				model.setUrunId(uModel.getId());
				model.setPersonelId(pModel.getId());
				model.setMusteriId(mModel.getId());
				model.setAdet(Integer.parseInt(satisAdetField.getText()));
				model.setTarih(date);
				
				new SatisDAL().insert(model);
				JOptionPane.showMessageDialog(null, "Satış İşlemi gerçekleşti!");
				
				int satir = satisTableModel.getRowCount();
				for (int i = 0; i < satir; i++) {

					satisTableModel.removeRow(0);
				}
				for (SatisComplexModel cModel : new SatisDAL().GetAllSatis()) {

					satisTableModel.addRow(cModel.getData());
				}
				
				// Stoktan düşme işlemi
				StokModel sModel = new StokModel();
				sModel.setPersonelId(pModel.getId());
				sModel.setUrunId(uModel.getId());
				sModel.setTarih(date);
				sModel.setAdet(-Integer.parseInt(satisAdetField.getText()));
				
				new StokDAL().insert(sModel);				
			}
		});

		JButton satisIptalButon = new JButton("İptal");
		satisSagUstPanel.add(satisIptalButon);

		satisSagPanel.add(satisSagUstPanel, BorderLayout.NORTH);
		satisSagPanel.add(satisSagAltPanel);

		satisPanel.add(satisSagPanel, BorderLayout.EAST);

		pane.addTab("Stok ", Icon, stokPanel);
		pane.addTab("Satış", Icon, satisPanel);

		return pane;
	}

}
