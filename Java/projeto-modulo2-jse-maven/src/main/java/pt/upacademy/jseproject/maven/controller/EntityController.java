package pt.upacademy.jseproject.maven.controller;

import java.util.List;

//public abstract class EntityController<T extends Entity, S extends EntityService<R,T>, R extends EntityRepository<T>> {
public abstract class EntityController<T> {
	public abstract List<T> getAll();
	public abstract T create(T entity);
	public abstract T update(T entity);
}
