package entidad;

import java.util.Date;

public class Cuenta {

    private int id;
    private String usuario;
    private int dni; //relaciona con cliente correspondiente
    private String CBU;
    private Date creacion;
    private int tipo;
    private float saldo;
    private boolean estado;
    
    public Cuenta() {
    	this.estado= true;
    }
    
    public Cuenta(int id, String usuario, int dni, String CBU, Date creacion, int tipo, float saldo, boolean estado) {
        this.id = id;
        this.usuario = usuario;
        this.dni = dni;
        this.CBU = CBU;
        this.creacion = creacion;
        this.tipo = tipo;
        this.saldo = saldo;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public int getDni() { return dni; }
    public void setDni(int dni) { this.dni = dni; }
    public String getCBU() { return CBU; }
    public void setCBU(String CBU) { this.CBU = CBU; }
    public Date getCreacion() { return creacion; }
    public void setCreacion(Date creacion) { this.creacion = creacion; }
    public int getTipo() { return tipo; }
    public void setTipo(int tipo) { this.tipo = tipo; }
    public float getSaldo() { return saldo; }
    public void setSaldo(float saldo) { this.saldo = saldo; }
    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
}
