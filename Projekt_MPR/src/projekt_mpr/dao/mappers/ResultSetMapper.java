package projekt_mpr.dao.mappers;

import java.sql.*;

public interface ResultSetMapper <TEntity>{
	public TEntity map(ResultSet rs) throws SQLException; 
}
