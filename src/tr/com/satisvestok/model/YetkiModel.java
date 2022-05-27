package tr.com.satisvestok.model;

public class YetkiModel {
	
	private int id;
	private String adi;
	
	// Getter - Setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}
	@Override
	public String toString() {
		return adi;
	}
		
}
