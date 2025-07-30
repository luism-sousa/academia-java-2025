package pt.upacademy.jseproject.maven.textinterface.states.product;

import pt.upacademy.jseproject.maven.services.ProductService;
import pt.upacademy.jseproject.maven.textinterface.states.State;

public class DeleteProducts extends State {
	private ProductService PS = new ProductService();

	@Override
	public int run() {
		System.out.println("== < Produtos > - [Remover produto] ==\n");
		Long id = sc.getLong("Introduza o ID do produto que pretende remover: ");
		
		boolean productRemoved = PS.delete(id);
		
		// Se o Produto foi removida com sucesso..
		if (productRemoved) {
			System.out.println("\nProduto com ID: " + id + " removido com sucesso!\n");
		} else { // Caso contrário..
			System.out.println("\nProduto com ID: " + id + " não encontrado! Produto não removido\n");
		}
		
		return 1;
	}

}
