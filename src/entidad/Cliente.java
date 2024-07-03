package entidad;

import java.util.ArrayList;
import java.util.Date;

import negocioImpl.CuentaNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

public class Cliente {

    private int dni;
    private String cuil;
    private String nombre;
    private String apellido;
    private boolean sexo;
    private int nacionalidad; // Cambiado a int
    private Date fechaNacimiento;
    private String direccion;
    private int localidad; // Cambiado a int
    private int provincia; // Cambiado a int
    private String correoElectronico;
    private int telefono;
    private String contrasena;
    private boolean activo;
    
    public Cliente() {
    }
    
    public Cliente(int dni, String cuil, String nombre, String apellido, boolean sexo,
                   int nacionalidad, Date fechaNacimiento, String direccion, int localidad,
                   int provincia, String correoElectronico, int telefono, String contrasena) {
        this.dni = dni;
        this.cuil = cuil;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.contrasena = contrasena;
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
    public int getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(int nacionalidad) { this.nacionalidad = nacionalidad; }
    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public int getLocalidad() { return localidad; }
    public void setLocalidad(int localidad) { this.localidad = localidad; }
    public int getProvincia() { return provincia; }
    public void setProvincia(int provincia) { this.provincia = provincia; }
    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public int getTelefono() { return telefono; }
    public void setTelefono(int telefono) { this.telefono = telefono; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public boolean getActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    
    public ArrayList<Cuenta> cuentas() {
        return (new CuentaNegocioImpl()).obtenerTodasLasCuentas(this.dni);
    }
}
