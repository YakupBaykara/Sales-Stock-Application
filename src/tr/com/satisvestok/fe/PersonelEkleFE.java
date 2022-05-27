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

import tr.com.satisvestok.dal.PersonelDAL;
import tr.com.satisvestok.interfaces.FeInterface;
import tr.com.satisvestok.model.PersonelModel;

public class PersonelEkleFE extends JDialog implements FeInterface{

	public PersonelEkleFE() {

		initPencere();
	}

	@Override
	public void initPencere() {
		
		JPanel panel = initPanel();
		add(panel);
		setTitle("Personel Ekleme Menüsü");
		setVisible(true);
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		
		JPanel panel = new JPanel(new GridLayout(3,2));
		panel.setBorder(BorderFactory.createTitledBorder("Personel Ekleme Alanı"));
		
		JLabel adiSoyadiLabel = new JLabel("Personel Adı");
		panel.add(adiSoyadiLabel);
		
		JTextField adiSoyadiField = new JTextField(10);
		panel.add(adiSoyadiField);
		
		JLabel emailLabel = new JLabel("Personel Email");
		panel.add(emailLabel);
		
		JTextField emailField = new JTextField(10);
		panel.add(emailField);
		
		JButton kaydetButon = new JButton("Kaydet");
		panel.add(kaydetButon);
		kaydetButon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PersonelModel model = new PersonelModel();
				model.setAdisoyadi(adiSoyadiField.getText());
				model.setEmail(emailField.getText());
				
				new PersonelDAL().insert(model);
				JOptionPane.showMessageDialog(null, model.getAdisoyadi()+ " başarıyla eklendi!");
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
