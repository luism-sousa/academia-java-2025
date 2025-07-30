package pt.upacademy.jseproject.maven.textinterface.states.product;

import java.util.NoSuchElementException;

import pt.upacademy.jseproject.maven.model.Product;
import pt.upacademy.jseproject.maven.repositories.ProductRepository;
import pt.upacademy.jseproject.maven.services.ProductService;
import pt.upacademy.jseproject.maven.textinterface.states.State;

public class ReadProducts extends State {
	private ProductService PS = new ProductService();
	
	@Override
	public int run() {
		System.out.println("\n== < Produto > - [Consultar produto] ==\n");
		if (PS.isEmpty()) {
			System.out.println("\nNão existem produtos na base de dados!\n");
			return 0;
		} 
		
		Long id = sc.getLong("Introduza o ID do produto que pretende consultar: ");
		
		try {
			Product foundProduct = (Product) PS.findById(id);
			System.out.println(foundProduct.toString());
		} catch (NoSuchElementException e) {
			System.out.println("\nProduto com ID: " + id + " não encontrado!\n");
		}
		
		return 1;
	}
}