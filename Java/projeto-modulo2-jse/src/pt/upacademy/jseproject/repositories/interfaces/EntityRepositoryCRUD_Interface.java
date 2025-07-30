package pt.upacademy.jseproject.repositories.interfaces;

import pt.upacademy.jseproject.model.Entity;
import pt.upacademy.jseproject.utils.interfaces.CRUD_Interface;

public abstract interface EntityRepositoryCRUD_Interface<T extends Entity> extends CRUD_Interface<T>{
	boolean isEmpty();
}
