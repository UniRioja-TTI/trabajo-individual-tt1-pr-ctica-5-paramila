package modelo;

public class Entidad {
	private int id;
	private String name;
	private String descripcion;

	public Entidad(int i, String entidad1, String prueba1) {
		this.id = i;
		this.name = entidad1;
		this.descripcion = prueba1;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
