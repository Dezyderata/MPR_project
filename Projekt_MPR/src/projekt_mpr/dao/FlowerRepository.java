package projekt_mpr.dao;

import java.sql.*;
import java.util.ArrayList;

import projekt_mpr.domain.Flower;

public class FlowerRepository extends RepositoryBase<Flower> {

	public FlowerRepository() {
		super();
	}
	@Override
	public String createTableSql() {
		return "CREATE TABLE flowers("
				+ "id INT GENERETED BY DEFAULT AS IDENTITY, "
				+ "name VARECHARE(20), "
				+ "latinName VARCHARE(40), "
				+ "price FLOAT(6,2)"
				+ ")";
	}
	@Override
	public String insertSql() {
		return "INSERT INTO flowers(name, latinName, price) VALUES (?,?,?)";
	}
	@Override
	public String deleteSql() {
		return "DELETE FROM flowers WHERE id=?";
	}
	@Override
	public String updateSql() {
		return "UPDATE flowers SET (name, latinName, price) = (?,?,?) WHERE id=?";
	}
	@Override
	public String selectAllSql() {
		return "SELECT * FROM flowers";
	}
	@Override
	public String tableName() {
		return "flowers";
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
			e.printStackTrace();
		}
		return result;
	}
	@Override
	protected void parametrizeUpdateStatement(PreparedStatement statement, Flower flower) throws SQLException {
		statement.setString(1, flower.getName());
		statement.setString(2, flower.getLatinName());
		statement.setFloat(3, flower.getPrice());
		statement.setInt(4, flower.getId());	
		
	}
	@Override
	protected void parametrizeInsertStatement(PreparedStatement statement, Flower flower) throws SQLException {
		statement.setString(1, flower.getName());
		statement.setString(2, flower.getLatinName());
		statement.setFloat(3, flower.getPrice());
		
	}
}