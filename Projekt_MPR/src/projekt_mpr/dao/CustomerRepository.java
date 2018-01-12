package projekt_mpr.dao;

import java.sql.*;
import java.util.ArrayList;

import projekt_mpr.dao.mappers.ResultSetMapper;
import projekt_mpr.dao.uow.UnitOfWork;
import projekt_mpr.domain.Customer;


public class CustomerRepository extends RepositoryBase<Customer>{

	public CustomerRepository(Connection connection, ResultSetMapper<Customer> mapper, UnitOfWork uow) throws SQLException {
			super(connection, mapper, uow);
	}
	@Override
	protected String createTableSql() {
		return"CREATE TABLE customers("
			+"id INT PRIMARY KEY AUTO_INCREMENT, "
			+"name VARCHAR(20), "
			+"surname VARCHAR(30), "
			+"address VARCHAR(50)" 
			+")";
	}
	@Override
	protected String insertSql() {
		return "INSERT INTO customers(name, surname, address) VALUES(?,?,?)";
	}
	@Override
	protected String updateSql() {
		return "UPDATE customers SET name=?, surname=?, address=? WHERE id=?";
	}
	@Override
	protected String deleteSql() {
		return "DELETE FROM customers WHERE id=?";
	}
	@Override
	protected String selectAllSql() {
		return "SELECT * FROM customers";
	}
	@Override
	protected String tableName() {
		return "customers";
	}
	@Override
	protected void parametrizeUpdateStatement(Customer customer) throws SQLException {
		update.setString(1, customer.getName());
		update.setString(2, customer.getSurname());
		update.setString(3, customer.getAddress());
		update.setInt(4, customer.getId());
	}
	@Override
	protected void parametrizeInsertStatement(Customer customer) throws SQLException {
		insert.setString(1, customer.getName());
		insert.setString(2, customer.getSurname());
		insert.setString(3, customer.getAddress());
	}
}