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
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;

import tr.com.satisvestok.dal.AccountDAL;
import tr.com.satisvestok.dal.PersonelDAL;
import tr.com.satisvestok.dal.YetkiDAL;
import tr.com.satisvestok.interfaces.FeInterface;
import tr.com.satisvestok.model.AccountModel;
import tr.com.satisvestok.model.PersonelModel;
import tr.com.satisvestok.model.YetkiModel;

public class SifreİslemleriFE extends JDialog implements FeInterface {

	public SifreİslemleriFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		
		JPanel panel = initPanel();
		add(panel);
		
		setTitle("Şifre Belirleme Menüsü");
		setVisible(true);
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {

		JPanel panel = new JPanel(new GridLayout(5,2));
		panel.setBorder(BorderFactory.createTitledBorder("Şifre Belirleme Alanı"));
		
		JLabel personelLabel = new JLabel("Personel Seç");
		panel.add(personelLabel);
		
		JComboBox personelBox = new JComboBox(new PersonelDAL().getAll().toArray());
		panel.add(personelBox);
		
		JLabel yetkiLabel = new JLabel("Yetki Seç");
		panel.add(yetkiLabel);
		
		JComboBox yetkiBox = new JComboBox(new YetkiDAL().getAll().toArray());
		panel.add(yetkiBox);
		
		JLabel sifreLabel = new JLabel("Şifre Gir");
		panel.add(sifreLabel);
		
		JPasswordField sifreField = new JPasswordField(10);
		panel.add(sifreField);
		
		JLabel sifreTekrarLabel = new JLabel("Şifreyi Tekrar Gir");
		panel.add(sifreTekrarLabel);
		
		JPasswordField sifreTekrarField = new JPasswordField(10);
		panel.add(sifreTekrarField);
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				AccountModel model = new AccountModel();
				PersonelModel pModel = (PersonelModel) personelBox.getSelectedItem();
				YetkiModel yModel = (YetkiModel) yetkiBox.getSelectedItem();
				
				if (sifreField.getText().equals(sifreTekrarField.getText())) {
					
					model.setPersonelId(pModel.getId());
					model.setSifre(sifreField.getText());
					model.setYetkiId(yModel.getId());
					
					new AccountDAL().insert(model);
					JOptionPane.showMessageDialog(null, "Şifre başaıyla eklendi!");
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Şifreler uyuşmuyor!");

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
