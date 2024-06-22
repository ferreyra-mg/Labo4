package entidad;

public class Administrador {

	private int id;
    private String usuario;
    private String contrasena;

    public Administrador() {
    }

	public Administrador(int id, String usu, String psw) {
		this.id = id;
		this.usuario = usu;
		this.contrasena = psw;
	}


	public int getID() { return id; }
	public void setID(int id) { this.id = id; }
	public String getUsuario() { return usuario; }
	public void setUsuario(String usuario) { this.usuario = usuario; }
	public String getContrasena() { return contrasena; }
	public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}

    