package tr.com.satisvestok.model;

public class KategoriModel {
	
	private int id;
	private int parentId;
	private String adi;
	
	// Getter - Setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
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
