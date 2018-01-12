package projekt_mpr.dao;

import projekt_mpr.domain.Accessory;
import projekt_mpr.domain.Customer;
import projekt_mpr.domain.Fertilizer;
import projekt_mpr.domain.Flower;

public interface RepositoryCatalog {

	Repository<Accessory> accessories();

	Repository<Customer> customers();

	Repository<Fertilizer> fertilizers();

	Repository<Flower> flowers();

	void saveChanges();

}