package dao;

import java.util.ArrayList;

import entidad.Cuota;
import entidad.Prestamo;

public interface CuotaDao {
	ArrayList<Cuota> traerCuotas(int idPrestamo);
	boolean grabar(Cuota cuota);
}
