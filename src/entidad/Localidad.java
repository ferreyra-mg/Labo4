package entidad;

public class Localidad {

	private int id;
	private int id_Provincia;
	private String localidad;
	
	public Localidad() {
		
	}
	
	public Localidad(int id, String localidad) {
		super();
		this.id = id;
		this.localidad = localidad;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public int getId_Provincia() {
		return id_Provincia;
	}

	public void setId_Provincia(int id_Provincia) {
		this.id_Provincia = id_Provincia;
	}
	
	
}
