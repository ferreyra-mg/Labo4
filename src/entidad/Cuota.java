package entidad;

import java.util.Date;

public class Cuota {

    private int id;
    private int idPrestamo;
    private float pagado;
    private Date fecha;

    public Cuota() { }

    public Cuota(int id, int idPrestamo, float pagado, Date fecha) {
        this.id = id;
        this.idPrestamo = idPrestamo;
        this.pagado = pagado;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdPrestamo() { return idPrestamo; }
    public void setIdPrestamo(int idPrestamo) { this.idPrestamo = idPrestamo; }
    public float getPagado() { return pagado; }
    public void setPagado(float pagado) { this.pagado = pagado; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
}
