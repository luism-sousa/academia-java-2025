package pt.upacademy.jseproject.maven.services;

import java.util.List;

public abstract class EntityService<T> {
	public abstract T create(T entity);
	public abstract T findById(Long id);
	public abstract List<Long> findAll();
	public abstract List<T> getAllEntities();
	public abstract T update(T entity);
	public abstract boolean delete(Long id);
	public abstract boolean isEmpty();
}
