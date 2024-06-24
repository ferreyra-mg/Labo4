package entidad;

import java.util.ArrayList;
import java.util.Date;

import negocioImpl.CuentaNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

public class Prestamo {

    private int id;
    private float prestamo;
    private int idCuenta;
    private int cantMeses;
    private float monto_mensual;
    private float monto_total;
    private boolean pagado;
    private boolean autorizado;
    private Date fecha;
    
    private Cuenta cuenta;
    
    private ArrayList<Cuota> cuotas;

    public Prestamo() { }

    public Prestamo(int id, float prestamo, int idCuenta, int cantMeses, float monto_mensual, float monto_total, boolean pagado, boolean autorizado, Date fecha) {
        this.id = id;
        this.prestamo = prestamo;
        this.idCuenta = idCuenta;
        this.cantMeses = cantMeses;
        this.pagado = pagado;
        this.autorizado = autorizado;
        this.monto_mensual = monto_mensual;
        this.monto_total = monto_total;
        this.fecha = fecha;
        
        this.cuotas = new ArrayList<Cuota>();
    }
    
    public Prestamo(float prestamo, int idCuenta, int cantMeses, float monto_mensual, float monto_total) {
        this.prestamo = prestamo;
        this.idCuenta = idCuenta;
        this.cantMeses = cantMeses;
        this.monto_mensual = monto_mensual;
        this.monto_total = monto_total;
        this.cuotas = new ArrayList<Cuota>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public float getPrestamo() { return prestamo; }
    public void setPrestamo(float prestamo) { this.prestamo = prestamo; }
    public int getIdCuenta() { return idCuenta; }
    public void setIdCuenta(int idCuenta) { this.idCuenta = idCuenta; }
    public int getCantMeses() { return cantMeses; }
    public void setCantMeses(int cantMeses) { this.cantMeses = cantMeses; }
    public boolean isPagado() { return pagado; }
    public void setPagado(boolean pagado) { this.pagado = pagado; }
    public boolean autorizado() { return autorizado; }
    public void setAutorizacion(boolean autorizado) { this.autorizado = autorizado; }
    
    public float getMontoMensual() { return this.monto_mensual; }
    public float getMontoTotal() { return this.monto_total; }
    
    public void setMontoMensual(float monto) { this.monto_mensual = monto; }
    public void setMontoTotal(float monto) { this.monto_total = monto; }
    
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public Date getFecha() { return this.fecha; }
    
    public void setCuenta(Cuenta cuenta) { this.cuenta = cuenta; }
    public Cuenta getCuenta() { return this.cuenta; }
    
    public void setCoutas(ArrayList<Cuota> cuotas) {
    	this.cuotas = cuotas;
    }
    
    public ArrayList<Cuota> getCuotas() {
    	return this.cuotas;
    }
    
    public boolean solicitar() {
    	return (new PrestamoNegocioImpl()).grabar(this);
    }
}
