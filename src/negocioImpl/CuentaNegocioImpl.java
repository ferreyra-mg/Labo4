package negocioImpl;

import java.util.ArrayList;
import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import entidad.Cuenta;
import negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio {

	private CuentaDao cuentaDao = new CuentaDaoImpl();
	
	@Override 
	public Cuenta obtenerUsuario(int dni) {
		return cuentaDao.obtenerUsuario(dni);
	}

	@Override
	public boolean crearCuenta(Cuenta cuenta) {
		return cuentaDao.crearCuenta(cuenta);
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
		return cuentaDao.obtenerTodasLasCuentas(dni);
	}

	@Override
	public Cuenta obtenerCuenta(int id) {
		return cuentaDao.obtenerCuenta(id);
	}

	@Override
	public Cuenta obtenerCuentaxUsuario(String Usuario) {
		return cuentaDao.obtenerCuentaxUsuario(Usuario);
	}
	
	@Override
	public int numeroCuentas(int dni) {
		return cuentaDao.numeroCuentas(dni);
	}
}