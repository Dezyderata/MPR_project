package projekt_mpr.dao;

import java.sql.*;
import java.util.ArrayList;

import projekt_mpr.dao.mappers.ResultSetMapper;
import projekt_mpr.domain.Accessory;
import projekt_mpr.domain.Flower;
import projekt_mpr.domain.IHaveId;

public abstract class RepositoryBase<TEntity extends IHaveId> implements Repository<TEntity> {

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

	ResultSetMapper<TEntity> mapper;
	
	protected RepositoryBase(Connection connection, ResultSetMapper<TEntity> mapper) throws SQLException {
		this.mapper = mapper;
		this.connection = connection;
		createTable = connection.createStatement();
		insert = connection.prepareStatement(insertSql());
		delete = connection.prepareStatement(deleteSql());
		update = connection.prepareStatement(updateSql());
		selectAll = connection.prepareStatement(selectAllSql());
	}
	public ArrayList<TEntity> getAll(){
		ArrayList<TEntity> result = new ArrayList<TEntity>();
		try {
			ResultSet rs = selectAll.executeQuery();
			while(rs.next()){
				result.add(mapper.map(rs));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public void insert(TEntity entity) {
		try {
			parametrizeInsertStatement(insert, entity);
			insert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void update(TEntity entity) {
		try {
			parametrizeUpdateStatement(update, entity);
			update.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(TEntity entity) {
		try {
			delete.setInt(1, entity.getId());
			delete.executeUpdate();
		} catch (SQLException e) {
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
			e.printStackTrace();
		}
	}
}