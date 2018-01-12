package projekt_mpr.dao;

import java.sql.*;
import java.util.ArrayList;

import projekt_mpr.dao.mappers.ResultSetMapper;
import projekt_mpr.domain.Flower;

public class FlowerRepository extends RepositoryBase<Flower> {

	public FlowerRepository(Connection connection, ResultSetMapper<Flower> mapper) throws SQLException {
		super(connection, mapper);
	}
	@Override
	protected String createTableSql() {
		return "CREATE TABLE flowers("
				+ "id INT PRIMARY KEY AUTO_INCREMENT, "
				+ "name VARCHAR(20), "
				+ "latinName VARCHAR(40), "
				+ "price FLOAT(6,2)"
				+ ")";
	}
	@Override
	protected String insertSql() {
		return "INSERT INTO flowers(name, latinName, price) VALUES (?,?,?)";
	}
	@Override
	protected String deleteSql() {
		return "DELETE FROM flowers WHERE id=?";
	}
	@Override
	protected String updateSql() {
		return "UPDATE flowers SET (name, latinName, price) = (?,?,?) WHERE id=?";
	}
	@Override
	protected String selectAllSql() {
		return "SELECT * FROM flowers";
	}
	@Override
	protected String tableName() {
		return "flowers";
	}
	@Override
	protected void parametrizeUpdateStatement(Flower flower) throws SQLException {
		update.setString(1, flower.getName());
		update.setString(2, flower.getLatinName());
		update.setDouble(3, flower.getPrice());
		update.setInt(4, flower.getId());	
	}
	@Override
	protected void parametrizeInsertStatement(Flower flower) throws SQLException {
		insert.setString(1, flower.getName());
		insert.setString(2, flower.getLatinName());
		insert.setDouble(3, flower.getPrice());
	}
}