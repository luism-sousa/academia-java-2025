package pt.upacademy.jseproject.maven.textinterface.states.product;

import java.util.List;

import pt.upacademy.jseproject.maven.model.Product;
import pt.upacademy.jseproject.maven.services.ProductService;
import pt.upacademy.jseproject.maven.textinterface.states.State;

public class ListProducts extends State {
	private ProductService PS = new ProductService();
	
	@Override
	public int run() {
		System.out.println("\n== < Produto > - [Listar todos os produtos] ==\n");
		
		List<Product> allProducts = PS.getAllEntities();

		if (allProducts.isEmpty()) {
			System.out.println("Não existem produtos na base de dados!\n");
		} else {
			for (Product product : allProducts) {
				System.out.println(product.toString());
			}
		}
		return 1;
	}
}
