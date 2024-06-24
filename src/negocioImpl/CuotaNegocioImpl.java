package negocioImpl;

import java.util.ArrayList;

import daoImpl.CuotaDaoImpl;
import entidad.Cuota;
import negocio.CuotaNegocio;

public class CuotaNegocioImpl implements CuotaNegocio {

	private CuotaDaoImpl cuotaDao = new CuotaDaoImpl();

	@Override
	public ArrayList<Cuota> traerCuotas(int idPrestamo) {
		return cuotaDao.traerCuotas(idPrestamo);
	}

	@Override
	public boolean grabar(Cuota cuota) {
		return cuotaDao.grabar(cuota);
	}

}
