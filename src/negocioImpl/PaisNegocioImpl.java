package negocioImpl;

import java.util.ArrayList;

import negocio.PaisNegocio;
import dao.PaisDao;
import daoImpl.PaisDaoImpl;
import entidad.Pais;

public class PaisNegocioImpl implements PaisNegocio{

	@Override
	public ArrayList<Pais> traerPaises() {
		PaisDao paisDao = new PaisDaoImpl();
		return paisDao.traerPaises();
	}

}
