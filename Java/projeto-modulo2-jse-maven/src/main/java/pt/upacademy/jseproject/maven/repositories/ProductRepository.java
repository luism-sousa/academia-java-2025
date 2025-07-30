package pt.upacademy.jseproject.maven.repositories;

import java.util.List;
import java.util.Map;

import pt.upacademy.jseproject.maven.model.Product;
import pt.upacademy.jseproject.maven.repositories.interfaces.ProductRepositoryCRUD_Interface;

/**
 * Prática 2 - Pt. 4<br>
 * 
 * - Subclasse da classe "EntityRepository"<br>
 * - Implementa o Singleton para o repositorio de Produtos (ProductRepository)<br>
 * - Deve ter um atributo chamado "INSTANCE" (private static final) pertencente a esta classe
 * - Deve ter um metodo (getInstance) que devolve um ProductRepository e retorna a variável 'INSTANCE'  
 * - Deve ter um construtor private e sem parametros
 */
public class ProductRepository extends EntityRepository<Product> implements ProductRepositoryCRUD_Interface {
	private static final ProductRepository INSTANCE = new ProductRepository();
	
	private ProductRepository() {
		super();
	}
	
	public static ProductRepository getInstance() {
		return INSTANCE;
	}

	@Override
	public Product getEntity(Long id) {
		return getById(id);
	}


	@Override
	public List<Long> getShelfIdsByProductId(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Product findByName(String name) {
		for (Product product : database.values()) {
			if (product.getName().equalsIgnoreCase(name.trim())) {
				return product;
			}
		}
		return null;
	}
//	
//	public boolean removeByName(String name) {
//		for (Map.Entry<Long, Product> product : database.entrySet()) {
//			if (product.getValue().getName().equalsIgnoreCase(name.trim())) {
//				database.remove(product.getKey());
//				return true;
//			}
//		}
//		return false;
//	}
}
