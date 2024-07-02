package negocioImpl;

import java.util.ArrayList;

import dao.ProvinciaDao;
import daoImpl.ProvinciaDaoImpl;
import entidad.Provincia;
import negocio.ProvinciaNegocio;

public class ProvinciaNegocioImpl implements ProvinciaNegocio {

	ProvinciaDao proDao = new ProvinciaDaoImpl();
	
	@Override
	public ArrayList<Provincia> obtenerProvinciasPorPais(int paisId) {
		return proDao.obtenerProvinciasPorPais(paisId);
	}

}
