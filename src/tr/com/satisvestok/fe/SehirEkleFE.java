package tr.com.satisvestok.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tr.com.satisvestok.dal.SehirDAL;
import tr.com.satisvestok.interfaces.FeInterface;
import tr.com.satisvestok.model.SehirModel;

public class SehirEkleFE extends JDialog implements FeInterface{

	public SehirEkleFE() {
		
		initPencere();
	}

	@Override
	public void initPencere() {
		
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Şehir Ekleme Alanı"));
		add(panel);
		
		setTitle("Şehir Ekle");
		setVisible(true);
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		
		JPanel panel = new JPanel(new GridLayout(2,2));
		
		JLabel sehirLabel = new JLabel("Şehir Adı Gir: ");
		panel.add(sehirLabel);
		
		JTextField sehirField = new JTextField(10);
		panel.add(sehirField);
		
		JButton kaydetButon = new JButton("Kaydet");
		panel.add(kaydetButon);
		kaydetButon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SehirModel model = new SehirModel();
				
				model.setAdi(sehirField.getText());
				
				new SehirDAL().insert(model);
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
