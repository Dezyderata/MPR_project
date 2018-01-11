package projekt_mpr.dao;

import java.sql.*;

import projekt_mpr.domain.Flower;
import projekt_mpr.domain.IHaveId;

public abstract class RepositoryBase<TEntity extends IHaveId> {

	Connection connection;
	protected Statement createTable;
	protected PreparedStatement insert;
	protected PreparedStatement delete;
	protected PreparedStatement update;
	protected PreparedStatement selectAll;
	
	protected abstract String tableName();
	protected abstract String createTableSql();
	protected abstract String insertSql();
	protected abstract String deleteSql();
	protected abstract String updateSql();
	protected abstract String selectAllSql();
	protected abstract void parametrizeUpdateStatement(PreparedStatement statement, TEntity entity) throws SQLException;
	protected abstract void parametrizeInsertStatement(PreparedStatement statement, TEntity entity) throws SQLException;
	
	protected String createTableSql;
	
	public RepositoryBase() {
		try {
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost");
			createTable = connection.createStatement();
			insert = connection.prepareStatement(insertSql());
			delete = connection.prepareStatement(deleteSql());
			update = connection.prepareStatement(updateSql());
			selectAll = connection.prepareStatement(selectAllSql());
		} catch (SQLException e) {
			System.err.println("Something went wrong during connecting datebase!");
			e.printStackTrace();
		}
	}
	public void insert(TEntity entity) {
		try {
			parametrizeInsertStatement(insert, entity);
			insert.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Something went wrong during insert into procedure");
			e.printStackTrace();
		}
	}
	public void update(TEntity entity) {
		try {
			parametrizeUpdateStatement(update, entity);
			update.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Something went wrong during update procedure");
			e.printStackTrace();
		}
	}
	
	public void delete(TEntity entity) {
		try {
			delete.setInt(1, entity.getId());
			delete.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Somtehing went wrong during delete procedure");
			e.printStackTrace();
		}
	}
	public void createTable() {
		try {
			boolean tableExist = false;
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			while(rs.next()) {
				if(rs.getString("TABLE_NAME").equalsIgnoreCase(tableName())) {
					tableExist = true;
					break;
				}
			}
			if(!tableExist) {
				createTable.executeUpdate(createTableSql());
			}
		} catch (SQLException e) {
			System.err.println("Something went wrong during create table procedure");
			e.printStackTrace();
		}
	}
}