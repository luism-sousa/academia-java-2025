package pt.upacademy.jseproject.maven.services;

import java.util.List;
import java.util.NoSuchElementException;

import pt.upacademy.jseproject.maven.model.Shelf;
import pt.upacademy.jseproject.maven.repositories.ShelfRepository;

public class ShelfService extends EntityService<Shelf> {
	private final ShelfRepository shelfDB = ShelfRepository.getInstance();

	@Override
	public Shelf create(Shelf shelf) {
		shelfDB.addEntity(shelf);
		return shelf;
	}
	
	public Shelf findById(Long id) {
		return shelfDB.getById(id);
	}
	
	@Override
	public List<Long> findAll() {
		return shelfDB.getAllIds();
	}

	@Override
	public boolean delete(Long id) {
		try {
			shelfDB.removeEntity(id);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	@Override
	public boolean isEmpty() {
		return shelfDB.isEmpty();
	}
}
