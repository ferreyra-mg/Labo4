package negocioImpl;

import java.util.ArrayList;

import dao.MovimientoDao;
import daoImpl.MovimientoDaoImpl;
import entidad.Cuenta;
import entidad.Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio{
	
	private MovimientoDao mvDao = new MovimientoDaoImpl(); 
	
	public ArrayList<Movimiento> traerMovimientos(int id){
		return mvDao.traerMovimientos(id);
	} //devuelve todos los movimientos de un solo usuario
	
	public boolean transferirCbu(Cuenta cuenta, String cbuDestino, float monto, String tipoCuenta){
		return mvDao.TransferirCbu(cuenta, cbuDestino, monto, tipoCuenta);
	}

	@Override
	public String ObtenerCbuDestino(String tipoCuenta, Cuenta cuenta) {
		return mvDao.ObtenerCbuDestino(tipoCuenta, cuenta);
	}

	@Override
	public boolean TransferirEntreCuentas(Cuenta cuenta, String cbuDestino, String cbuEmisor, float monto) {
		return mvDao.TransferirEntreCuentas(cuenta, cbuDestino, cbuEmisor, monto);
	}

	@Override
	public String ObtenerCbuEmisor(String tipoCuenta, Cuenta cuenta) {
		return mvDao.ObtenerCbuEmisor(tipoCuenta, cuenta);
	}

	@Override
	public float VerificarSaldoxCuenta(String usuario, String tipoCuenta) {
		return mvDao.VerificarSaldoxCuenta(usuario, tipoCuenta);
	}
}
