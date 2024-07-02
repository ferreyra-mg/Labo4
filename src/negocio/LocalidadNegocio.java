package negocio;

import java.util.ArrayList;

import entidad.Localidad;

public interface LocalidadNegocio {
	ArrayList<Localidad> obtenerLocalidadesPorProvincia(int provinciaId);
	Localidad obtenerLocalidad(int loc);
}
