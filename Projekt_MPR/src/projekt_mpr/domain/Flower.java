package projekt_mpr.domain;

public class Flower {
	private int id;
	private String name;
	private String latinName;
	private float price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLatinName() {
		return latinName;
	}
	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
