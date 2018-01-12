package projekt_mpr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import projekt_mpr.dao.uow.JdbcUnitOfWork;
import projekt_mpr.dao.uow.UnitOfWork;

public class JdbcCatalogFactory implements DbCatalogFactory {

	@Override
	public RepositoryCatalog HsqlDbWorkDb() {
		String url;
		String user;
		String  password;
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			UnitOfWork uow = new JdbcUnitOfWork(connection);
			return new JdbcRepositoryCatalog(connection, uow);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
