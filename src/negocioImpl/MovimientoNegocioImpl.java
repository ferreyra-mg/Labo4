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
	
	public boolean transferirCbu(Cuenta cuenta, String cbuDestino, float monto){
		return mvDao.TransferirCbu(cuenta, cbuDestino, monto);
	}
	
	public float VerificarSaldo(String usuario) {
		return mvDao.VerificarSaldo(usuario);
	}

	@Override
	public int ObtenerCbuDestino(String tipoCuenta, Cuenta cuenta) {
		return mvDao.ObtenerCbuDestino(tipoCuenta, cuenta);
	}

	@Override
	public boolean TransferirEntreCuentas(Cuenta cuenta, int cbuDestino, int cbuEmisor, float monto) {
		return mvDao.TransferirEntreCuentas(cuenta, cbuDestino, cbuEmisor, monto);
	}

	@Override
	public int ObtenerCbuEmisor(String tipoCuenta, Cuenta cuenta) {
		return mvDao.ObtenerCbuEmisor(tipoCuenta, cuenta);
	}
}
