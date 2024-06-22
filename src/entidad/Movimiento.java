package entidad;

import java.util.Date;

public class Movimiento {

    private int id;
    private int idCuenta;
    private String movimiento;
    private Date fecha;

    public Movimiento() { }

    public Movimiento(int id, int idCuenta, String movimiento, Date fecha) {
        this.id = id;
        this.idCuenta = idCuenta;
        this.movimiento = movimiento;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdCuenta() { return idCuenta; }
    public void setIdCuenta(int idCuenta) { this.idCuenta = idCuenta; }
    public String getMovimiento() { return movimiento; }
    public void setMovimiento(String movimiento) { this.movimiento = movimiento; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
}
