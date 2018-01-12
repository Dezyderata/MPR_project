package projekt_mpr.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import projekt_mpr.domain.Flower;

public class FlowerResultMapper implements ResultSetMapper<Flower> {

	@Override
	public Flower map(ResultSet rs) throws SQLException {
		Flower f = new Flower();
		f.setId(rs.getInt("id'"));
		f.setName(rs.getString("name"));
		f.setLatinName(rs.getString("latinName"));
		f.setPrice(rs.getFloat("price"));
		return f;
	}

}
