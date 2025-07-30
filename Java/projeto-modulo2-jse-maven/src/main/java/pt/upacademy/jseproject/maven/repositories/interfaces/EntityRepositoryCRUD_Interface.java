package pt.upacademy.jseproject.maven.repositories.interfaces;

import pt.upacademy.jseproject.maven.model.Entity;
import pt.upacademy.jseproject.maven.utils.interfaces.CRUD_Interface;

public interface EntityRepositoryCRUD_Interface<T extends Entity> extends CRUD_Interface<T>{
	// Não é preciso redefinir os métodos pois vai herdar da classe base
	// Já implementa os métodos já definidos na class "CRUD_Interface" (/utils)
}
