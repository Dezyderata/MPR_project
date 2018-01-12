package projekt_mpr.dao.uow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUnitOfWork implements UnitOfWork {
	private Connection connection;
	private List<Entity> entities = new ArrayList<Entity>();
	public JdbcUnitOfWork(Connection connection) throws SQLException{
		this.connection =connection;
		this.connection.setAutoCommit(false);
	}
	@Override
	public void markAsNew(Entity entity) {
		entity.setState(EntityState.New);
		entities.add(entity);
	}

	@Override
	public void markAsDeleted(Entity entity) {
		entity.setState(EntityState.Deleted);
		entities.add(entity);
	}

	@Override
	public void markAsChanged(Entity entity) {
		entity.setState(EntityState.Changed);
		entities.add(entity);
	}

	@Override
	public void saveChanges() {
		for(Entity entity : entities)
			entity.persistChange();
		try {
			connection.commit();
			entities.clear();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void rollback() {
		entities.clear();
		
	}


}
