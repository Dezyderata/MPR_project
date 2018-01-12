package projekt_mpr.dao;

import java.sql.*;
import java.util.ArrayList;

import projekt_mpr.dao.mappers.ResultSetMapper;
import projekt_mpr.dao.uow.UnitOfWork;
import projekt_mpr.domain.Fertilizer;

public class FertilizerRepository extends RepositoryBase<Fertilizer> {

	public FertilizerRepository(Connection connection, ResultSetMapper<Fertilizer> mapper, UnitOfWork uow) throws SQLException {
		super(connection, mapper, uow);
	}
	@Override
	protected String createTableSql() {
		return "CREATE TABLE fertilizers("
				+ "id INT PRIMARY KEY AUTO_INCREMENT, "
				+ "name VARCHAR(20), "
				+ "manufacturer VARCHAR(30), "
				+ "price FLOAT(6,2)"
				+ ")";
	}
	@Override
	protected String insertSql() {
		return "INSERT INTO fertilizers(name, manufacturer, price) VALUES (?,?,?)";
	}
	@Override
	protected String updateSql() {
		return "UPDATE fertilizers SET name=?, manufacturer=?, price=? WHERE id=?";
	}
	@Override
	protected String deleteSql() {
		return "DELETE FROM fertilizers WHERE id =?";
	}
	@Override
	protected String selectAllSql() {
		return "SELECT * FROM fertilizers";	
	}
	@Override
	protected String tableName() {
		return "fertilizers";
	}
	@Override
	protected void parametrizeUpdateStatement(Fertilizer fertilizer) throws SQLException {
		update.setString(1, fertilizer.getName());
		update.setString(2, fertilizer.getManufacturer());
		update.setDouble(3, fertilizer.getPrice());
		update.setInt(4, fertilizer.getId());
	}
	@Override
	protected void parametrizeInsertStatement(Fertilizer fertilizer) throws SQLException {
		insert.setString(1, fertilizer.getName());
		insert.setString(2, fertilizer.getManufacturer());
		insert.setDouble(3, fertilizer.getPrice());
	}
}