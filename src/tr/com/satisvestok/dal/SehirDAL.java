package tr.com.satisvestok.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.satisvestok.core.DbHelper;
import tr.com.satisvestok.interfaces.DALInterface;
import tr.com.satisvestok.model.SehirModel;

public class SehirDAL extends DbHelper implements DALInterface<SehirModel>{

	@Override
	public void insert(SehirModel model) {
		
		Connection connection = getConnection();
		try {
			
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO sehir (Adi) VALUES ('"+model.getAdi()+"')");
			
			statement.close();
			connection.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<SehirModel> getAll() {
		
		List<SehirModel> dataModel = new ArrayList<SehirModel>();
		Connection connection = getConnection();
		
		try {
			SehirModel model;
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM sehir");
			while (resultSet.next()) {
				
				model = new SehirModel();
				model.setId(resultSet.getInt("Id"));
				model.setAdi(resultSet.getString("Adi"));
				
				dataModel.add(model);
			}
			resultSet.close();
			statement.close();
			connection.close();			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
				
		return dataModel;
	}

	@Override
	public SehirModel delete(SehirModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SehirModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SehirModel getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
