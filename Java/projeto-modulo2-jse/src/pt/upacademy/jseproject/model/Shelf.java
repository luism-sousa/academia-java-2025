package pt.upacademy.jseproject.model;

/*
 * Uma prateleira pode ter 0 ou 1 produto
 * Um produto pode estar presente em 0 ou “n” prateleiras
 */
/* Enunciado da classe:
   Esta classe deve possuir:
	- Um código (ID)
	- Capacidade
 	- Produto que alberga
 	- Preço de aluguer de localização (diário)
 */

public class Shelf extends Entity {
	private static long count = 0;
	
	private Long id;
	private int capacity;
	private double rentPrice;
	private Product product;	// Agregação

	// Construtor
	public Shelf(int capacity, double rentPrice) {
		this.id = count++;
		this.capacity = capacity;
		this.rentPrice = rentPrice;
	}
	
	// region Getters
	public Long getId() {
		return id;
	}

	public int getCapacity() {
		return capacity;
	}

	public double getRentPrice() {
		return rentPrice;
	}

	public Product getProduct() {
		return product;
	}
	// endregion

	// region Setters
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setRentPrice(double rentPrice) {
		this.rentPrice = rentPrice;
	}

	public void setProducts(Product product) {
		this.product = product;
	}
	// endregion

	public void showMenu() {
		System.out.println("Aqui shelf");		
	}
}