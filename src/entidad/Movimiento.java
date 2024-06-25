package entidad;

import java.util.Date;

public class Movimiento {

    private int id;
    private String movimiento;
    private String detalle;
    private float importe;
    private Date fecha;

    public Movimiento() { }

    public Movimiento(int id, float importe, String movimiento, Date fecha, String detalle) {
        this.id = id;
        this.importe = importe;
        this.detalle = detalle;
        this.movimiento = movimiento;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMovimiento() { return movimiento; }
    public void setMovimiento(String movimiento) { this.movimiento = movimiento; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
	public float getImporte() { return importe; }
	public void setImporte(float importe) { this.importe = importe; }
	public String getDetalle() { return detalle; }
	public void setDetalle(String detalle) { this.detalle = detalle; }
}
