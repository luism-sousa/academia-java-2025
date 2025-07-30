package pt.upacademy.jseproject.textinterface.states.shelves;

import java.util.NoSuchElementException;

import pt.upacademy.jseproject.model.Shelf;
import pt.upacademy.jseproject.repositories.ShelfRepository;
import pt.upacademy.jseproject.textinterface.states.State;

public class ReadShelves extends State {
	private ShelfRepository DBS = ShelfRepository.getInstance();

	@Override
	public int run() {
		System.out.println("\n1== < Prateleira > - [Consultar prateleira] ==\n");
		if (DBS.isEmpty()) {
			System.out.println("\nNão existem prateleira na base de dados!\n");
			return 0;
		} 
		
		Long id = sc.getLong("Introduza o ID da prateleira que pretende consultar: ");
		
		try {
			Shelf foundShelf = (Shelf) DBS.getById(id);
			System.out.println(foundShelf.toString());
		} catch (NoSuchElementException e) {
			System.out.println("\nPrateleira com ID: " + id + " não encontrada!\n");
		}
		
		return 1;
	}
}
