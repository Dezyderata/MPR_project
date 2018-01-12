package projekt_mpr.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import projekt_mpr.domain.Fertilizer;

public class FertilizerResultMapper implements ResultSetMapper<Fertilizer> {

	@Override
	public Fertilizer map(ResultSet rs) throws SQLException {
		Fertilizer f = new Fertilizer();
		f.setId(rs.getInt("id"));
		f.setName(rs.getString("name"));
		f.setManufacturer(rs.getString("manufacturer"));
		f.setPrice(rs.getFloat("price"));
		return f;
	}

}
