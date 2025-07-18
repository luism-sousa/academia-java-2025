package pt.upacademy.jseproject.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import pt.upacademy.jseproject.model.Entity;

/**
 * Prática 2 - Pt. 4<br>
 * 
 * - Subclasse da classe "EntityRepository"<br>
 * - Implementa o Singleton para o repositorio de Produtos<br>
 * - Deve ter um atributo chamada "INSTANCE" (private static  
 * @param <T>
 */
public abstract class ProductRepository<T extends Entity>  {
	private Map<Long, T> database = new HashMap<Long, T>();
	private Long maxId = 0L;
	
	private Long getNextId() {
		// Não colocamos ++ para não aumentar, caso corra mal
		return maxId + 1;	
	}
	
	public Long addEntity(T entity) {
		Long nextId = getNextId();
		entity.setId(nextId);
		database.put(nextId, entity);
		return nextId;
	}
	
	public Collection<T> getAll() {
		// Retornar todos os elementos da "database"
		return database.values();
	}
	
	public T getById(Long id) {
		T entity = database.get(id);
		// Caso a "Entity" não exista na "database", lança uma Exception
		if (entity == null) {
			throw new NoSuchElementException("Entity doesn't exist in DB!");
		}
		// Se chegou aqui, é porque existe => retorno a Entity encontrada
		return entity;
	}
	
	public boolean editEntity(T entity) {
		Long id = entity.getId();
		// Caso o ID recebido seja válido e a "Entity" exista na "database"..
		if (id != null && database.containsKey(id)) {
			database.put(id, entity);
			return true;
		}
		// Caso uma das condições não seja cumprida..
		return false;
	}
	
	public void removeEntity(Long id) {
		// Caso a "Entity" não exista na "database", lançar uma Exception
		if (!database.containsKey(id)) {
			throw new NoSuchElementException("Entity doesn't exist in DB! Can't remove");
		}
		// Se chegou aqui, é porque existe => remover
		database.remove(id);
	}
}
