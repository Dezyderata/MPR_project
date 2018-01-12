	package projekt_mpr.dao;

import java.sql.Connection;
import java.sql.SQLException;

import projekt_mpr.dao.mappers.AccessoryResultMapper;
import projekt_mpr.dao.mappers.CustomerResultMapper;
import projekt_mpr.dao.mappers.FertilizerResultMapper;
import projekt_mpr.dao.mappers.FlowerResultMapper;
import projekt_mpr.dao.uow.UnitOfWork;
import projekt_mpr.domain.Accessory;
import projekt_mpr.domain.Customer;
import projekt_mpr.domain.Fertilizer;
import projekt_mpr.domain.Flower;

public class JdbcRepositoryCatalog implements RepositoryCatalog {
	Connection connection;
	UnitOfWork uow;
	public JdbcRepositoryCatalog(Connection connection, UnitOfWork uow) {
		this.connection = connection;
		this.uow = uow;
	}
	/* (non-Javadoc)
	 * @see projekt_mpr.dao.RepositoryCatalog#accessories()
	 */
	@Override
	public Repository<Accessory> accessories(){
		try {
			return new AccessoryRepository(connection, new AccessoryResultMapper(), uow);
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/* (non-Javadoc)
	 * @see projekt_mpr.dao.RepositoryCatalog#customers()
	 */
	@Override
	public Repository<Customer> customers(){
		try {
			return new CustomerRepository(connection, new CustomerResultMapper(), uow);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/* (non-Javadoc)
	 * @see projekt_mpr.dao.RepositoryCatalog#fertilizers()
	 */
	@Override
	public Repository<Fertilizer> fertilizers(){
		try {
			return new FertilizerRepository(connection, new FertilizerResultMapper(), uow);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/* (non-Javadoc)
	 * @see projekt_mpr.dao.RepositoryCatalog#flowers()
	 */
	@Override
	public Repository<Flower> flowers(){
		try {
			return new FlowerRepository(connection, new FlowerResultMapper(), uow);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/* (non-Javadoc)
	 * @see projekt_mpr.dao.RepositoryCatalog#saveChanges()
	 */
	@Override
	public void saveChanges() {
		uow.saveChanges();
	}
}
