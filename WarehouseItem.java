public class WarehouseItem {
	private String key;
	private String brand;
	private String model;
	private int weightInKg;
	private double price;
	public WarehouseItem(
		String key,
		String brand,
		String model,
		int weightInKg,
		double price
	) {
		this.setKey(key);
		this.setBrand(brand);
		this.setModel(model);
		this.setWeightInKg(weightInKg);
		this.setPrice(price);
	}
	public String getKey() {
		return key;
	}
	public String getBrand() {
		return brand;
	}
	public String getModel() {
		return model;
	}
	public int getWeightInKg() {
		return weightInKg;
	}
	public double getPrice() {
		return price;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setWeightInKg(int weightInKg) {
		this.weightInKg = weightInKg;
	}
	public void setPrice(double price) {
		// Round to two decimal places.
		this.price = Math.round(price * 100) / 100;
	}
	public String toString() {
		return toString(false);
	}
	public String toString(boolean verbose) {
		if (verbose)
			return
				"Key:    " + key + "\n" +
				"Brand:  " + brand + "\n" +
				"Model:  " + model + "\n" +
				"Weight: " + weightInKg + " kg\n" +
				"Price   $" + String.format("%.2f", price);
		else
			return key;
	}
	public static void main(String[] args) {
		WarehouseItem w = new WarehouseItem(
			"Couch:6", "Fremarc", "Deluxe", 101, 1871.7
		);
		System.out.println(w);
	}
}
