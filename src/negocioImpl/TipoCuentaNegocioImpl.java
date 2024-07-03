package negocioImpl;

import java.util.ArrayList;

import negocio.TipoCuentaNegocio;
import dao.TipoCuentaDao;
import daoImpl.TipoCuentaDaoImpl;
import entidad.Tipo_Cuenta;

public class TipoCuentaNegocioImpl implements TipoCuentaNegocio{
	
	TipoCuentaDao tDao = new TipoCuentaDaoImpl();
	@Override
	public ArrayList<String> traerTipo()
	{
		
		return tDao.traerTipo();
	}

	@Override
	public Tipo_Cuenta traerTipo(int id) {
		return tDao.traerTipo(id);
	}
}
