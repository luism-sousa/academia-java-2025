package pt.upacademy.jseproject.maven.services;

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

	public boolean removeProductFromShelf(Product product, Long shelfIdToRemove) {
	    if (product == null || shelfIdToRemove == null) {
	        return false;
	    }

	    List<Long> shelfIds = product.getShelvesId();
	    if (shelfIds == null || !shelfIds.contains(shelfIdToRemove)) {
	        return false; // Product not linked to this shelf
	    }

	    Shelf shelf = findById(shelfIdToRemove);
	    if (shelf != null && shelf.getProduct() != null
	            && product.getId() != null
	            && product.getId().equals(shelf.getProduct().getId())) {

	        // Remove the shelf reference from the product
	        shelfIds.remove(shelfIdToRemove);
	        product.setShelvesId(shelfIds);

	        // Remove the product reference from the shelf
	        shelf.setProduct(null);

	        // Update the shelf in the repository
	        shelfDB.updateEntity(shelf);

	        return true;
	    }

	    return false;
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
