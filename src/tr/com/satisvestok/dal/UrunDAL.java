package tr.com.satisvestok.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.satisvestok.core.DbHelper;
import tr.com.satisvestok.interfaces.DALInterface;
import tr.com.satisvestok.model.UrunModel;

public class UrunDAL extends DbHelper implements DALInterface<UrunModel> {

	@Override
	public void insert(UrunModel model) {

		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO urun (Adi, KategoriId, Tarih, Fiyat) VALUES "
					+ "('"+model.getAdi()+"',  "+model.getKategoriId()+", '"+model.getTarih()+"', "+model.getFiyat()+")");
			statement.close();
			connection.close();				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<UrunModel> getAll() {
		
		List<UrunModel> dataModel = new ArrayList<UrunModel>();
		UrunModel model;
		
		Connection connection = getConnection();
		try {
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM urun");
			
			while(resultSet.next()) {
				model = new UrunModel();
				model.setId(resultSet.getInt("Id"));
				model.setAdi(resultSet.getString("Adi"));
				model.setKategoriId(resultSet.getInt("KategoriId"));
				model.setTarih(resultSet.getString("Tarih"));
				model.setFiyat(resultSet.getFloat("Fiyat"));
				
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
	public UrunModel delete(UrunModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(UrunModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UrunModel getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
