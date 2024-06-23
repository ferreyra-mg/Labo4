package negocio;

import java.util.ArrayList;

import entidad.Prestamo;

public interface PrestamoNegocio {
	boolean grabar(Prestamo prestamo);
	
	ArrayList<Prestamo> traerTodos(int dni);
}
