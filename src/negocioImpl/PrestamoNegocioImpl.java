package negocioImpl;

import java.util.ArrayList;
import java.util.Date;

import daoImpl.PrestamoDaoImpl;
import entidad.Prestamo;
import negocio.PrestamoNegocio;

public class PrestamoNegocioImpl implements PrestamoNegocio {

	private PrestamoDaoImpl presDao = new PrestamoDaoImpl();
	
	@Override
	public boolean grabar(Prestamo prestamo) {
		return presDao.grabar(prestamo);
	}

	@Override
	public ArrayList<Prestamo> traerTodos(int dni) {
		return presDao.traerTodos(dni);
	}
	
	@Override
	public ArrayList<Prestamo> traerPendientes() {
		return presDao.traerPendientes();
	}

	@Override
	public int obtenerPrestamosEntre(Date inicio, Date fin) {
		return presDao.obtenerPrestamosEntre(inicio, fin);
	}

	@Override
	public boolean autortizacionPrestamo(int idCuenta, boolean autoriz) {
		return presDao.autortizacionPrestamo(idCuenta, autoriz);
	}

	@Override
	public ArrayList<Prestamo> prestamosXCapital(float minimo, float maximo) {
		return presDao.prestamosXCapital(minimo, maximo);
	}

	@Override
	public ArrayList<Prestamo> traerPrestamos(int idCuenta) {
		return presDao.traerPrestamos(idCuenta);
	}
	
	
	

}
