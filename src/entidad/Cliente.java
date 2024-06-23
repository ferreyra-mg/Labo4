package entidad;

import java.util.ArrayList;
import java.util.Date;

import daoImpl.ClienteDaoImpl;
import daoImpl.CuentaDaoImpl;

public class Cliente {

    private int dni;
    private String cuil;
    private String nombre;
    private String apellido;
    private boolean sexo;
    private String nacionalidad;
    private Date fechaNacimiento;
    private String direccion;
    private String localidad;
    private String provincia;
    private String correoElectronico;
    private int telefono;
    private String contrasena;
    private boolean activo;
    
    public Cliente() {
    }
    
    
	public Cliente(int dni, String cuil, String nom, String ap, boolean sex,
			String nac, Date naciminiento, String dir, String loc, String prov,
			String cor, int tel, String psw) {
		this.dni = dni;
		this.cuil = cuil;
		this.nombre = nom;
		this.apellido = ap;
		this.sexo = sex;
		this.nacionalidad = nac;
		this.fechaNacimiento = naciminiento;
		this.direccion = dir;
		this.localidad = loc;
		this.provincia = prov;
		this.correoElectronico = cor;
		this.telefono = tel;
		this.contrasena = psw;
		this.activo = true;
	}
	
	
	
	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", cuil=" + cuil + ", nombre=" + nombre + ", apellido=" + apellido + ", sexo="
				+ sexo + ", nacionalidad=" + nacionalidad + ", fechaNacimiento=" + fechaNacimiento + ", direccion="
				+ direccion + ", localidad=" + localidad + ", provincia=" + provincia + ", correoElectronico="
				+ correoElectronico + ", telefono=" + telefono + ", contrasena=" + contrasena + "]";
	}


	public int getDni() { return dni; }
	public void setDni(int dni) { this.dni = dni; }
	public String getCuil() { return cuil; }
	public void setCuil(String cuil) { this.cuil = cuil; }
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public String getApellido() { return apellido; }
	public void setApellido(String apellido) { this.apellido = apellido; }
	public boolean getSexo() { return sexo; }
	public void setSexo(boolean sexo) { this.sexo = sexo; }
	public String getNacionalidad() { return nacionalidad; }
	public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
	public Date getFechaNacimiento() { return fechaNacimiento; }
	public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
	public String getDireccion() { return direccion; }
	public void setDireccion(String direccion) { this.direccion = direccion; }
	public String getLocalidad() { return localidad; }
	public void setLocalidad(String localidad) { this.localidad = localidad; }
	public String getProvincia() { return provincia; }
	public void setProvincia(String provincia) { this.provincia = provincia; }
	public String getCorreoElectronico() { return correoElectronico; }
	public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
	public int getTelefono() { return telefono; }
	public void setTelefono(int telefono) { this.telefono = telefono; }
	public String getContrasena() { return contrasena; }
	public void setContrasena(String contrasena) { this.contrasena = contrasena; }
	public boolean getActivo() { return activo; }
	public void setActivo(boolean activo) { this.activo = activo; }
	
	public ArrayList<Cuenta> cuentas() {
		return (new CuentaDaoImpl()).obtenerTodasLasCuentas(this.dni) ;
	}
}
