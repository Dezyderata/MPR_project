	package projekt_mpr.dao;

import java.sql.Connection;
import java.sql.SQLException;

import projekt_mpr.dao.mappers.AccessoryResultMapper;
import projekt_mpr.dao.mappers.CustomerResultMapper;
import projekt_mpr.dao.mappers.FertilizerResultMapper;
import projekt_mpr.dao.mappers.FlowerResultMapper;
import projekt_mpr.domain.Accessory;
import projekt_mpr.domain.Customer;
import projekt_mpr.domain.Fertilizer;
import projekt_mpr.domain.Flower;

public class RepositoryCatalog {
	Connection connection;
	public RepositoryCatalog(Connection connection) {
		this.connection = connection;
	}
	public Repository<Accessory> accessories(){
		try {
			return new AccessoryRepository(connection, new AccessoryResultMapper());
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public Repository<Customer> customers(){
		try {
			return new CustomerRepository(connection, new CustomerResultMapper());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public Repository<Fertilizer> fertilizers(){
		try {
			return new FertilizerRepository(connection, new FertilizerResultMapper());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public Repository<Flower> flowers(){
		try {
			return new FlowerRepository(connection, new FlowerResultMapper());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
