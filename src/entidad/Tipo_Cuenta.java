package entidad;

public class Tipo_Cuenta {

	 private int id;
	    private String descripcion;

	    // Constructor por defecto
	    public Tipo_Cuenta() {}

	    // Constructor con parámetros
	    public Tipo_Cuenta(int id, String descripcion) {
	        this.id = id;
	        this.descripcion = descripcion;
	    }

	    // Getters y Setters
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getDescripcion() {
	        return descripcion;
	    }

	    public void setDescripcion(String descripcion) {
	        this.descripcion = descripcion;
	    }

	    @Override
	    public String toString() {
	        return "TipoMovimiento [id=" + id + ", descripcion=" + descripcion + "]";
	    }
}
