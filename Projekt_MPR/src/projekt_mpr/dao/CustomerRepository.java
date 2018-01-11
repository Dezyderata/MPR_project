package projekt_mpr.dao;

import java.sql.*;
import java.util.ArrayList;

import projekt_mpr.domain.Customer;


public class CustomerRepository extends RepositoryBase<Customer>{

	public CustomerRepository() {
			super();
	}
	@Override
	public String createTableSql() {
		return"CREATE TABLE customers("
			+"id INT GENERATED BY DEFAULT AS IDENTITY, "
			+"name VARCHAR(20), "
			+"surname VARCHAR(30), "
			+"address VARCHAR(50)" 
			+")";
	}
	@Override
	public String insertSql() {
		return "INSERT INTO customers(name, surname, address) VALUES(?,?,?)";
	}
	@Override
	public String updateSql() {
		return "UPDATE customers SET (name, surname, address)=(?,?,?) WHERE id=?";
	}
	@Override
	public String deleteSql() {
		return "DELETE FROM customers WHERE id=?";
	}
	@Override
	public String selectAllSql() {
		return "SELECT * FROM customers";
	}
	@Override
	public String tableName() {
		return "customers";
	}
	
	
	public ArrayList<Customer> getAll(){
		ArrayList<Customer> result = new ArrayList<Customer>();
		try {
			ResultSet rs = selectAll.executeQuery();
			while(rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setSurname(rs.getString("surname"));
				c.setAddress(rs.getString("address"));
				result.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	@Override
	protected void parametrizeUpdateStatement(PreparedStatement statement, Customer customer) throws SQLException {
		statement.setString(1, customer.getName());
		statement.setString(2, customer.getSurname());
		statement.setString(3, customer.getAddress());
		statement.setInt(4, customer.getId());
		
	}
	@Override
	protected void parametrizeInsertStatement(PreparedStatement statement, Customer customer) throws SQLException {
		statement.setString(1, customer.getName());
		statement.setString(2, customer.getSurname());
		statement.setString(3, customer.getAddress());
		
	}