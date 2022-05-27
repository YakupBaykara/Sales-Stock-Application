package tr.com.satisvestok.utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import tr.com.satisvestok.dal.AccountDAL;
import tr.com.satisvestok.fe.KategoriDuzenleFE;
import tr.com.satisvestok.fe.KategoriEkleFE;
import tr.com.satisvestok.fe.LoginFE;
import tr.com.satisvestok.fe.MusteriEkleFE;
import tr.com.satisvestok.fe.PersonelEkleFE;
import tr.com.satisvestok.fe.SehirEkleFE;
import tr.com.satisvestok.fe.SifreİslemleriFE;
import tr.com.satisvestok.fe.UrunEkleFE; 
import tr.com.satisvestok.fe.YetkiEkleFE;
import tr.com.satisvestok.model.PersonelModel;

public class AnaMenuler {
		
	public static JMenuBar initBar() {
		
		JMenuBar bar = new JMenuBar();
		
		// Dosya Menüsü
		
		JMenu dosyaMenu = new JMenu("Dosya");
		bar.add(dosyaMenu);
		
		JMenuItem çıkışItem = new JMenuItem("Çıkış");
		dosyaMenu.add(çıkışItem);
		
		// Ürün Menüsü
	    
		JMenu urunMenu = new JMenu("Ürünler");
		bar.add(urunMenu);
		
		JMenuItem urunEkleItem = new JMenuItem("Ürün Ekle");
		urunMenu.add(urunEkleItem);
		urunEkleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new UrunEkleFE();
						
					}
				});
			}
		});
		
		JMenuItem kategoriEkleItem = new JMenuItem("Kategori Ekle");
		urunMenu.add(kategoriEkleItem);
		kategoriEkleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new KategoriEkleFE();
						
					}
				});
				
			}
		});
		
		urunMenu.addSeparator();
		
		JMenuItem urunDuzenleItem = new JMenuItem("Ürün Düzenle");
		urunMenu.add(urunDuzenleItem);
		
		JMenuItem kategoriDuzenleItem = new JMenuItem("Kategori Düzenle");
		urunMenu.add(kategoriDuzenleItem);
		kategoriDuzenleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new KategoriDuzenleFE();
			}
		});
		
		// Personel Menüsü
		
		JMenu personelMenu = new JMenu("Personel");
		bar.add(personelMenu);
		
		JMenuItem personelEkle = new JMenuItem("Personel Ekle");
		personelMenu.add(personelEkle);
		personelEkle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						
						new PersonelEkleFE();
					}
				});
			}
		});
		
		JMenuItem yetkiEkle = new JMenuItem("Yetki Ekle");
		personelMenu.add(yetkiEkle);
		yetkiEkle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						
						new YetkiEkleFE();
					}
				});
			}
		});
		
		JMenuItem sifreBelirle = new JMenuItem("Şifre Belirle");
		personelMenu.add(sifreBelirle);
		sifreBelirle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						
						new SifreİslemleriFE();
					}
				});
			}
		});
		
		personelMenu.addSeparator();
		
		JMenuItem personelDuzenle = new JMenuItem("Personel Düzenle");
		personelMenu.add(personelDuzenle);
		
		JMenuItem yetkiDuzenle = new JMenuItem("Yetki Düzenle");
		personelMenu.add(yetkiDuzenle);
		
		JMenuItem sifreDuzenle = new JMenuItem("Şifre Düzenle");
		personelMenu.add(sifreDuzenle);
		
		// Müşteri Menüsü
		
		JMenu musteriMenu = new JMenu("Müşteri");
		bar.add(musteriMenu);
		
		JMenuItem musteriEkle = new JMenuItem("Müşteri Ekle");
		musteriMenu.add(musteriEkle);
		musteriEkle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						
						new MusteriEkleFE();
					}
				});
			}
		});
		
		
		JMenuItem sehirEkle = new JMenuItem("Şehir Ekle");
		musteriMenu.add(sehirEkle);
		sehirEkle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						
						new SehirEkleFE();
					}
				});
			}
		});
		
		musteriMenu.addSeparator();
		
		JMenuItem musteriDuzenle = new JMenuItem("Müşteri Düzenle");
		musteriMenu.add(musteriDuzenle);
		
		JMenuItem sehirDuzenle = new JMenuItem("Şehir Düzenle");
		musteriMenu.add(sehirDuzenle);
		
		PersonelModel model = (PersonelModel) LoginFE.userNameBox.getSelectedItem();
		
		if(new AccountDAL().GetYetki(model.getId()).getYetkiId()== 2) {
		
			personelMenu.hide();
		
		}else if(new AccountDAL().GetYetki(model.getId()).getYetkiId()== 3) {
			
			personelMenu.hide();
			musteriMenu.hide();
			urunMenu.hide();
		}
		
		return bar;
	}
}
