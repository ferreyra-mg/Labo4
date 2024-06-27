package negocioImpl;

import java.util.ArrayList;

import negocio.TipoCuentaNegocio;
import dao.TipoCuentaDao;
import daoImpl.TipoCuentaDaoImpl;

public class TipoCuentaNegocioImpl implements TipoCuentaNegocio{
	
	@Override
	public ArrayList<String> traerTipo()
	{
		TipoCuentaDao tDao = new TipoCuentaDaoImpl();
		return tDao.traerTipo();
	}
}
