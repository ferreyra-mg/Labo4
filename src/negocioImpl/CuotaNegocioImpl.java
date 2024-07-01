package negocioImpl;

import negocio.CuotaNegocio;
import dao.CuotaDao;
import daoImpl.CuotaDaoImpl;

public class CuotaNegocioImpl implements CuotaNegocio{

	//private ClienteDao clienteDao = new ClienteDaoImpl();
	private CuotaDao cuDao = new CuotaDaoImpl();
	
	@Override
	public boolean pagarCuota(int idPrestamo) {
		return cuDao.pagarCuota(idPrestamo);
	}

}