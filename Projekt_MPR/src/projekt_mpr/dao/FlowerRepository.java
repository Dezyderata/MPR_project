package projekt_mpr.dao;

import java.sql.*;
import java.util.ArrayList;

import projekt_mpr.dao.mappers.ResultSetMapper;
import projekt_mpr.domain.Flower;

public class FlowerRepository extends RepositoryBase<Flower> {

	public FlowerRepository(ResultSetMapper<Flower> mapper) {
		super(mapper);
	}
	@Override
	protected String createTableSql() {
		return "CREATE TABLE flowers("
				+ "id INT GENERETED BY DEFAULT AS IDENTITY, "
				+ "name VARECHARE(20), "
				+ "latinName VARCHARE(40), "
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