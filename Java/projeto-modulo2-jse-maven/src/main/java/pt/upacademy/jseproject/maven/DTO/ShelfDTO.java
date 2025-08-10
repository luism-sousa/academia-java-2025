package pt.upacademy.jseproject.maven.DTO;

public class ShelfDTO {
	private Long id;
	private int capacity;
	private double rentPrice;
	private Long productId;
	
	//region Getters
	public Long getId() {
		return id;
	}
	public int getCapacity() {
		return capacity;
	}
	public double getRentPrice() {
		return rentPrice;
	}
	public Long getProductId() {
		return productId;
	}
	//endregion
	
	//region Setters
	public void setId(Long id) {
		this.id = id;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void setRentPrice(double rentPrice) {
		this.rentPrice = rentPrice;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	//endregion
}
