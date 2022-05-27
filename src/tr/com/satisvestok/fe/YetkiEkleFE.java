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

import tr.com.satisvestok.dal.YetkiDAL;
import tr.com.satisvestok.interfaces.FeInterface;
import tr.com.satisvestok.model.YetkiModel;

public class YetkiEkleFE extends JDialog implements FeInterface {

	public YetkiEkleFE() {
		
		initPencere();
	}

	@Override
	public void initPencere() {
		
		JPanel panel = initPanel();
		add(panel);
		
		setTitle("Yetki Ekle Menüsü");
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		
		JPanel panel = new JPanel(new GridLayout(2,2));
		panel.setBorder(BorderFactory.createTitledBorder("Yetki Ekleme Alanı"));
		
		JLabel adiLabel = new JLabel("Yetki Seç");
		panel.add(adiLabel);
		
		JTextField adiField = new JTextField(10);
		panel.add(adiField);
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				YetkiModel model = new YetkiModel();
				model.setAdi(adiField.getText());
				
				new YetkiDAL().insert(model);
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
