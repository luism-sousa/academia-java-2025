package pt.upacademy.jseproject.maven.model;

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
	
	private int capacity;
	private double rentPrice;
	private Product product;	// Agregação

	// Construtor
	public Shelf(int capacity, double rentPrice) {
		this.id = ++count;	// ++count para o 1º elemento não começar com ID '0'
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

	public void setProduct(Product product) {
		if (this.product == null) {
			this.product = product;
		} else {
			System.out.println("Erro! Esta prateleira já possui um produto");
		}
	}
	// endregion
	
	// TODO Fazer override do método 'ToString'
	@Override
	public String toString() {
//		 System.out.println(" - ID: " + id + " | Conteúdo: " +
//	                (shelf.getProduct() != null ? "Produto #" + shelf.getProduct().getId() : "vazia") +
//	                ("| Preço aluguer: " + shelf.getRentPrice()));
		return "Prateleira ID: " + id + " | " +
				(product != null ? "Produto ID: " + product.getId() + " | Nome Produto: " + product.getName() + " " : "Sem produtos ") +
				"| Capacidade: " + capacity + " " +
				"| Preço de Aluguer: " + rentPrice + "€ / dia";
	}
}