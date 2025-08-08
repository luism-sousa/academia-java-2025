package pt.upacademy.jseproject.maven.textinterface.states.shelves;

import java.util.List;

import pt.upacademy.jseproject.maven.model.Shelf;
import pt.upacademy.jseproject.maven.services.ShelfService;
import pt.upacademy.jseproject.maven.textinterface.states.State;

public class ListShelves extends State {
	private ShelfService SS = new ShelfService();
	
	@Override
	public int run() {
		System.out.println("\n== < Prateleira > - [Listar todas as prateleiras] ==\n");
		
		List<Shelf> allShelves = SS.getAllEntities();

		if (allShelves.isEmpty()) {
			System.out.println("Não existem prateleiras na base de dados!\n");
		} else {
			for (Shelf shelf : allShelves) {
				System.out.println(shelf.toString());
			}
		}
		return 1;
	}
}
