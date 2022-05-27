package tr.com.satisvestok.dal;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tr.com.satisvestok.core.DbHelper;
import tr.com.satisvestok.interfaces.DALInterface;
import tr.com.satisvestok.model.KategoriModel;

public class KategoriDAL extends DbHelper implements DALInterface<KategoriModel> {

	@Override
	public void insert(KategoriModel model) {

		Connection connection = getConnection();
		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO kategori (Adi, ParentId) VALUES " + "('" + model.getAdi() + "',"
					+ model.getParentId() + ")");
			statement.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<KategoriModel> getAll() {

		List<KategoriModel> dataModel = new ArrayList<KategoriModel>();
		Connection connection = getConnection();
		KategoriModel model;
		
		try {

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM kategori");
			while (resultSet.next()) {
				model = new KategoriModel();
				model.setId(resultSet.getInt("Id"));
				model.setAdi(resultSet.getString("Adi"));
				model.setParentId(resultSet.getInt("ParentId"));
				dataModel.add(model);
			}
			resultSet.close();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataModel;
	}

	public List<KategoriModel> getAllParentId() {

		List<KategoriModel> dataModel = new ArrayList<KategoriModel>();
		Connection connection = getConnection();
		KategoriModel model;
		try {

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM kategori WHERE ParentId = 0");
			while (resultSet.next()) {
				model = new KategoriModel();
				model.setId(resultSet.getInt("Id"));
				model.setAdi(resultSet.getString("Adi"));
				model.setParentId(resultSet.getInt("ParentId"));
				dataModel.add(model);

			}
			return dataModel;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataModel;
	}
	
	public List<KategoriModel> GetSearchKategori(String kategoriAdi) {
		
		List<KategoriModel> dataModel = new ArrayList<KategoriModel>();
		KategoriModel model;
		
		Connection connection = getConnection();
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM kategori WHERE Adi LIKE '"+"%"+kategoriAdi+"%"+"'");
			
			while(resultSet.next()) {
				
				model = new KategoriModel();
				//model.setId(resultSet.getInt(""));
				model.setAdi(resultSet.getString("Adi"));
				model.setParentId(resultSet.getInt("ParentId"));
				
				dataModel.add(model);
			}
		
		} catch (SQLException e) {			
			System.out.println(e);
		}
		
		return dataModel;
	}

	@Override
	public KategoriModel delete(KategoriModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(KategoriModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public KategoriModel getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
