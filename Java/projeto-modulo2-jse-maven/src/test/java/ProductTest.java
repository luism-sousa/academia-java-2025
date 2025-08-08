import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pt.upacademy.jseproject.maven.model.Product;
import pt.upacademy.jseproject.maven.repositories.ProductRepository;
import pt.upacademy.jseproject.maven.services.ProductService;
import pt.upacademy.jseproject.maven.utils.VatRate;

public class ProductTest {
	ProductService PS;
	
	@BeforeEach
	void constructorSetup() {
		PS = new ProductService();
		ProductRepository.getInstance().clear();	// Limpar repository
	}
	
	@Test
	@DisplayName("[Product] - Create Product")
	void testCreateProduct() {
		Product newProduct = new Product("Gelado", 10.0, VatRate.VAT_23, 1.00);
		PS.create(newProduct);
		assertNotNull(newProduct.getId());
		assertEquals("Gelado", newProduct.getName());		
	}
	
	@Test
	@DisplayName("[Product] - Find Product")
	void testFindProduct() {
		Product newProduct = new Product("Gelado", 10.0, VatRate.VAT_23, 1.00);
		PS.create(newProduct);
		
		Product foundProduct = PS.findByName(newProduct.getName());
		assertEquals("Gelado", foundProduct.getName());
	}
	
	@Test
	@DisplayName("[Product] - Update Product")
	void testUpdateProduct() {
		Product newProduct = PS.create(new Product("Gelado", 10.0, VatRate.VAT_23, 1.00));
		newProduct.setPvp(1.50);
		PS.update(newProduct);
		
		Product foundProduct = PS.findByName(newProduct.getName());
		assertEquals(1.50, foundProduct.getPvp());
	}
	
	@Test
	@DisplayName("[Product] - Delete Product")
	void testDeleteProduct() {
		Product newProduct = new Product("Gelado", 10.0, VatRate.VAT_23, 1.00);
		PS.create(newProduct);
		
		boolean isDeleted = PS.delete(newProduct.getId());
		assertTrue(isDeleted);
		assertThrows(NoSuchElementException.class, () -> PS.findById(newProduct.getId()));
	}
	
	@Test
	@DisplayName("[Product] - List All Products")
	void testListAllProducts() {
		PS.create(new Product("Gelado", 10.0, VatRate.VAT_23, 1.00));
		PS.create(new Product("Pão", 0.0, VatRate.VAT_6, 0.75));
		
		List<Product> listProducts = PS.getAllEntities();
		assertEquals(2, listProducts.size());
	}
	
	/*
	* ## Fail Tests ##
	*/
	@Test 
	@DisplayName("[Product (Fail)] - Find Product that doesn't exist")
	void testFindNonExistentProduct() {
		Product foundProduct = PS.findByName("Chocolate");
		assertNull(foundProduct);
	}
	
	@Test
	@DisplayName("[Product (Fail)] - Delete Product that doesn't exist")
	void testDeleteNonExistentProduct() {
		boolean isDeleted = PS.delete(10L);
		assertFalse(isDeleted);
	}
}
