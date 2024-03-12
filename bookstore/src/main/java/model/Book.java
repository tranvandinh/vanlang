package model;

public class Book implements java.io.Serializable {
	private String id,name;
	private double price;
	
	public Book() {
		super();
		id="";
		name="";
		price=0.0;
	}

	public Book(String id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	

}
