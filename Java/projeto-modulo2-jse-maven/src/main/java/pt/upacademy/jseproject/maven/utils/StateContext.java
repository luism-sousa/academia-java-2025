package pt.upacademy.jseproject.maven.utils;

import pt.upacademy.jseproject.maven.model.Product;
import pt.upacademy.jseproject.maven.model.Shelf;

public class StateContext {
	private Shelf associatedShelves;
	private String productName;
	private Product createdProduct;
	
	public enum ShelfStatus {
		CREATED,		// Oriunda do "CreateShelves"
		SELECTED		// Oriunda do "MenuShelves"
	}
	private ShelfStatus shelfStatus;
	
	// Getters
	public Shelf getAssociatedShelves() {
		return associatedShelves;
	}
	public String getProductName() {
		return productName;
	}
	public Product getCreatedProduct() {
		return createdProduct;
	}
	public ShelfStatus getShelfStatus() {
		return shelfStatus;
	}
	
	// Setters
	public void setAssociatedShelves(Shelf associatedShelves) {
		this.associatedShelves = associatedShelves;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setCreatedProduct(Product createdProduct) {
		this.createdProduct = createdProduct;
	}
	public void setShelfStatus(ShelfStatus shelfStatus) {
		this.shelfStatus = shelfStatus;
	}
}
