package pt.upacademy.jseproject.repositories.interfaces;

import java.util.List;

import pt.upacademy.jseproject.model.Product;

public interface ProductRepositoryCRUD_Interface extends EntityRepositoryCRUD_Interface<Product>{
	List<Long> getShelfIdsByProductId(Long productId);
}
