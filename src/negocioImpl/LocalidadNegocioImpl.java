package negocioImpl;

import java.util.ArrayList;

import dao.LocalidadDao;
import daoImpl.LocalidadDaoImpl;
import entidad.Localidad;
import negocio.LocalidadNegocio;

public class LocalidadNegocioImpl implements LocalidadNegocio{

	LocalidadDao loDao = new LocalidadDaoImpl();
	
	@Override
	public ArrayList<Localidad> obtenerLocalidadesPorProvincia(int provinciaId) {
		return loDao.obtenerLocalidadesPorProvincia(provinciaId);
	}

}
