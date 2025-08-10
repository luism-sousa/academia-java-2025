package pt.upacademy.jseproject.maven.utils;

import java.util.NoSuchElementException;

import pt.upacademy.jseproject.maven.model.Product;
import pt.upacademy.jseproject.maven.model.Shelf;

public class ShelfValidation {
	public static void validateShelfData(Shelf shelf) {
        // == Input validations ==
        if (shelf == null) {
            throw new IllegalArgumentException("[Error] - Shelf is null!");
        }
        if (shelf.getCapacity() <= 0) {
            throw new IllegalArgumentException("[Error] - Shelf capacity must not be zero or below!");
        }
        if (shelf.getRentPrice() <= 0) {
            throw new IllegalArgumentException("[Error] - Shelf rent price must not be zero or below!");
        }
    }

    public static void validateShelfExists(Shelf shelf, Long shelfId) {
        if (shelf == null) {
            throw new NoSuchElementException("[Error] - Shelf with ID: " + shelfId + " not found");
        }
    }

    public static void validateShelfIsAvailable(Shelf shelf) {
        if (shelf == null) { 
            throw new IllegalStateException("[Error] - Shelf is null (cannot check availability)");
        }
        if (shelf.getProduct() != null) {
            throw new IllegalStateException("[Error] - Shelf with ID: " + shelf.getId() + " is occupied");
        }
    }

    public static void validateProductToAssociate(Product product, Long productId) {
    	if (product == null) {
    		throw new NoSuchElementException("[Error] - Product with ID: " + productId + " not found");
    	}           
    }

}
