package projekt_mpr.dao;

import java.sql.*;
import java.util.ArrayList;

import projekt_mpr.domain.Fertilizer;

public class FertilizerRepository extends RepositoryBase<Fertilizer> {

	public FertilizerRepository() {
		super();
	}
	@Override
	public String createTableSql() {
		return "CREATE TABLE fertilizers("
				+ "id INT GENERATED BY DEFAULT AS IDENTITY, "
				+ "name VARCHAR(20), "
				+ "manufacturer VARCHAR(30), "
				+ "price FLOAT(6,2)"
				+ ")";
	}
	@Override
	public String insertSql() {
		return "INSERT INTO fertilizers(name, manufacturer, price) VALUES (?,?,?)";
	}
	@Override
	public String updateSql() {
		return "UPDATE fertilizers SET (name, manufacturer, price) =(?,?,?) WHERE id=?";
	}
	@Override
	public String deleteSql() {
		return "DELETE FROM fertilizers WHERE id =?";
	}
	@Override
	public String selectAllSql() {
		return "SELECT * FROM fertilizers";	
	}
	@Override
	public String tableName() {
		return "fertilizers";
	}
	
	public ArrayList<Fertilizer> selectAll(){
		ArrayList<Fertilizer> result = new ArrayList<Fertilizer>();
		try {
			ResultSet rs = selectAll.executeQuery();
			while(rs.next()) {
				Fertilizer f = new Fertilizer();
				f.setId(rs.getInt("id"));
				f.setName(rs.getString("name"));
				f.setManufacturer(rs.getString("manufacturer"));
				f.setPrice(rs.getFloat("price"));
				result.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	protected void parametrizeUpdateStatement(PreparedStatement statement, Fertilizer fertilizer) throws SQLException {
		statement.setString(1, fertilizer.getName());
		statement.setString(2, fertilizer.getManufacturer());
		statement.setFloat(3, fertilizer.getPrice());
		statement.setInt(4, fertilizer.getId());
		
	}
	@Override
	protected void parametrizeInsertStatement(PreparedStatement statement, Fertilizer fertilizer) throws SQLException {
		statement.setString(1, fertilizer.getName());
		statement.setString(2, fertilizer.getManufacturer());
		statement.setFloat(3, fertilizer.getPrice());
		
	}
}