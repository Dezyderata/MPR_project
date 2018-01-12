package projekt_mpr.dao;

import java.util.ArrayList;

import projekt_mpr.domain.IHaveId;

public interface Repository<TEntity extends IHaveId> {
	public ArrayList<TEntity> getAll();
	public void insert(TEntity entity);
	public void update(TEntity entity);
	public void delete(TEntity entity);
	public void createTable();
}
