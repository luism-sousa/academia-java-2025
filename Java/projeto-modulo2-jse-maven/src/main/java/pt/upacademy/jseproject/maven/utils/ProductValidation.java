package pt.upacademy.jseproject.maven.utils;

import java.util.NoSuchElementException;

import pt.upacademy.jseproject.maven.model.Product;

public class ProductValidation {

    public static void validateProductData(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("[Error] - Product is null!");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("[Error] - Product name must not be empty!");
        }
        if (product.getPvp() < 0) {
            throw new IllegalArgumentException("[Error] - Product price must not be below zero!");
        }
        if (product.getDiscount() < 0 || product.getDiscount() > 100) {
            throw new IllegalArgumentException("[Error] - Product discount must be between 0 and 100!");
        }
    }

    public static void validateProductExists(Product product, Long productId) {
        if (product == null) {
            throw new NoSuchElementException("[Error] - Product with ID: " + productId + " not found");
        }
    }

    public static void validateProductNameUnique(Product existingProduct, Product newProduct) {
        if (existingProduct != null && !existingProduct.getId().equals(newProduct.getId())) {
            throw new IllegalArgumentException("[Error] - A product with the same name already exists!");
        }
    }
}

