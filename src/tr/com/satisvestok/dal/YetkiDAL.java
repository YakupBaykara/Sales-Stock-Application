package tr.com.satisvestok.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.satisvestok.core.DbHelper;
import tr.com.satisvestok.interfaces.DALInterface;
import tr.com.satisvestok.model.YetkiModel;

public class YetkiDAL extends DbHelper implements DALInterface<YetkiModel> {

	@Override
	public void insert(YetkiModel model) {
		
		Connection connection = getConnection();
		
		try {
			
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO yetki (Adi) VALUES ('"+model.getAdi()+"')");
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public List<YetkiModel> getAll() {
		
		List<YetkiModel> dataModel = new ArrayList<YetkiModel>();
		Connection connection = getConnection();
		YetkiModel model;
	
		try {
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM yetki");
			while(resultSet.next()) {
				model = new YetkiModel();
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
	public YetkiModel delete(YetkiModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(YetkiModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public YetkiModel getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
