package negocio;

import java.util.ArrayList;

import entidad.Cuota;

public interface CuotaNegocio {
	ArrayList<Cuota> traerCuotas(int idPrestamo);
	boolean grabar(Cuota cuota);
}
