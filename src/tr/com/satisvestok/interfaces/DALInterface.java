package tr.com.satisvestok.interfaces;

import java.util.List;

public interface DALInterface <T> {
	
	public void insert (T model);
	public List<T> getAll();
	public T delete (T model);
	public void update (T model);
	public T getById (int id);
	
}
 