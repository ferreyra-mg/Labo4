package entidad;

public class Provincia {
	
	private int id;
	private int id_Pais;
	private String provincia;
	
	public Provincia() {
		
	}
	
	public Provincia(int id, String provincia) {
		this.id = id;
		this.provincia = provincia;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getId_Pais() {
		return id_Pais;
	}

	public void setId_Pais(int id_Pais) {
		this.id_Pais = id_Pais;
	}
	
	

}
