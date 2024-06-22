package negocioImpl;

import java.util.ArrayList;
import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import entidad.Cuenta;
import negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio {

	private CuentaDao cuentaDao = new CuentaDaoImpl();
	
	@Override 
	public String obtenerUsuario(int dni) {
		return cuentaDao.obtenerUsuario(dni);
	}

	@Override
	public void crearCuenta(Cuenta cuenta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarCuenta(Cuenta cuenta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cambiarEstadoCuenta(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Cuenta> obtenerTodasLasCuentas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Cuenta> obtenerTodasLasCuentas(int dni) {
		return (new CuentaDaoImpl()).obtenerTodasLasCuentas(dni);
	}

	@Override
	public Cuenta obtenerCuenta(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}