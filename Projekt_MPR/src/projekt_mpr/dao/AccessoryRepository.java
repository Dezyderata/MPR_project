package projekt_mpr.dao;

import java.sql.*;
import java.util.ArrayList;

import projekt_mpr.dao.mappers.ResultSetMapper;
import projekt_mpr.domain.Accessory;


public class AccessoryRepository extends RepositoryBase<Accessory>{
	
	public AccessoryRepository(Connection connection, ResultSetMapper <Accessory> mapper) throws SQLException{
		super(connection, mapper);
	}
	@Override
	protected String createTableSql() {
		return "CREATE TABLE accessories("
				+ "id INT PRIMARY KEY AUTO_INCREMENT,"
				+ "name VARCHAR(20),"
				+ "price FLOAT(6,2)"
				+ ")";
	}
	@Override
	protected String insertSql() {
		return "INSERT INTO accessories(name,price) VALUES (?,?)";
	}
	@Override
	protected String updateSql() {
		return "UPDATE accessories SET (name, price) = (?,?) WHERE id=?";
	}
	@Override
	protected String deleteSql() {
		return "DELETE FROM accessories WHERE id=?";
	}
	@Override
	protected String selectAllSql() {
		return "SELECT * FROM accessories";
	}
	@Override
	protected String tableName() {
		return "accessories";
	}
	@Override
	protected void parametrizeUpdateStatement(Accessory accessory) throws SQLException {
		update.setString(1, accessory.getName());
		update.setDouble(2, accessory.getPrice());
		update.setInt(3, accessory.getId());
		
	}
	@Override
	protected void parametrizeInsertStatement(Accessory accessory) throws SQLException {
		insert.setString(1, accessory.getName());
		insert.setDouble(2, accessory.getPrice());
		
	}
}
