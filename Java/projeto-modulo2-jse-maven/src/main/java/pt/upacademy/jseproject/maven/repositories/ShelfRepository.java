package pt.upacademy.jseproject.maven.repositories;

import pt.upacademy.jseproject.maven.model.Shelf;
import pt.upacademy.jseproject.maven.repositories.interfaces.ShelfRepositoryCRUD_Interface;

/**
 * Prática 2 - Pt. 5<br>
 * 
 * - Subclasse da classe "EntityRepository"<br>
 * - Implementa o Singleton para o repositorio de Prateleiras
 * (ShelfRepository)<br>
 * - Deve ter um atributo chamado "INSTANCE" (private static final) pertencente
 * a esta classe<br>
 * - Deve ter um metodo (getInstance) que devolve um ShelfRepository e retorna a
 * variável 'INSTANCE'<br>
 * - Deve ter um construtor private e sem parametros<br>
 */
public class ShelfRepository extends EntityRepository<Shelf> implements ShelfRepositoryCRUD_Interface {
	private static final ShelfRepository INSTANCE = new ShelfRepository();

	private ShelfRepository() {
		super();
	}

	public static ShelfRepository getInstance() {
		return INSTANCE;
	}

	@Override
	public Shelf getEntity(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
