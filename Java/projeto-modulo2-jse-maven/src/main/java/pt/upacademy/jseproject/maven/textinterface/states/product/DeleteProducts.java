package pt.upacademy.jseproject.maven.textinterface.states.product;

import pt.upacademy.jseproject.maven.model.Product;
import pt.upacademy.jseproject.maven.services.ProductService;
import pt.upacademy.jseproject.maven.services.ShelfService;
import pt.upacademy.jseproject.maven.textinterface.states.State;

public class DeleteProducts extends State {
	private ProductService PS = new ProductService();
	private ShelfService SS = new ShelfService();

	@Override
	public int run() {
		System.out.println("== < Produtos > - [Remover produto] ==\n");
		Long id = sc.getLong("Introduza o ID do produto que pretende remover: ");
		
		try {
			Product product = PS.findById(id);
			
			// Verificar se o produto se encontra em alguma prateleira
			boolean productRemovedFromShelves = SS.removeProductFromShelves(product);		
			boolean productRemoved = PS.delete(id);
			
			// Se o Produto foi removida com sucesso..
			if (productRemoved) {
				System.out.println("\nProduto com ID: " + id + " removido com sucesso!\n");
				if (productRemovedFromShelves) {
					System.out.println("\nProduto com ID: " + id + " removido da prateleiras associadas\n");
				}
			} else { // Caso contrário..
				System.out.println("\nErro a remover o Produto");
			}
			
		} catch (Exception e) {
			System.out.println("\nProduto com ID: " + id + " não encontrado! Produto não removido\n");
		}
		
		return 1;
	}
}
