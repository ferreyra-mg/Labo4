package negocioImpl;

import java.util.ArrayList;

import negocio.PaisNegocio;
import dao.PaisDao;
import daoImpl.PaisDaoImpl;
import entidad.Pais;

public class PaisNegocioImpl implements PaisNegocio{

	PaisDao paisDao = new PaisDaoImpl();
	@Override
	public ArrayList<Pais> traerPaises() {
		return paisDao.traerPaises();
	}

	@Override
	public Pais obtenerPais(int pais) {
		return paisDao.obtenerPais(pais);
	}

}
