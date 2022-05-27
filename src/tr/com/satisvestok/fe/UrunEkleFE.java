package tr.com.satisvestok.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import tr.com.satisvestok.dal.KategoriDAL;
import tr.com.satisvestok.dal.UrunDAL;
import tr.com.satisvestok.interfaces.FeInterface;
import tr.com.satisvestok.model.KategoriModel;
import tr.com.satisvestok.model.UrunModel;

public class UrunEkleFE extends JDialog implements FeInterface{

	public UrunEkleFE() {

		initPencere();
	}

	@Override
	public void initPencere() {
		
		JPanel panel = initPanel();
		
		add(panel);
		setTitle("Ürün Ekle");
		setVisible(true);
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);		
	}

	@Override
	public JPanel initPanel() {

		JPanel panel = new JPanel(new GridLayout(5, 2));
		panel.setBorder(BorderFactory.createTitledBorder("Ürün Kayıt Alanı"));
		
		JLabel adiLabel = new JLabel("Ürün Adı: ");
		panel.add(adiLabel);
		JTextField adiField = new JTextField(10);
		panel.add(adiField);
		
		JLabel kategoriLabel = new JLabel("Kategori Seç: ");
		panel.add(kategoriLabel);
		JComboBox kategoriBox = new JComboBox(new KategoriDAL().getAllParentId().toArray());
		panel.add(kategoriBox);
		
		JLabel tarihLabel = new JLabel("Tarih Seç: ");
		panel.add(tarihLabel);
		JDateChooser tarihDate = new JDateChooser();
		panel.add(tarihDate);
		
		JLabel fiyatLabel = new JLabel("Fiyat Gir: ");
		panel.add(fiyatLabel);
		
		JTextField fiyatField = new JTextField(10);
		panel.add(fiyatField);
		
		JButton kaydetButon = new JButton("Kaydet");
		panel.add(kaydetButon);
		kaydetButon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				UrunModel model = new UrunModel();
				KategoriModel casModel = (KategoriModel) kategoriBox.getSelectedItem();
				SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
				String date =format.format(tarihDate.getDate());
				
				model.setAdi(adiField.getText());
				model.setKategoriId(casModel.getId());
				model.setTarih(date);
				model.setFiyat(Float.parseFloat(fiyatField.getText()));
				
				new UrunDAL().insert(model);
				JOptionPane.showMessageDialog(null, model.getAdi()+ " başarıyla eklendi!");
			}
		});
		
		JButton iptalButon = new JButton("İptal");
		panel.add(iptalButon);
		
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
