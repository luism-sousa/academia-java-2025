package pt.upacademy.jseproject.textinterface.states.product;

import java.util.NoSuchElementException;

import pt.upacademy.jseproject.model.Product;
import pt.upacademy.jseproject.repositories.ProductRepository;
import pt.upacademy.jseproject.textinterface.states.State;

public class ReadProducts extends State {
	private ProductRepository DBP = ProductRepository.getInstance();
	
	@Override
	public int run() {
		System.out.println("\n1== < Produto > - [Consultar produto] ==\n");
		if (DBP.isEmpty()) {
			System.out.println("\nNão existem produtos na base de dados!\n");
			return 0;
		} 
		
		Long id = sc.getLong("Introduza o ID do produto que pretende consultar: ");
		
		try {
			Product foundProduct = (Product) DBP.getById(id);
			System.out.println(foundProduct.toString());
		} catch (NoSuchElementException e) {
			System.out.println("\nProduto com ID: " + id + " não encontrado!\n");
		}
		
		return 1;
	}
}