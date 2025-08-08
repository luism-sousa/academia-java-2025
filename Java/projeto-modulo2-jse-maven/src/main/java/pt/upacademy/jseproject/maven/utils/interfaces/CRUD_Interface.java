package pt.upacademy.jseproject.maven.utils.interfaces;

import java.util.List;

import pt.upacademy.jseproject.maven.model.Entity;

public abstract interface CRUD_Interface<T extends Entity> {
	// Métodos usados por N classes implementados (obrigatóriamente) pelas classes que usam o 'implements'
	// Métodos do tipo T (generic) são implementados por todas as classes que usam o 'implements' desta interface
	Long addEntity(T entity);	
	T getEntity(Long id);	
	List<T> getAllEntities();	
	void updateEntity(T entity);	
	void removeEntity(Long id);
}
