package pt.upacademy.jseproject.maven.model;

public abstract class Entity {
	protected Long id;	// Protected para ter acesso ao ID nas minhas sub-classes

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
