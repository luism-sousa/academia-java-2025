package pt.upacademy.jseproject.maven.services;

import java.util.List;
import java.util.NoSuchElementException;

import pt.upacademy.jseproject.maven.model.Product;
import pt.upacademy.jseproject.maven.repositories.ProductRepository;

public class ProductService extends EntityService<Product> {

	private final ProductRepository productDB = ProductRepository.getInstance();
	
	@Override
	public Product create(Product product) {
//		ProductValidation.validateProductData(product);	
		
		productDB.addEntity(product);
		return product;
	}
	
	public Product findByName(String name) {
		return productDB.findByName(name);
	}
	
	@Override
	public Product findById(Long id) {
		return productDB.getById(id);
	}

	@Override
	public List<Long> findAll() {
		return productDB.getAllIds();
	}
	
	@Override
	public List<Product> getAllEntities() {
		return productDB.getAllEntities();
	}
	
	@Override
	public Product update(Product product) {
//		ProductValidation.validateProductData(product, PS);
//		
//		if (product.getId() == null) {
//			throw new IllegalArgumentException("[Error] - Invalid Product ID! Unable to update!");
//		}
//		
//		Product existingProduct = productDB.getById(product.getId());
//		ProductValidation.validateProductExists(existingProduct, product.getId());
//		
//		Product testProduct = productDB.findByName(product.getName());
//		ProductValidation.validateProductNameUnique(existingProduct, testProduct);
		
		productDB.updateEntity(product);
		return product;
	}
	
	@Override
	public boolean delete(Long id) {
		try {
			productDB.removeEntity(id);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	@Override
	public boolean isEmpty() {
		return productDB.isEmpty();
	}
}
