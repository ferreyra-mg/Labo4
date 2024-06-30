package negocio;

import java.util.ArrayList;
import java.util.Date;

import entidad.Prestamo;

public interface PrestamoNegocio {
	boolean grabar(Prestamo prestamo);
	
	ArrayList<Prestamo> traerTodos(int dni);
	
	ArrayList<Prestamo> traerPendientes();
	int obtenerPrestamosEntre(Date inicio,Date fin);
	
	boolean autortizacionPrestamo(int idCuenta, boolean autoriz);
}
