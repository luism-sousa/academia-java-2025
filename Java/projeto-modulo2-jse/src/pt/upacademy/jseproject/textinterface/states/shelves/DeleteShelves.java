package pt.upacademy.jseproject.textinterface.states.shelves;

import java.util.NoSuchElementException;

import pt.upacademy.jseproject.repositories.ShelfRepository;
import pt.upacademy.jseproject.textinterface.states.State;

public class DeleteShelves extends State {
	private ShelfRepository DBS = ShelfRepository.getInstance();

	@Override
	public int run() {
		System.out.println("== < Prateleira > - [Remover prateleira] ==\n");
		Long id = sc.getLong("Introduza o ID da prateleira que pretende remover: ");
		
		try {
			DBS.removeEntity(id);
			System.out.println("\nPrateleira com ID: " + id + " removida com sucesso!\n");
		} catch (NoSuchElementException e) {
			System.out.println("\nPrateleira com ID: " + id + " não encontrada! Prateleira não removida\n");
		}
		return 1;
	}
}
