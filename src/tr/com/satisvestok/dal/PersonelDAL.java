package tr.com.satisvestok.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.satisvestok.core.DbHelper;
import tr.com.satisvestok.interfaces.DALInterface;
import tr.com.satisvestok.model.PersonelModel;

public class PersonelDAL extends DbHelper implements DALInterface<PersonelModel> {

	@Override
	public void insert(PersonelModel model) {
		
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO personel (AdiSoyadi, Email) "
					+ "VALUES ('"+model.getAdisoyadi()+"', '"+model.getEmail()+"')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<PersonelModel> getAll() {
		
		List<PersonelModel> dataModel = new ArrayList<PersonelModel>();
		PersonelModel model;
		Connection connection = getConnection();
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM personel");
			while(resultSet.next()) {
				
				model = new PersonelModel();
				model.setId(resultSet.getInt("Id"));
				model.setAdisoyadi(resultSet.getString("AdiSoyadi"));
				model.setEmail(resultSet.getString("Email"));
				
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
	public PersonelModel delete(PersonelModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(PersonelModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PersonelModel getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
