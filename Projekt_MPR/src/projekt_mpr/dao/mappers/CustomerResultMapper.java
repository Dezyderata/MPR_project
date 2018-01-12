package projekt_mpr.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import projekt_mpr.domain.Customer;

public class CustomerResultMapper implements ResultSetMapper<Customer> {

	@Override
	public Customer map(ResultSet rs) throws SQLException {
		Customer c = new Customer();
		c.setId(rs.getInt("id"));
		c.setName(rs.getString("name"));
		c.setSurname(rs.getString("surname"));
		c.setAddress(rs.getString("address"));
		return c;
	}


}
