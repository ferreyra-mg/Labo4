package negocioImpl;

import java.util.ArrayList;
import java.util.Date;

import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import entidad.Cuenta;
import entidad.Tipo_Cuenta;
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

	@Override
	public int obtenerCuentasEntre(Date inicio, Date fin) {
		return cuentaDao.obtenerCuentasEntre(inicio, fin);
	}

	@Override
	public ArrayList<Tipo_Cuenta> traerTipos() {
		return cuentaDao.traerTipos();
	}

	@Override
	public Tipo_Cuenta traerDescripcion(int id) {
		return cuentaDao.traerDescripcion(id);
	}
}