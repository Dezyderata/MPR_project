package projekt_mpr.dao;

import java.sql.*;
import java.util.ArrayList;

import projekt_mpr.domain.Flower;

public class FlowerRepository {
	Connection connection;
	
	private String createTableSql = "CREATE TABLE flowers("
			+ "id INT GENERETED BY DEFAULT AS IDENTITY, "
			+ "name VARECHARE(20), "
			+ "latinName VARCHARE(40), "
			+ "price FLOAT(6,2)"
			+ ")";
	private String insertSql = "INSERT INTO flowers(name, latinName, price) VALUES (?,?,?)";
	private String deleteSql = "DELETE FROM flowers WHERE id=?";
	private String updateSql = "UPDATE flowers SET (name, latinName, price) = (?,?,?) WHERE id=?";
	private String selectAllSql = "SELECT * FROM flowers";
	
	private Statement createTable;
	private PreparedStatement insert;
	private PreparedStatement delete;
	private PreparedStatement update;
	private PreparedStatement selectAll;
	
	public FlowerRepository() {
		try {
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			createTable = connection.createStatement();
			insert = connection.prepareStatement(insertSql);
			delete = connection.prepareStatement(deleteSql);
			update = connection.prepareStatement(updateSql);
			selectAll = connection.prepareStatement(selectAllSql);
		} catch (SQLException e) {
			System.err.println("Something went wrong during connecting flowers table");
			e.printStackTrace();
		}
	}
	public ArrayList<Flower> selectAll(){
		ArrayList<Flower> result = new ArrayList<>(); 
		try {
			ResultSet rs = selectAll.executeQuery();
			while(rs.next()) {
				Flower f = new Flower();
				f.setId(rs.getInt("id'"));
				f.setName(rs.getString("name"));
				f.setLatinName(rs.getString("latinName"));
				f.setPrice(rs.getFloat("price"));
				result.add(f);
			}
		} catch (SQLException e) {
			System.err.println("Something went wrong during select all flowers");
			e.printStackTrace();
		}
		return result;
	}
	public void update(Flower flower) {
		try {
			update.setString(1, flower.getName());
			update.setString(2, flower.getLatinName());
			update.setFloat(3, flower.getPrice());
			update.setInt(4, flower.getId());
			update.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Something went wrong during updating flower");
			e.printStackTrace();
		}
		
	}
	public void delete(Flower flower) {
		try {
			delete.setInt(1, flower.getId());
			delete.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Somtehing went wrong during deleting flower");
			e.printStackTrace();
		}
	}
	public void insert(Flower flower) {
		try {
			insert.setString(1, flower.getName());
			insert.setString(2, flower.getLatinName());
			insert.setFloat(3, flower.getPrice());
			insert.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Something went wrong during adding flower to db");
			e.printStackTrace();
		}
		
	}
	public void createTable() {
		try {
			boolean tableExist = false;
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			while(rs.next()) {
				if(rs.getString("TABLE_NAME").equalsIgnoreCase("flowers")) {
					tableExist = true;
					break;
				}
			}
			if(!tableExist) {
				createTable.executeUpdate(createTableSql);
			}
		} catch (SQLException e) {
			System.err.println("Something went wrong during creating flowers");
			e.printStackTrace();
		}
	}

}
