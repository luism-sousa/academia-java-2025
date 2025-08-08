package pt.upacademy.jseproject.maven.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import pt.upacademy.jseproject.maven.model.Entity;

/**
 * Prática 2 - Pt. 3<br>
 * 
 * - Criar classe abstrata "EntityRepository"<br>
 * - Deve ter como parametro generico, uma subclasse da classe Entity<br>
 * - Como atributo deve ter um Map (key será o ID da "Entity", value deve ser a classe T)<br>
 * <br>
 * - Deve possuir um atributo Long, que contem o max. ID presente na DB<br>
 * - Deve possuir um método privado que devolve o próximo ID disponivel<br>
 * <br>
 * Deve ter metodos para permitir:<br>
 * - Criação de entidades (recebe obj. do tipo T, deve retornar um Long com o novo ID)<br>
 * - Consulta de entidades: <br>
 * ==> Um método que não recebe parametros que devolva todos os elementos da BD<br>
 * ==> Um método que recebe o ID e devolve a "Entity"<br>
 * - Edição de entidades (parametro de entrada uma "Entity" que tenha um ID definido)<br>
 * - Remoção de entidades (parametro de entrada um ID)<br>
 * @param <T>
 */
public abstract class EntityRepository<T extends Entity>  {
	protected final Map<Long, T> database = new HashMap<Long, T>();
	private Long maxId = 0L;
	
	private Long getNextId() {
		// Não colocamos ++ para não aumentar, caso corra mal
		return ++maxId;	
	}
	
	public Long addEntity(T entity) {
		Long nextId = getNextId();
		entity.setId(nextId);
		database.put(nextId, entity);
		return nextId;
	}
	
	public List<T> getAllEntities() {
		// Retornar todos os elementos da "database"
		return new ArrayList<>(database.values());
	}
	
	public List<Long> getAllIds() {
		return new ArrayList<>(database.keySet());
	}
	
	public T getById(Long id) {
		T entity = database.get(id);
		// Caso a "Entity" não exista na "database", lança uma Exception
		if (entity == null) {
			throw new NoSuchElementException("Entity não existe na DB!");
		}
		// Se chegou aqui, é porque existe => retorno a Entity encontrada
		return entity;
	}
	
	public void updateEntity(T entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Entity não existe na DB!");
		}
		
		Long id = entity.getId();
		// Caso o ID recebido não seja válido
		if (id == null) {
			throw new IllegalArgumentException("ID da Entity inválido!");
		}
		
		// Caso a "Entity" não exista na "database"..
		if (!database.containsKey(id)) {
			throw new NoSuchElementException("Entity com ID: " + id + " não encontrada!");
		}
		
		// Caso o ID recebido seja válido e a "Entity" exista na "database"..
		database.put(id, entity);
	}
	
	public void removeEntity(Long id) {
		// Caso a "Entity" não exista na "database", lançar uma Exception
		if (!database.containsKey(id)) {
			throw new NoSuchElementException("Entity doesn't exist in DB! Can't remove");
		}
		// Se chegou aqui, é porque existe => remover
		database.remove(id);
	}
	
	public boolean isEmpty() {
		return database.isEmpty();
	}
	
	// Limpar database
	public void clear() {
		database.clear();
		maxId = 0L;
	}
}
