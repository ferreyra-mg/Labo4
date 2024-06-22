package entidad;

public class Transferencia {

    private int idMovimiento;
    private int idRecibe;
    private float cantidad;

    public Transferencia() { }

    public Transferencia(int idMovimiento, int idRecibe, float cantidad) {
        this.idMovimiento = idMovimiento;
        this.idRecibe = idRecibe;
        this.cantidad = cantidad;
    }

    public int getIdMovimiento() { return idMovimiento; }
    public void setIdMovimiento(int idMovimiento) { this.idMovimiento = idMovimiento; }
    public int getIdRecibe() { return idRecibe; }
    public void setIdRecibe(int idRecibe) { this.idRecibe = idRecibe; }
    public float getCantidad() { return cantidad; }
    public void setCantidad(float cantidad) { this.cantidad = cantidad; }
}
