package pt.upacademy.jseproject.maven.textinterface.states.shelves;

import pt.upacademy.jseproject.maven.services.ShelfService;
import pt.upacademy.jseproject.maven.textinterface.states.State;

public class DeleteShelves extends State {
	private ShelfService SS = new ShelfService();

	@Override
	public int run() {
		System.out.println("== < Prateleira > - [Remover prateleira] ==\n");
		Long id = sc.getLong("Introduza o ID da prateleira que pretende remover: ");
		
		boolean shelfRemoved = SS.delete(id);
		
		// Se a Shelf foi removida com sucesso..
		if (shelfRemoved) {
			System.out.println("\nPrateleira com ID: " + id + " removida com sucesso!\n");
		} else { // Caso contrário..
			System.out.println("\nPrateleira com ID: " + id + " não encontrada! Prateleira não removida\n");
		}
		
		return 1;
	}
}
