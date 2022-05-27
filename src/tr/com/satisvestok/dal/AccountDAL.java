package tr.com.satisvestok.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import tr.com.satisvestok.core.DbHelper;
import tr.com.satisvestok.interfaces.DALInterface;
import tr.com.satisvestok.model.AccountModel;

public class AccountDAL extends DbHelper implements DALInterface<AccountModel> {

	@Override
	public void insert(AccountModel model) {
		
		Connection connection = getConnection();
		
		try {
			
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO account (PersonelId, Sifre, YetkiId)"
					+ "VALUES ("+model.getPersonelId()+", '"+model.getSifre()+"', "+model.getYetkiId()+")");
			
			statement.close();
			connection.close();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
	}
	
	public AccountModel GetIdVeSifre(int personelId, String sifre){
		
		AccountModel model = new AccountModel();
//		List<AccountModel> dataModel = new ArrayList<AccountModel>();
		
		Connection connection = getConnection();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM account  WHERE PersonelId = "+personelId+" AND Sifre = '"+sifre.trim()+"'");
				
			
			while(resultSet.next()) {
				model.setId(resultSet.getInt("Id"));
				model.setPersonelId(resultSet.getInt("PersonelId"));
				model.setSifre(resultSet.getString("Sifre"));
				model.setYetkiId(resultSet.getInt("YetkiId"));
				
//				dataModel.add(model);
			}
			
			resultSet.close();
			statement.close();
			connection.close();	
		
		} catch (SQLException e) {
			
			System.out.println(e);
		}
		
		return model;		
	}
	
	public AccountModel GetYetki(int personelId) {
		AccountModel model = new AccountModel();
		
		Connection connection = getConnection();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM account  WHERE PersonelId = "+personelId+"");
				
			
			while(resultSet.next()) {
				model.setId(resultSet.getInt("Id"));
				model.setPersonelId(resultSet.getInt("PersonelId"));
				model.setYetkiId(resultSet.getInt("YetkiId"));			
			}
			
			resultSet.close();
			statement.close();
			connection.close();	
		
		} catch (SQLException e) {
			
			System.out.println(e);
		}
		
		return model;		
		
	}
	
	@Override
	public List<AccountModel> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountModel delete(AccountModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(AccountModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AccountModel getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
