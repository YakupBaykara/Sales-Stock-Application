package tr.com.satisvestok.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.satisvestok.core.DbHelper;
import tr.com.satisvestok.interfaces.DALInterface;
import tr.com.satisvestok.model.MusteriModel;

public class MusteriDAL extends DbHelper implements DALInterface<MusteriModel>{

	@Override
	public void insert(MusteriModel model) {
		
		Connection connection = getConnection();
		
		try {	
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO musteri (AdiSoyadi, Telefon, Adres, SehirId)"
					+ "VALUES ('"+model.getAdiSoyadi()+"', '"+model.getTelefon()+"', '"+model.getAdres()+"',"+model.getSehirId()+")");
			
			statement.close();
			connection.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MusteriModel> getAll() {

		List<MusteriModel> dataModel = new ArrayList<MusteriModel>();
		MusteriModel model;
		
		Connection connection = getConnection();
		try {
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM musteri");
			while(resultSet.next()) {
				
				model = new MusteriModel();
				model.setId(resultSet.getInt("Id"));
				model.setAdiSoyadi(resultSet.getString("AdiSoyadi"));
				model.setTelefon(resultSet.getString("Telefon"));
				model.setAdres(resultSet.getString("Adres"));
				model.setSehirId(resultSet.getInt("SehirId"));
				
				dataModel.add(model);
			}
			resultSet.close();
			statement.close();
			connection.close();
		
		} catch (SQLException e) {
			System.out.println(e);
		}
		return dataModel;
	}

	@Override
	public MusteriModel delete(MusteriModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(MusteriModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MusteriModel getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
