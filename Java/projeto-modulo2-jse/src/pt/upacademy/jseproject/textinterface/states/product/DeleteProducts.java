package pt.upacademy.jseproject.textinterface.states.product;

import java.util.NoSuchElementException;

import pt.upacademy.jseproject.repositories.ProductRepository;
import pt.upacademy.jseproject.textinterface.states.State;

public class DeleteProducts extends State {
	private ProductRepository DBP = ProductRepository.getInstance();

	@Override
	public int run() {
		System.out.println("== < Produto > - [Remover produto] ==\n");
		Long id = sc.getLong("Introduza o ID da produto que pretende remover: ");
		
		try {
			DBP.removeEntity(id);
			System.out.println("\nProduto com ID: " + id + " removido com sucesso!\n");
		} catch (NoSuchElementException e) {
			System.out.println("\nProduto com ID: " + id + " não encontrado! Produto não removido\n");
		}
		return 1;
	}

}
