package tr.com.satisvestok.model.complex;

public class StokComplexModel {
	
	private int id;
	private String urunAdi;
	private String personelAdi;
	private int adet;
	private String tarih;
	
	// Getter - Setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrunAdi() {
		return urunAdi;
	}
	public void setUrunAdi(String urunAdi) {
		this.urunAdi = urunAdi;
	}
	public String getPersonelAdi() {
		return personelAdi;
	}
	public void setPersonelAdi(String personelAdi) {
		this.personelAdi = personelAdi;
	}
	public int getAdet() {
		return adet;
	}
	public void setAdet(int adet) {
		this.adet = adet;
	}
	public String getTarih() {
		return tarih;
	}
	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
	
	public Object[] getData() {
		Object[] data = {id, urunAdi, personelAdi, adet, tarih};
		return data;
	}
	
	@Override
	public String toString() {
		
		return personelAdi+" "+urunAdi;
	}

}
