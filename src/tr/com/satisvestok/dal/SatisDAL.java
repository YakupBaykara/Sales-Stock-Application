package tr.com.satisvestok.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.satisvestok.core.DbHelper;
import tr.com.satisvestok.interfaces.DALInterface;
import tr.com.satisvestok.model.SatisModel;
import tr.com.satisvestok.model.complex.SatisComplexModel;

public class SatisDAL extends DbHelper implements DALInterface<SatisModel> {

	@Override
	public void insert(SatisModel model) {
		
		Connection connection = getConnection();
		
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO satis (PersonelId, UrunId, MusterilId, Tarih, Adet)"
					+ "VALUES ("+model.getPersonelId()+","+model.getUrunId()+","+model.getMusteriId()+",'"+model.getTarih()+"',"+model.getAdet()+")");
			
			statement.close();
			connection.close();
		
		} catch (SQLException e) {			
			System.out.println(e);
		}
	}
	
	public List<SatisComplexModel> GetAllSatis() {
		
		List<SatisComplexModel> dataModel = new ArrayList<SatisComplexModel>();
		SatisComplexModel model;
		
		Connection connection = getConnection();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT s.Id, u.Adi, p.AdiSoyadi, m.AdiSoyadi, s.Adet, s.Tarih FROM satis s"
					+ "						LEFT jOIN urun u ON s.UrunId = u.Id"
					+ "						LEFT JOIN personel p ON s.PersonelId = p.Id"
					+ "						LEFT JOIN musteri m ON s.MusterilId = m.Id"
					+ "						ORDER BY s.Id DESC");
			
			while(resultSet.next()) {
				
				model = new SatisComplexModel();
				model.setId(resultSet.getInt("s.Id"));
				model.setUrunAdi(resultSet.getString("u.Adi"));
				model.setPersonelAdi(resultSet.getString("p.AdiSoyadi"));
				model.setMusteriAdi(resultSet.getString("m.AdiSoyadi"));
				model.setAdet(resultSet.getInt("s.Adet"));
				model.setTarih(resultSet.getString("s.Tarih"));
				
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
	public List<SatisModel> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SatisModel delete(SatisModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SatisModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SatisModel getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
