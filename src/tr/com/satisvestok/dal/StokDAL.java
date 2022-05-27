package tr.com.satisvestok.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.satisvestok.core.DbHelper;
import tr.com.satisvestok.interfaces.DALInterface;
import tr.com.satisvestok.model.StokModel;
import tr.com.satisvestok.model.complex.StokComplexModel;
import tr.com.satisvestok.model.complex.StokComplexTotalModel;

public class StokDAL extends DbHelper implements DALInterface<StokModel> {

	@Override
	public void insert(StokModel model) {

		Connection connection = getConnection();

		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(
					"INSERT INTO stok (PersonelId, UrunId, Tarih, Adet)" + "VALUES (" + model.getPersonelId() + ", "
							+ model.getUrunId() + ", '" + model.getTarih() + "', " + model.getAdet() + ")");

			statement.close();
			connection.close();

		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public List<StokComplexModel> GetAllStok() {

		List<StokComplexModel> dataModel = new ArrayList<StokComplexModel>();
		StokComplexModel model;

		Connection connection = getConnection();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT s.Id,  u.Adi, p.AdiSoyadi, s.Adet, s.Tarih FROM stok s"
					+ "					LEFT JOIN urun u ON s.UrunId = u.Id"
					+ "					LEFT JOIN personel p ON s.PersonelId = p.Id "
					+ "                 ORDER BY s.Id DESC");

			while (resultSet.next()) {

				model = new StokComplexModel();
				model.setId(resultSet.getInt("s.Id"));
				model.setUrunAdi(resultSet.getString("u.Adi"));
				model.setPersonelAdi(resultSet.getString("p.AdiSoyadi"));
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

	public List<StokComplexTotalModel> GetTotalStok() {

		List<StokComplexTotalModel> dataModel = new ArrayList<StokComplexTotalModel>();
		StokComplexTotalModel model;

		Connection connection = getConnection();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT sum(s.Adet) AS Toplam,"
					+ "					s.Id,  u.Adi, p.AdiSoyadi, s.Adet, s.Tarih FROM stok s"
					+ "					LEFT JOIN urun u ON s.UrunId = u.Id"
					+ "					LEFT JOIN personel p ON s.PersonelId = p.Id"
					+ "					GROUP BY s.UrunId");
			while (resultSet.next()) {

				model = new StokComplexTotalModel();
				model.setId(resultSet.getInt("s.Id"));
				model.setUrunAdi(resultSet.getString("u.Adi"));
				model.setPersonelAdi(resultSet.getString("p.AdiSoyadi"));
				model.setAdet(resultSet.getInt("s.Adet"));
				model.setTarih(resultSet.getString("s.Tarih"));
				model.setToplam(resultSet.getInt("Toplam"));

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
	public List<StokModel> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StokModel delete(StokModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(StokModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public StokModel getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
