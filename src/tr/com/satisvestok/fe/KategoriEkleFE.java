package tr.com.satisvestok.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import tr.com.satisvestok.dal.KategoriDAL;
import tr.com.satisvestok.interfaces.FeInterface;
import tr.com.satisvestok.model.KategoriModel;

public class KategoriEkleFE extends JDialog implements FeInterface {

	public KategoriEkleFE() {
		
		initPencere();
	}

	@Override
	public void initPencere() {
		
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Kategori Ekleme Alanı"));
		
		add(panel);
		setTitle("Kategori Ekle");
		setVisible(true);
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		
		JPanel panel = new JPanel(new GridLayout(3,2));
		
		JLabel adiLabel = new JLabel("Kategori Adı Gir: ");
		panel.add(adiLabel);
		JTextField adiField = new JTextField(10);
		panel.add(adiField);
		
		JLabel anaKategoriLabel = new JLabel("Ana Kategori Seç");
		panel.add(anaKategoriLabel);
		JComboBox anaKategoriBox = new JComboBox(new KategoriDAL().getAllParentId().toArray());
		panel.add(anaKategoriBox);
		anaKategoriBox.insertItemAt("--Kategori Seçiniz--", 0);
		anaKategoriBox.setSelectedIndex(0);
				
		JButton kaydetButon = new JButton("Kaydet");
		panel.add(kaydetButon);
		kaydetButon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				KategoriModel model = new KategoriModel();
				
				if(anaKategoriBox.getSelectedIndex()!=0) {
					
					KategoriModel casModel = (KategoriModel) anaKategoriBox.getSelectedItem();
					model.setAdi(adiField.getText());
					model.setParentId(casModel.getId());
					
					new KategoriDAL().insert(model);
					JOptionPane.showMessageDialog(null, model.getAdi()+ " başarıyla eklendi!");
				}else {
					
					model.setAdi(adiField.getText());
					model.setParentId(anaKategoriBox.getSelectedIndex());
					
					new KategoriDAL().insert(model);
					JOptionPane.showMessageDialog(null, model.getAdi()+ " başarıyla eklendi!");
				}					
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
