package tr.com.satisvestok.model;

public class MusteriModel {
	
	private int id;
	private String adiSoyadi;
	private String telefon;
	private int sehirId;
	private String adres;
	
	// Getter - Setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdiSoyadi() {
		return adiSoyadi;
	}
	public void setAdiSoyadi(String adiSoyadi) {
		this.adiSoyadi = adiSoyadi;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public int getSehirId() {
		return sehirId;
	}
	public void setSehirId(int sehirId) {
		this.sehirId = sehirId;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	@Override
	public String toString() {
		return adiSoyadi;
	}
	
}
