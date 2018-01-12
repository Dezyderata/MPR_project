package projekt_mpr.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import projekt_mpr.domain.Accessory;

public class AccessoryResultMapper implements ResultSetMapper<Accessory> {

	@Override
	public Accessory map(ResultSet rs) throws SQLException {
		Accessory p = new Accessory();
		p.setId(rs.getInt("id"));
		p.setName(rs.getString("name"));
		p.setPrice(rs.getFloat("price"));
		return p;
	}

}
