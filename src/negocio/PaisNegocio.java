package negocio;

import java.util.ArrayList;

import entidad.Pais;

public interface PaisNegocio {
	ArrayList<Pais> traerPaises();
	Pais obtenerPais(int pais);
}
