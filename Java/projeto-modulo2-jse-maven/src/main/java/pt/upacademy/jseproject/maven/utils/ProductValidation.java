package pt.upacademy.jseproject.maven.utils;

import java.util.NoSuchElementException;
import java.util.Objects;

import pt.upacademy.jseproject.maven.model.Product;
import pt.upacademy.jseproject.maven.services.ProductService;

public class ProductValidation {
	
	public static void validateProductData(Product product, ProductService PS) {
		validateProductData(product, PS, null);
	}

    public static void validateProductData(Product product, ProductService PS, Long currentProductId) {
        if (product == null) {
            throw new IllegalArgumentException("[Error] - Product is null!");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("[Error] - Product name must not be empty!");
        }
        if (product.getVat() == null) {
            throw new IllegalArgumentException("[Error] - Product VAT must not be null!");
        }
        if (Double.isNaN(product.getPvp()) || product.getPvp() <= 0) {
            throw new IllegalArgumentException("[Error] - Product price must not be below or equal to zero!");
        }
        if (product.getDiscount() < 0 || product.getDiscount() > 100) {
            throw new IllegalArgumentException("[Error] - Product discount must be between 0% and 100%!");
        }

        Product existingProduct = PS.findByName(product.getName());
        if (existingProduct != null) {
            // If currentProductId is null => creating new product => any existing product with same name is conflict.
            // If currentProductId != null => updating => allow if it's the same product (ids equal).
            if (currentProductId == null || !Objects.equals(existingProduct.getId(), currentProductId)) {
                throw new IllegalArgumentException("[Error] - A Product with the name '" + product.getName() + "' already exists!");
            }
        }
    }

    public static void validateProductExists(Product product, Long productId) {
        if (product == null) {
            throw new NoSuchElementException("[Error] - Product with ID: " + productId + " not found");
        }
    }

    public static void validateProductNameUnique(Product existingProduct, Product newProduct) {
        if (existingProduct != null && newProduct != null) { 
            if (existingProduct.getId() == null || newProduct.getId() == null ||
                !existingProduct.getId().equals(newProduct.getId())) {
                throw new IllegalArgumentException("[Error] - A product with the same name already exists!");
            }
        }
    }
}

