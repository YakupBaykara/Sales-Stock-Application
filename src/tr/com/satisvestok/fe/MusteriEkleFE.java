package tr.com.satisvestok.fe;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tr.com.satisvestok.dal.MusteriDAL;
import tr.com.satisvestok.dal.SehirDAL;
import tr.com.satisvestok.interfaces.FeInterface;
import tr.com.satisvestok.model.MusteriModel;
import tr.com.satisvestok.model.SehirModel;

public class MusteriEkleFE extends JDialog implements FeInterface {

	public MusteriEkleFE() {
		
		initPencere();
	}

	@Override
	public void initPencere() {
		
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Müşteri Ekleme Alanı"));
		add(panel);
		
		setTitle("Müşteri Ekle");
		setVisible(true);
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		
		JPanel panel = new JPanel(new BorderLayout());
		JPanel fieldPanel = new JPanel(new GridLayout(3,2));
		JPanel butonPanel = new JPanel(new GridLayout(1,2));
		
		JLabel adiSoyadiLabel = new JLabel("Müşteri Adı");
		fieldPanel.add(adiSoyadiLabel);
		
		JTextField adiSoyadiField = new JTextField(10);
		fieldPanel.add(adiSoyadiField);
		
		JLabel telefonLabel = new JLabel("Müşteri Telefonu");
		fieldPanel.add(telefonLabel);
		
		JTextField telefonField = new JTextField(10);
		fieldPanel.add(telefonField);
		
		JLabel sehirLabel = new JLabel("Şehir Seç");
		fieldPanel.add(sehirLabel);
		
		JComboBox sehirBox = new JComboBox(new SehirDAL().getAll().toArray());
		fieldPanel.add(sehirBox);
//		sehirBox.insertItemAt("--Sehir Seçiniz--", 0);
//		sehirBox.setSelectedIndex(0);
		
//		JLabel adresLabel = new JLabel("Açık Adres:");
//		fieldPanel.add(adresLabel);
		
		JTextArea adresField = new JTextArea(5,1);
		JScrollPane pane = new JScrollPane(adresField);
		pane.setBorder(BorderFactory.createTitledBorder("Açık Adres Alanı"));
		
		JButton kaydetButon = new JButton("Kaydet");
		butonPanel.add(kaydetButon);
		kaydetButon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				MusteriModel model = new MusteriModel();
//				
//				if(sehirBox.getSelectedIndex() != 0) {
//					
  			    SehirModel casModel = (SehirModel) sehirBox.getSelectedItem();
//					model.setAdiSoyadi(adiSoyadiField.getText());
//					model.setTelefon(telefonField.getText());
//					model.setSehirId(casModel.getId());
//					model.setAdres(adresField.getText());	
//					
//					new MusteriDAL().insert(model);
//					JOptionPane.showMessageDialog(null, model.getAdiSoyadi()+ " başarıyla eklendi!");					
				
//				} else {

					model.setAdiSoyadi(adiSoyadiField.getText());
					model.setTelefon(telefonField.getText());
					model.setSehirId(casModel.getId());
					model.setAdres(adresField.getText());	
					
					new MusteriDAL().insert(model);
					JOptionPane.showMessageDialog(null, model.getAdiSoyadi()+ " başarıyla eklendi!");							
//				}

				

			}
		});
		
		JButton iptalButon = new JButton("İptal");
		butonPanel.add(iptalButon);		
		
		panel.add(fieldPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);
		panel.add(butonPanel, BorderLayout.SOUTH);
		
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
