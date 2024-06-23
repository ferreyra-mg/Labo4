package negocioImpl;

import daoImpl.PrestamoDaoImpl;
import entidad.Prestamo;
import negocio.PrestamoNegocio;

public class PrestamoNegocioImpl implements PrestamoNegocio {

	@Override
	public boolean grabar(Prestamo prestamo) {
		return (new PrestamoDaoImpl()).grabar(prestamo);
	}

}
