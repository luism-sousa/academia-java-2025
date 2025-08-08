package pt.upacademy.jseproject.maven.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import pt.upacademy.jseproject.maven.model.Product;
import pt.upacademy.jseproject.maven.model.Shelf;
import pt.upacademy.jseproject.maven.repositories.ShelfRepository;
import pt.upacademy.jseproject.maven.utils.ShelfValidation;

public class ShelfService extends EntityService<Shelf> {
	private final ShelfRepository shelfDB = ShelfRepository.getInstance();

	@Override
	public Shelf create(Shelf shelf) {
		ShelfValidation.validateShelfData(shelf);
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
	public List<Shelf> getAllEntities() {
		return shelfDB.getAllEntities();
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

//	public void deleteProduct(Shelf shelf) {
//		Product product = shelf.getProduct();
//		if (product != null) {
//			// Remover a referencia à Prateleira no Produto
//			product.getShelvesId().remove(shelf.getId());
//			
//			// Remover a referencia do Produto na Prateleira
//			shelf.setProduct(null);
//			
//			// Atualiza a Shelf no repositório
//			shelfDB.editEntity(shelf);
//		}
//	}

	public boolean removeProductFromShelves(Product product) {
		List<Long> shelfIds = new ArrayList<>(product.getShelvesId());
		boolean removed = false;

		for (Long shelfId : shelfIds) {
			Shelf shelf = findById(shelfId);
			if (shelf != null && product.equals(shelf.getProduct())) {
				if (product != null) {
					// Remover a referencia à Prateleira no Produto
					product.getShelvesId().remove(shelf.getId());

					// Remover a referencia do Produto na Prateleira
					shelf.setProduct(null);

					// Atualiza a Shelf no repositório
					shelfDB.updateEntity(shelf);

					removed = true;
				}
			}
		}
		return removed;
	}

	@Override
	public Shelf update(Shelf shelf) {
		ShelfValidation.validateShelfData(shelf);

		// Check if shelf exists
		if (shelf.getId() == null) {
			throw new IllegalArgumentException("[Error] - Invalid Shelf ID! Unable to update!");
		}

		Shelf existingShelf = shelfDB.getById(shelf.getId());
		if (existingShelf == null) {
			throw new NoSuchElementException("[Error] - Shelf not found. Unable to update!");
		}		

		// Update specific fields
		existingShelf.setCapacity(shelf.getCapacity());
		existingShelf.setRentPrice(shelf.getRentPrice());

		if (shelf.getProduct() != null) {
			// Ensure the product exists or is null
			if (shelf.getProduct().getId() != null) {
				existingShelf.setProduct(shelf.getProduct());
			} else {
				existingShelf.setProduct(null);
			}
		}

		// Perform the update
		shelfDB.updateEntity(existingShelf);
		return existingShelf;
	}

	@Override
	public boolean isEmpty() {
		return shelfDB.isEmpty();
	}
}
