package negocioImpl;

import java.util.ArrayList;

import negocio.PaisNegocio;
import dao.PaisDao;
import daoImpl.PaisDaoImpl;

public class PaisNegocioImpl implements PaisNegocio{

	@Override
	public ArrayList<String> traerPaises() {
		PaisDao paisDao = new PaisDaoImpl();
		return paisDao.traerPaises();
	}

}
