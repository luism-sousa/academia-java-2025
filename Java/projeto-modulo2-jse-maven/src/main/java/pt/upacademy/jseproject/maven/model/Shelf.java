package pt.upacademy.jseproject.maven.model;

import java.util.Locale;

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
	private int capacity;
	private double rentPrice;
	private Product product;	// Agregação
	
	// Construtor por defeito
	public Shelf() {
		
	}

	// Construtor
	public Shelf(int capacity, double rentPrice) {
		this.capacity = capacity;
		this.rentPrice = rentPrice;
	}
	
	// region Getters
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

	public boolean setProduct(Product product) {
		if (product == null) {
			// Remover produto
			this.product = null;
			return true;
		} else if (this.product == null) {
			// Adicionar produto
			this.product = product;
			return true;
		} else {
			System.out.println("Erro! Esta prateleira já possui um produto");
			return false;
		}
	}
	// endregion
	
	@Override
	public String toString() {
		return "Prateleira ID: " + id + " | " +
				(product != null ? "Produto ID: " + product.getId() + " | Nome Produto: " + product.getName() + " " : "Sem produtos ") +
				"| Capacidade: " + capacity + " " +
				"| Preço de Aluguer: " + String.format(Locale.US, "%.2f", rentPrice) + "€ / dia";
	}
}