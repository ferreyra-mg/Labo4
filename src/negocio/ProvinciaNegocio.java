package negocio;

import java.util.ArrayList;

import entidad.Provincia;

public interface ProvinciaNegocio {

	ArrayList<Provincia> obtenerProvinciasPorPais(int paisId);
	Provincia obtenerProvincia(int pro);
}
