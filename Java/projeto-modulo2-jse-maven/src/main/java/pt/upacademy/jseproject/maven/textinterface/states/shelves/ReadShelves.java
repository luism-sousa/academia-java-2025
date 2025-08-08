package pt.upacademy.jseproject.maven.textinterface.states.shelves;

import java.util.NoSuchElementException;

import pt.upacademy.jseproject.maven.model.Shelf;
import pt.upacademy.jseproject.maven.services.ShelfService;
import pt.upacademy.jseproject.maven.textinterface.states.State;

public class ReadShelves extends State {
	private ShelfService SS = new ShelfService();
	
	@Override
	public int run() {
		System.out.println("\n== < Prateleira > - [Consultar prateleira] ==\n");

		if (SS.isEmpty()) {
			System.out.println("\nNão existem prateleiras na base de dados!\n");
			return 0;
		}
		
		Long id = sc.getLong("Introduza o ID da prateleira que pretende consultar: ");
		
		try {
			Shelf foundShelf = SS.findById(id);
			System.out.println(foundShelf.toString());
		} catch (NoSuchElementException e) {
			System.out.println("\nPrateleira com ID: " + id + " não encontrada!\n");
		}
		
		return 1;
	}
}
