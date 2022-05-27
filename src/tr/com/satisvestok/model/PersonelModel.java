package tr.com.satisvestok.model;

public class PersonelModel {
	
	private int id;
	private String adisoyadi;
	private String email;
	
	// Getter - Setter 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdisoyadi() {
		return adisoyadi;
	}
	public void setAdisoyadi(String adisoyadi) {
		this.adisoyadi = adisoyadi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return adisoyadi;
	}

}
