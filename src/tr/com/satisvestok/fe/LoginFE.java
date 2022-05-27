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
import javax.swing.JTextField;

import tr.com.satisvestok.dal.AccountDAL;
import tr.com.satisvestok.dal.PersonelDAL;
import tr.com.satisvestok.interfaces.FeInterface;
import tr.com.satisvestok.model.PersonelModel;

public class LoginFE extends JDialog implements FeInterface{

	public static JComboBox userNameBox;
	
	public LoginFE() {
		
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		add(panel);
		
		setTitle("Lütfen Giriş Yapınız!");
		setVisible(true);
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {

		JPanel panel = new JPanel(new GridLayout(3,2));
		panel.setBorder(BorderFactory.createTitledBorder("Lütfen Bilgilerinizi Giriniz!"));
		
		JLabel userNameLabel = new JLabel("Kullanıcı Adı: ");
		panel.add(userNameLabel);
		
		userNameBox = new JComboBox(new PersonelDAL().getAll().toArray());
		panel.add(userNameBox);
		
		JLabel sifreLabel = new JLabel("Şifre: ");
		panel.add(sifreLabel);
		
		JPasswordField sifreField = new JPasswordField(10);
		panel.add(sifreField);
		
		JButton loginButon = new JButton("Giriş Yap");
		panel.add(loginButon);
		loginButon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PersonelModel model = (PersonelModel) userNameBox.getSelectedItem();
				String sifre = sifreField.getText();
				
				if(new AccountDAL().GetIdVeSifre(model.getId(), sifre).getId() != 0) {
					
					new AnaPencereFE();
					dispose();
					
				}else {
					
					JOptionPane.showMessageDialog(null, "Hatalı giriş yaptınız!");
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
